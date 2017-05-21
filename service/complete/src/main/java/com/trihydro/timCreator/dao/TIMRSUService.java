package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.model.SubmittedTIM;
import com.trihydro.timCreator.DBUtility;
import java.sql.Connection;
import java.sql.SQLException;
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
				System.out.println("rsus length: " + submittedTIM.getRSUs().length);
				String insertQueryStatement = "insert into tim_rsu(rsu_id, tim_id, date_sent, date_received, snmp_rsu_id, snmp_msg_id, snmp_mode, snmp_channel, snmp_interval, snmp_delivery_start, snmp_delivery_stop, snmp_enable, snmp_status) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"tim_rsu_id"});

				System.out.println("****** rsu ID: " + submittedTIM.getRSUs()[i].getRsuId().toString() + " ********");
				preparedStatement.setString(1, submittedTIM.getRSUs()[i].getRsuId().toString());		
				preparedStatement.setString(2, timId.toString());
				preparedStatement.setString(3, submittedTIM.getDateSent());
				preparedStatement.setString(4, submittedTIM.getDateReceived());   	
				preparedStatement.setString(5, submittedTIM.getSNMP().getRsuid());  
				preparedStatement.setString(6, submittedTIM.getSNMP().getMsgid().toString());  
				preparedStatement.setString(7, submittedTIM.getSNMP().getMode().toString());
				preparedStatement.setString(8, submittedTIM.getSNMP().getChannel().toString());
				preparedStatement.setString(9, submittedTIM.getSNMP().getInterval().toString());  
				preparedStatement.setString(10, submittedTIM.getSNMP().getDeliverystart());  
				preparedStatement.setString(11, submittedTIM.getSNMP().getDeliverystop());  
				preparedStatement.setString(12, submittedTIM.getSNMP().getEnable().toString());  
				preparedStatement.setString(13, submittedTIM.getSNMP().getStatus().toString());  

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