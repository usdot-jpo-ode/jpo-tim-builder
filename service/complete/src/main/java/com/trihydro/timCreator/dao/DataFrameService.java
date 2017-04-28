package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.model.SubmittedTIM;
import com.trihydro.timCreator.model.DataFrame;
import com.trihydro.timCreator.model.TIM;
import com.trihydro.timCreator.DBUtility;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class DataFrameService
{
	private Connection connection;

	public DataFrameService(){
		connection = DBUtility.getConnection();	
	}	

	    public void insertDataFrames(DataFrame dataFrames[], Long timId) {
    	try {
			
			RegionService regionService = new RegionService();
			for(int i = 0; i < dataFrames.length; i++){
			
				String insertQueryStatement = "insert into data_frame(tim_id, ssp_tim_rights, frame_type, msg_id, further_info_id, position_lat, position_long, position_elev, view_angle, mutcd, crc, start_time, duration_time, priority, ssp_location_rights, ssp_msg_types, ssp_msg_content, content, url) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"data_frame_id"});
				
				preparedStatement.setString(1, timId.toString());		
				preparedStatement.setString(2, String.valueOf(dataFrames[i].getsspTimRights()));
				preparedStatement.setString(3, dataFrames[i].getframeType().toString());
				preparedStatement.setString(4, dataFrames[i].getmsgID());   	
				preparedStatement.setString(5, dataFrames[i].getfurtherInfoID());   	
				preparedStatement.setString(6, dataFrames[i].getposition().getLatitude().toString());   	
				preparedStatement.setString(7, dataFrames[i].getposition().getLongitude().toString());   	
				preparedStatement.setString(8, dataFrames[i].getposition().getElevation().toString());   	
				preparedStatement.setString(9, dataFrames[i].getviewAngle());   	
				preparedStatement.setString(10, dataFrames[i].getmutcd().toString());   
				preparedStatement.setString(11, dataFrames[i].getcrc());   	
				preparedStatement.setString(12, dataFrames[i].getstartDateTime());   	
				preparedStatement.setString(13, dataFrames[i].getdurationTime().toString());
				preparedStatement.setString(14, dataFrames[i].getpriority().toString());   
				preparedStatement.setString(15, String.valueOf(dataFrames[i].getsspLocationRights()));  				
				preparedStatement.setString(16, String.valueOf(dataFrames[i].getsspMsgTypes()));   	
				preparedStatement.setString(17, String.valueOf(dataFrames[i].getsspMsgContent()));   	
				preparedStatement.setString(18, dataFrames[i].getcontent());
				preparedStatement.setString(19, dataFrames[i].geturl());  

				preparedStatement.executeUpdate();
				// execute insert statement
	 			Long dataFrameId = null;

	 			if(preparedStatement.executeUpdate() > 0){
	 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

	 				if(generatedKeys != null && generatedKeys.next()){
	 					dataFrameId = generatedKeys.getLong(1);
	 					System.out.println("------ Generated data frame ID: " + dataFrameId + " --------------");
	 				}
	 			}
	 			regionService.insertRegions(dataFrames[i].getRegions(), dataFrameId, new Long(0), new Long(0));
			}

	    } catch (SQLException e) {
	   		e.printStackTrace();
	  	}
    }

}