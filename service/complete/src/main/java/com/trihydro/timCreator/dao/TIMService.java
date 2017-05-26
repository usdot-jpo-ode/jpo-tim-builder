package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.TIM;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.trihydro.timCreator.helpers.SQLNullHandler;

public class TIMService
{
	private Connection connection;

	public TIMService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertTIM(TIM tim) {
    	try {
			
			String insertQueryStatement = "insert into tim(msg_cnt, packet_id, url_b, time_stamp) values (?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"tim_id"});
			
			SQLNullHandler.setIntegerOrNull(preparedStatement, 1, tim.getMsgCnt());
			SQLNullHandler.setStringOrNull(preparedStatement, 2, tim.getPacketID());
			SQLNullHandler.setStringOrNull(preparedStatement, 3, tim.getUrlB());
			SQLNullHandler.setTimestampOrNull(preparedStatement, 4, new java.sql.Timestamp(System.currentTimeMillis()));

			// execute insert statement
 			Long timId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					timId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Tim ID: " + timId + " --------------");
 				}
 			}
			return timId;

	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return new Long(0);
    }

}