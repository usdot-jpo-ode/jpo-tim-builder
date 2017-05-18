package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.model.SubmittedTIM;
import com.trihydro.timCreator.model.DataFrame;
import com.trihydro.timCreator.model.TIM;
import com.trihydro.timCreator.model.Region;
import com.trihydro.timCreator.DBUtility;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
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
		
		preparedStatement.setString(1, dataFrameId.toString());		
		preparedStatement.setString(2, region.getName());
		preparedStatement.setString(3, region.getRegulatorID().toString());
		preparedStatement.setString(4, region.getSegmentID().toString());   	
		preparedStatement.setString(5, region.getAnchorPosition().getLatitude().toString());   	
		preparedStatement.setString(6, region.getAnchorPosition().getLongitude().toString());   
		preparedStatement.setString(7, region.getAnchorPosition().getElevation().toString());   
		preparedStatement.setString(8, region.getLaneWidth().toString());   
		preparedStatement.setString(9, region.getDirectionality().toString());  
		preparedStatement.setString(10, region.getClosedPath().toString());  
		preparedStatement.setString(11, region.getDirection());  
		preparedStatement.setString(12, region.getRegionType());  
		preparedStatement.setString(13, region.getDescription());  
		preparedStatement.setString(14, null);  
		preparedStatement.setString(15, null);  

		if(region.getGeometry() != null) {
			preparedStatement.setString(16, region.getGeometry().getDirection()); 
			preparedStatement.setString(17, region.getGeometry().getExtent().toString()); 
			preparedStatement.setString(18, region.getGeometry().getLaneWidth().toString()); 
			preparedStatement.setString(19, region.getGeometry().getCircle().getPosition().getLatitude().toString()); 
			preparedStatement.setString(20, region.getGeometry().getCircle().getPosition().getLongitude().toString()); 
			preparedStatement.setString(21, region.getGeometry().getCircle().getPosition().getElevation().toString()); 
			preparedStatement.setString(22, region.getGeometry().getCircle().getRadius().toString()); 
			preparedStatement.setString(23, region.getGeometry().getCircle().getUnits().toString()); 
		}

		// execute insert statement
			Long regionId = null;

			if(preparedStatement.executeUpdate() > 0){
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

				if(generatedKeys != null && generatedKeys.next()){
					regionId = generatedKeys.getLong(1);
					System.out.println("------ Generated region ID: " + regionId + " --------------");
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