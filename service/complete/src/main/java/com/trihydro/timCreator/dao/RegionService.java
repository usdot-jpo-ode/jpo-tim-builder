
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

		String insertQueryStatement = "";
		if(region.getGeometry() != null)
			insertQueryStatement = "insert into region(data_frame_id, name, regulator_id, segment_id, anchor_lat, anchor_long, anchor_elev, lane_width, directionality, closed_path, direction, region_type, description, path_id, old_region_id, geometry_direction, geometry_extent, geometry_lane_width, geometry_circle_position_lat, geometry_circle_position_long, geometry_circle_position_elev, geometry_circle_radius, geometry_circle_units) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		else{
			insertQueryStatement = "insert into region(data_frame_id, name, regulator_id, segment_id, anchor_lat, anchor_long, anchor_elev, lane_width, directionality, closed_path, direction, region_type, description, path_id, old_region_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}

		PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"region_id"});
		
		SQLNullHandler.setLongOrNull(preparedStatement, 1, dataFrameId);
		SQLNullHandler.setStringOrNull(preparedStatement, 2, region.getName());
		SQLNullHandler.setIntegerOrNull(preparedStatement, 3, region.getRegulatorID());
		SQLNullHandler.setIntegerOrNull(preparedStatement, 4, region.getSegmentID());
		
		if(region.getAnchorPosition() != null){
			SQLNullHandler.setBigDecimalOrNull(preparedStatement, 5, region.getAnchorPosition().getLatitude());
			SQLNullHandler.setBigDecimalOrNull(preparedStatement, 6, region.getAnchorPosition().getLongitude());
			SQLNullHandler.setBigDecimalOrNull(preparedStatement, 7, region.getAnchorPosition().getElevation());
		}
		else{
			preparedStatement.setNull(5, java.sql.Types.NUMERIC);
			preparedStatement.setNull(6, java.sql.Types.NUMERIC);
			preparedStatement.setNull(7, java.sql.Types.NUMERIC);	
		}
		
		SQLNullHandler.setIntegerOrNull(preparedStatement, 8, region.getLaneWidth());
		SQLNullHandler.setLongOrNull(preparedStatement, 9, region.getDirectionality());
		
		// closed path boolean conversion
		if(region.getClosedPath() != null){
			if(region.getClosedPath())
				SQLNullHandler.setIntegerOrNull(preparedStatement, 10, 1);						
			else				
				SQLNullHandler.setIntegerOrNull(preparedStatement, 10, 1);
		}
		else
			preparedStatement.setNull(10, java.sql.Types.NUMERIC);
		
		SQLNullHandler.setStringOrNull(preparedStatement, 11, region.getDirection());
		SQLNullHandler.setStringOrNull(preparedStatement, 12, region.getRegionType());
		SQLNullHandler.setStringOrNull(preparedStatement, 13, region.getDescription());
		SQLNullHandler.setLongOrNull(preparedStatement, 14, pathId);
		SQLNullHandler.setLongOrNull(preparedStatement, 15, oldRegionId);
			
		// if there is a geometry 
		if(region.getGeometry() != null) {
			SQLNullHandler.setStringOrNull(preparedStatement, 16, region.getGeometry().getDirection());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 17, region.getGeometry().getExtent());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 18, region.getGeometry().getLaneWidth());
			
			if(region.getGeometry().getCircle() != null && region.getGeometry().getCircle().getPosition() != null){
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 19, region.getGeometry().getCircle().getPosition().getLatitude());
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 20, region.getGeometry().getCircle().getPosition().getLongitude());
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 21, region.getGeometry().getCircle().getPosition().getElevation());
			}
			else{
				preparedStatement.setNull(19, java.sql.Types.NUMERIC);
				preparedStatement.setNull(20, java.sql.Types.NUMERIC);
				preparedStatement.setNull(21, java.sql.Types.NUMERIC);	
			}
			
			if(region.getGeometry().getCircle() != null){
				SQLNullHandler.setIntegerOrNull(preparedStatement, 22, region.getGeometry().getCircle().getRadius());
				SQLNullHandler.setIntegerOrNull(preparedStatement, 23, region.getGeometry().getCircle().getUnits());
			}
			else{
				preparedStatement.setNull(22, java.sql.Types.NUMERIC);
				preparedStatement.setNull(23, java.sql.Types.NUMERIC);
			}			
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