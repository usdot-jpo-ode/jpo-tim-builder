package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.OldRegion;
import com.trihydro.timCreator.helpers.SQLNullHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class OldRegionService
{
	private Connection connection;

	public OldRegionService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertOldRegion(OldRegion oldRegion, Long shapePointId) {
    	try {
			    		    		                                    
			String insertQueryStatement = "insert into old_region(DIRECTION, EXTENT, AREA, CIRCLE_POSITION_LAT, CIRCLE_POSITION_LONG, CIRCLE_POSITION_ELEV, CIRCLE_RADIUS, CIRCLE_UNITS, SHAPE_POINT_ID, REGION_POINT_LAT, REGION_POINT_LONG, REGION_POINT_ELEV, REGION_POINT_SCALE) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"old_region_id"});
			
			SQLNullHandler.setStringOrNull(preparedStatement, 1, oldRegion.getDirection());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 2, oldRegion.getExtent());
			SQLNullHandler.setStringOrNull(preparedStatement, 3, oldRegion.getArea());
			
			if(oldRegion.getCircle() != null && oldRegion.getCircle().getPosition() != null){
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 4, oldRegion.getCircle().getPosition().getLatitude());
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 5, oldRegion.getCircle().getPosition().getLongitude());
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 6, oldRegion.getCircle().getPosition().getElevation());
			}
			else{
				preparedStatement.setNull(4, java.sql.Types.NUMERIC);
				preparedStatement.setNull(5, java.sql.Types.NUMERIC);
				preparedStatement.setNull(6, java.sql.Types.NUMERIC);				
			}
			
			if(oldRegion.getCircle() != null){
				SQLNullHandler.setIntegerOrNull(preparedStatement, 7, oldRegion.getCircle().getRadius());
				SQLNullHandler.setIntegerOrNull(preparedStatement, 8, oldRegion.getCircle().getUnits());
			}
			else{
				preparedStatement.setNull(7, java.sql.Types.NUMERIC);	
				preparedStatement.setNull(8, java.sql.Types.NUMERIC);	
			}
			
			SQLNullHandler.setLongOrNull(preparedStatement, 9, shapePointId);
			
			if(oldRegion.getRegionPoint() != null && oldRegion.getRegionPoint().getPosition() != null){
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 10, oldRegion.getRegionPoint().getPosition().getLatitude());
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 11, oldRegion.getRegionPoint().getPosition().getLongitude());
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 12, oldRegion.getRegionPoint().getPosition().getElevation());
			}
			else{
				preparedStatement.setNull(10, java.sql.Types.NUMERIC);
				preparedStatement.setNull(11, java.sql.Types.NUMERIC);
				preparedStatement.setNull(12, java.sql.Types.NUMERIC);	
			}
			
			if(oldRegion.getRegionPoint() != null)				
				SQLNullHandler.setIntegerOrNull(preparedStatement, 13, oldRegion.getRegionPoint().getScale());
			else
				preparedStatement.setNull(13, java.sql.Types.NUMERIC);	
				
			// execute insert statement
 			Long oldRegionId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					oldRegionId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Old Region ID: " + oldRegionId + " --------------");
 				}
 			}
 			
			return oldRegionId;

	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return new Long(0);
    }
}