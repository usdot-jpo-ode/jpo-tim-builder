package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.model.SubmittedTIM;
import com.trihydro.timCreator.model.TIM;
import com.trihydro.timCreator.model.RSU;
import com.trihydro.timCreator.DBUtility;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class TIMRSUService
{
	private Connection connection;

	public TIMRSUService(){
		connection = DBUtility.getConnection();	
	}	

    public void insertTIMRSUs(SubmittedTIM submittedTIM, Long timId) {
    	try {
			
			for(int i = 0; i < submittedTIM.getRSUs().length; i++) {
				String insertQueryStatement = "insert into tim_rsu(rsu_id, tim_id, date_sent, date_received, snmp_rsu_id, snmp_msg_id, snmp_mode, snmp_channel, snmp_interval, snmp_delivery_start, snmp_delivery_stop, snmp_enable, snmp_status) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"tim_rsu_id"});

				System.out.println("****** rsu ID: " + submittedTIM.getRSUs()[i].getRsuId().toString() + " ********");
				preparedStatement.setString(1, submittedTIM.getRSUs()[i].getRsuId().toString());		
				preparedStatement.setString(2, timId.toString());
				preparedStatement.setString(3, submittedTIM.getDateSent());
				preparedStatement.setString(4, submittedTIM.getDateReceived());   	
				preparedStatement.setString(5, submittedTIM.getSNMP().getRsuId());  
				preparedStatement.setString(6, submittedTIM.getSNMP().getMsgId().toString());  
				preparedStatement.setString(7, submittedTIM.getSNMP().getMode().toString());
				preparedStatement.setString(8, submittedTIM.getSNMP().getChannel().toString());
				preparedStatement.setString(9, submittedTIM.getSNMP().getInterval().toString());  
				preparedStatement.setString(10, submittedTIM.getSNMP().getDeliveryStart());  
				preparedStatement.setString(11, submittedTIM.getSNMP().getDeliveryStop());  
				preparedStatement.setString(12, submittedTIM.getSNMP().getEnable().toString());  
				preparedStatement.setString(13, submittedTIM.getSNMP().getStatus().toString());  

				preparedStatement.executeUpdate();

				// execute insert statement
	 			Long timRsuId = null;

	 			if(preparedStatement.executeUpdate() > 0){
	 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

	 				if(generatedKeys != null && generatedKeys.next()){
	 					timRsuId = generatedKeys.getLong(1);
	 					System.out.println("------ Generated tim rsu ID: " + timRsuId + " --------------");
	 				}
	 			}
	 		}
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
    }

}