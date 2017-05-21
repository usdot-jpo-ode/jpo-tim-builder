package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.model.OldRegion;
import com.trihydro.timCreator.DBUtility;
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
			    		    		                                    
			String insertQueryStatement = "insert into old_region(DIRECTION, EXTENT, AREA, CIRCLE_POSITION_LAT, CIRCLE_POSITION_LONG, CIRCLE_POSITION_ELEV, CIRCLE_RADIUS, CIRCLE_UNITS, SHAPE_POINT_ID, REGION_POINT_LAT, REGION_POINT_LONG, REGION_POINT_ELEV, SCALE) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"path_id"});
			
			preparedStatement.setString(1, oldRegion.getDirection().toString());		
			preparedStatement.setString(2, oldRegion.getExtent().toString());
			preparedStatement.setString(3, oldRegion.getArea().toString());
			preparedStatement.setString(4, oldRegion.getCircle().getPosition().getLatitude().toString());
			preparedStatement.setString(5, oldRegion.getCircle().getPosition().getLongitude().toString());
			preparedStatement.setString(6, oldRegion.getCircle().getPosition().getElevation().toString());
			preparedStatement.setString(7, oldRegion.getCircle().getRadius().toString());
			preparedStatement.setString(8, oldRegion.getCircle().getUnits().toString());
			preparedStatement.setString(9, shapePointId.toString());
			preparedStatement.setString(10, oldRegion.getRegionPoint().getPosition().getLatitude().toString());
			preparedStatement.setString(11, oldRegion.getRegionPoint().getPosition().getLongitude().toString());
			preparedStatement.setString(12, oldRegion.getRegionPoint().getPosition().getElevation().toString());
			preparedStatement.setString(13, oldRegion.getRegionPoint().getScale().toString());

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