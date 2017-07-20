package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.SubmittedTIM;
import com.trihydro.timCreator.helpers.SQLNullHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

				SQLNullHandler.setIntegerOrNull(preparedStatement, 1, submittedTIM.getRSUs()[i].getRsuId());
				SQLNullHandler.setLongOrNull(preparedStatement, 2, timId);
				SQLNullHandler.setTimestampOrNull(preparedStatement, 3, java.sql.Timestamp.valueOf(LocalDateTime.parse(submittedTIM.getDateSent(), DateTimeFormatter.ISO_DATE_TIME)));
				SQLNullHandler.setTimestampOrNull(preparedStatement, 4, java.sql.Timestamp.valueOf(LocalDateTime.parse(submittedTIM.getDateReceived(), DateTimeFormatter.ISO_DATE_TIME)));
				if(submittedTIM.getSNMP() != null){
					SQLNullHandler.setStringOrNull(preparedStatement, 5, submittedTIM.getSNMP().getRsuid());
					SQLNullHandler.setIntegerOrNull(preparedStatement, 6, submittedTIM.getSNMP().getMsgid());
					SQLNullHandler.setIntegerOrNull(preparedStatement, 7, submittedTIM.getSNMP().getMode());
					SQLNullHandler.setIntegerOrNull(preparedStatement, 8, submittedTIM.getSNMP().getChannel());
					SQLNullHandler.setIntegerOrNull(preparedStatement, 9, submittedTIM.getSNMP().getInterval());
				    SQLNullHandler.setTimestampOrNull(preparedStatement, 10, java.sql.Timestamp.valueOf(LocalDateTime.parse(submittedTIM.getSNMP().getDeliverystart(), DateTimeFormatter.ISO_DATE_TIME)));
				    SQLNullHandler.setTimestampOrNull(preparedStatement, 11, java.sql.Timestamp.valueOf(LocalDateTime.parse(submittedTIM.getSNMP().getDeliverystop(), DateTimeFormatter.ISO_DATE_TIME)));
					SQLNullHandler.setIntegerOrNull(preparedStatement, 12, submittedTIM.getSNMP().getEnable());
					SQLNullHandler.setIntegerOrNull(preparedStatement, 13, submittedTIM.getSNMP().getStatus());				  	
				}
				else{
					preparedStatement.setNull(5, java.sql.Types.VARCHAR);
					preparedStatement.setNull(6, java.sql.Types.NUMERIC);
					preparedStatement.setNull(7, java.sql.Types.NUMERIC);
					preparedStatement.setNull(8, java.sql.Types.NUMERIC);
					preparedStatement.setNull(9, java.sql.Types.NUMERIC);
					preparedStatement.setNull(10, java.sql.Types.TIMESTAMP);
					preparedStatement.setNull(11, java.sql.Types.TIMESTAMP);
					preparedStatement.setNull(12, java.sql.Types.NUMERIC);
					preparedStatement.setNull(13, java.sql.Types.NUMERIC);
				}

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