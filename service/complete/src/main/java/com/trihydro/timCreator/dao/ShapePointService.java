package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.ShapePoint;

public class ShapePointService {
	
	private Connection connection;

	public ShapePointService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertShapePoint(ShapePoint shapePoint, Long computedLaneId) {
    	try {
    		
			String insertQueryStatement = "insert into shape_point(POSITION_LAT, POSITION_LONG, POSITION_ELEV, LANE_WIDTH, DIRECTIONALITY, NODE_TYPE, COMPUTED_LANE_ID) values (?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"shape_point_id"});
			
			preparedStatement.setString(1, shapePoint.getPosition().getLatitude().toString());
			preparedStatement.setString(2, shapePoint.getPosition().getLongitude().toString());
			preparedStatement.setString(3, shapePoint.getPosition().getElevation().toString());
			preparedStatement.setString(4, shapePoint.getLaneWidth().toString());
			preparedStatement.setString(5, shapePoint.getDirectionality().toString());
			preparedStatement.setString(6, shapePoint.getNodeType());
			preparedStatement.setString(7, computedLaneId.toString());

			// execute insert statement
 			Long shapePointId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					shapePointId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Shape Point Id: " + shapePointId + " --------------");
 				}
 			}
 			
			return shapePointId;

	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return new Long(0);
    }
}
