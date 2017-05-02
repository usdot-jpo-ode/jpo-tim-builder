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
			DataFrameItisCodeService dataFrameItisCodeService = new DataFrameItisCodeService();

			for(int i = 0; i < dataFrames.length; i++) {
				System.out.println("dataframes length: " + dataFrames.length);
				String insertQueryStatement = "insert into data_frame(tim_id, ssp_tim_rights, frame_type, msg_id, further_info_id, position_lat, position_long, position_elev, view_angle, mutcd, crc, start_date_time, duration_time, priority, ssp_location_rights, ssp_msg_types, ssp_msg_content, content, url) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"data_frame_id"});
				
				preparedStatement.setString(1, timId.toString());		
				preparedStatement.setString(2, String.valueOf(dataFrames[i].getSspTimRights()));
				preparedStatement.setString(3, dataFrames[i].getFrameType().toString());
				preparedStatement.setString(4, dataFrames[i].getMsgID());   	
				preparedStatement.setString(5, dataFrames[i].getFurtherInfoID());   	
				preparedStatement.setString(6, dataFrames[i].getPosition().getLatitude().toString());   	
				preparedStatement.setString(7, dataFrames[i].getPosition().getLongitude().toString());   	
				preparedStatement.setString(8, dataFrames[i].getPosition().getElevation().toString());   	
				preparedStatement.setString(9, dataFrames[i].getViewAngle());   	
				preparedStatement.setString(10, dataFrames[i].getMutcd().toString());   
				preparedStatement.setString(11, dataFrames[i].getCrc());   	
				preparedStatement.setString(12, dataFrames[i].getStartDateTime());   	
				preparedStatement.setString(13, dataFrames[i].getDurationTime().toString());
				preparedStatement.setString(14, dataFrames[i].getPriority().toString());   
				preparedStatement.setString(15, String.valueOf(dataFrames[i].getSspLocationRights()));  				
				preparedStatement.setString(16, String.valueOf(dataFrames[i].getSspMsgTypes()));   	
				preparedStatement.setString(17, String.valueOf(dataFrames[i].getSspMsgContent()));   	
				preparedStatement.setString(18, dataFrames[i].getContent());
				preparedStatement.setString(19, dataFrames[i].getUrl());  

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
	 			dataFrameItisCodeService.insertDataFrameItisCode(dataFrameId, dataFrames[i]);

			}

	    } catch (SQLException e) {
	   		e.printStackTrace();
	  	}
    }

}