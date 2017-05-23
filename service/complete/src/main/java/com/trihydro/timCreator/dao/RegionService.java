
package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.helpers.SQLNullHandler;
import com.trihydro.timCreator.model.Region;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class RegionService
{
	private Connection connection;

	public RegionService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertRegion(Region region, Long dataFrameId, Long oldRegionId, Long pathId) {
	try {

		String insertQueryStatement = "insert into region(data_frame_id, name, regulator_id, segment_id, anchor_lat, anchor_long, anchor_elev, lane_width, directionality, closed_path, direction, region_type, description, path_id, old_region_id";
		
		// 8
		if(region.getGeometry() != null){
			System.out.println("geometry not null");
			insertQueryStatement += ", geometry_direction, geometry_extent, geometry_lane_width, geometry_circle_position_lat, geometry_circle_position_long, geometry_circle_position_elev, geometry_circle_radius, geometry_circle_units) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}
		else
			insertQueryStatement += ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		System.out.println(insertQueryStatement);
		PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"region_id"});
		
		SQLNullHandler.setLongOrNull(preparedStatement, 1, dataFrameId);
		SQLNullHandler.setStringOrNull(preparedStatement, 2, region.getName());
		SQLNullHandler.setIntegerOrNull(preparedStatement, 3, region.getRegulatorID());
		SQLNullHandler.setIntegerOrNull(preparedStatement, 4, region.getSegmentID());
		SQLNullHandler.setIntegerOrNull(preparedStatement, 5, region.getSegmentID());
		preparedStatement.setString(5, String.valueOf((Object) region.getAnchorPosition().getLatitude()));   	
		preparedStatement.setString(6, String.valueOf((Object) region.getAnchorPosition().getLongitude()));   
		preparedStatement.setString(7, String.valueOf((Object) region.getAnchorPosition().getElevation()));   
		preparedStatement.setString(8, String.valueOf((Object) region.getLaneWidth()));   
		preparedStatement.setString(9, String.valueOf((Object) region.getDirectionality()));
		
		// closed path boolean conversion
		if(region.getClosedPath() != null){
			if(region.getClosedPath())
				preparedStatement.setString(10, "1");			
			else
				preparedStatement.setString(10, "0");	
		}
		else
			preparedStatement.setString(10, null);	
		
		preparedStatement.setString(11, region.getDirection());  
		preparedStatement.setString(12, region.getRegionType());  
		preparedStatement.setString(13, region.getDescription());		
		preparedStatement.setString(14,  String.valueOf((Object) pathId));  	
		preparedStatement.setString(15,  String.valueOf((Object) oldRegionId));  
	
		// if there is a geometry 
		if(region.getGeometry() != null) {
			preparedStatement.setString(16, region.getGeometry().getDirection()); 
			preparedStatement.setString(17, String.valueOf((Object) region.getGeometry().getExtent())); 
			preparedStatement.setString(18, String.valueOf((Object) region.getGeometry().getLaneWidth())); 
			preparedStatement.setString(19, String.valueOf((Object) region.getGeometry().getCircle().getPosition().getLatitude())); 
			preparedStatement.setString(20, String.valueOf((Object) region.getGeometry().getCircle().getPosition().getLongitude())); 
			preparedStatement.setString(21, String.valueOf((Object) region.getGeometry().getCircle().getPosition().getElevation())); 
			preparedStatement.setString(22, String.valueOf((Object) region.getGeometry().getCircle().getRadius())); 
			preparedStatement.setString(23, String.valueOf((Object) region.getGeometry().getCircle().getUnits())); 
		}

		// execute insert statement
			Long regionId = null;

			if(preparedStatement.executeUpdate() > 0){
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

				if(generatedKeys != null && generatedKeys.next()){
					regionId = generatedKeys.getLong(1);
					System.out.println("------ Generated Region ID: " + regionId + " --------------");
				}
			}
		return regionId;
	

	    } catch (SQLException e) {
	   		e.printStackTrace();
	  	}
	  	return new Long(0);
    }

    protected void insertGeometry(){


    }
}