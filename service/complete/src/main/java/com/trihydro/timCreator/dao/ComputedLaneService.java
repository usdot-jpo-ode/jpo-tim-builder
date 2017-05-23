package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.ComputedLane;

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
			
			preparedStatement.setString(1, computedLane.getLaneID().toString());		
			preparedStatement.setString(2, computedLane.getOffsetSmallX().toString());
			preparedStatement.setString(3, computedLane.getOffsetLargeX().toString());
			preparedStatement.setString(4, computedLane.getOffsetSmallY().toString());
			preparedStatement.setString(5, computedLane.getOffsetLargeY().toString());
			preparedStatement.setString(6, computedLane.getAngle().toString());
			preparedStatement.setString(7, computedLane.getxScale().toString());
			preparedStatement.setString(8, computedLane.getyScale().toString());

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