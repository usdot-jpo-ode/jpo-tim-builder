package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.ComputedLane;
import com.trihydro.timCreator.helpers.SQLNullHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ComputedLaneService
{
	private Connection connection;

	public ComputedLaneService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertComputedLane(ComputedLane computedLane) {
    	try {
			
			String insertQueryStatement = "insert into computed_lane(LANE_ID, OFFSET_SMALL_X, OFFSET_LARGE_X, OFFSET_SMALL_Y, OFFSET_LARGE_Y, ANGLE, X_SCALE, Y_SCALE) values (?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"computed_lane_id"});
			
			SQLNullHandler.setIntegerOrNull(preparedStatement, 1, computedLane.getLaneID());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 2, computedLane.getOffsetSmallX());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 3, computedLane.getOffsetLargeX());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 4, computedLane.getOffsetSmallY());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 5, computedLane.getOffsetLargeY());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 6, computedLane.getAngle());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 7, computedLane.getxScale());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 8, computedLane.getyScale());

			// execute insert statement
 			Long computedLaneId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					computedLaneId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Computed Lane ID: " + computedLaneId + " --------------");
 				}
 			}
 			
			return computedLaneId;

	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return new Long(0);
    }
}