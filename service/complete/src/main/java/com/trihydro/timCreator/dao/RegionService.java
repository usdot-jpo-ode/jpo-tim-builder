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

    public Long insertRegions(Region regions[], Long dataFrameId, Long pathId, Long oldRegionId) {
	try {
		
		for(int i = 0; i < regions.length; i++){
		
			String insertQueryStatement = "insert into region(data_frame_id, name, regulator_id, segment_id, anchor_lat, anchor_long, anchor_elev, lane_width, directionality, closed_path, direction, region_type, description, path_id, old_region_id, geometry_direction, geometry_extent, geometry_lane_width, geometry_circle_position_lat, geometry_circle_position_long, geometry_circle_position_elev, geometry_circle_radius, geometry_circle_units) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"region_id"});
			
			preparedStatement.setString(1, dataFrameId.toString());		
			preparedStatement.setString(2, regions[i].getName());
			preparedStatement.setString(3, regions[i].getRegulatorID().toString());
			preparedStatement.setString(4, regions[i].getSegmentID().toString());   	
			preparedStatement.setString(5, regions[i].getAnchorPosition().getLatitude().toString());   	
			preparedStatement.setString(6, regions[i].getAnchorPosition().getLongitude().toString());   
			preparedStatement.setString(7, regions[i].getAnchorPosition().getElevation().toString());   
			preparedStatement.setString(8, regions[i].getLaneWidth().toString());   
			preparedStatement.setString(9, regions[i].getDirectionality().toString());  
			preparedStatement.setString(10, regions[i].getClosedPath().toString());  
			preparedStatement.setString(11, regions[i].getDirection());  
			preparedStatement.setString(12, regions[i].getRegionType());  
			preparedStatement.setString(13, regions[i].getDescription());  
			preparedStatement.setString(14, null);  
			preparedStatement.setString(15, null);  
			preparedStatement.setString(16, regions[i].getGeometry().getDirection()); 
			preparedStatement.setString(17, regions[i].getGeometry().getExtent().toString()); 
			preparedStatement.setString(18, regions[i].getGeometry().getLaneWidth().toString()); 
			preparedStatement.setString(19, regions[i].getGeometry().getCircle().getPosition().getLatitude().toString()); 
			preparedStatement.setString(20, regions[i].getGeometry().getCircle().getPosition().getLongitude().toString()); 
			preparedStatement.setString(21, regions[i].getGeometry().getCircle().getPosition().getElevation().toString()); 
			preparedStatement.setString(22, regions[i].getGeometry().getCircle().getRadius().toString()); 
			preparedStatement.setString(23, regions[i].getGeometry().getCircle().getUnits().toString()); 

			preparedStatement.executeUpdate();
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
		}

    } catch (SQLException e) {
   		e.printStackTrace();
  	}
	  	return new Long(0);
    }
}