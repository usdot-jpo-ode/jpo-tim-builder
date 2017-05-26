package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.ShapePoint;
import com.trihydro.timCreator.helpers.SQLNullHandler;

public class ShapePointService {
	
	private Connection connection;

	public ShapePointService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertShapePoint(ShapePoint shapePoint, Long computedLaneId) {
    	try {
    		
			String insertQueryStatement = "insert into shape_point(POSITION_LAT, POSITION_LONG, POSITION_ELEV, LANE_WIDTH, DIRECTIONALITY, NODE_TYPE, COMPUTED_LANE_ID) values (?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"shape_point_id"});
			
			if(shapePoint.getPosition() != null){
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 1, shapePoint.getPosition().getLatitude());
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 2, shapePoint.getPosition().getLongitude());
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 3, shapePoint.getPosition().getElevation());
			}
			else{
				preparedStatement.setNull(1, java.sql.Types.NUMERIC);
				preparedStatement.setNull(2, java.sql.Types.NUMERIC);
				preparedStatement.setNull(3, java.sql.Types.NUMERIC);	
			}
			
			SQLNullHandler.setIntegerOrNull(preparedStatement, 4, shapePoint.getLaneWidth());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 5, shapePoint.getDirectionality());
			SQLNullHandler.setStringOrNull(preparedStatement, 6, shapePoint.getNodeType());
			SQLNullHandler.setLongOrNull(preparedStatement, 7, computedLaneId);

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
