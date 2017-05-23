package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.TIM;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

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
			
			preparedStatement.setString(1, String.valueOf(tim.getMsgCnt()));		
			preparedStatement.setString(2, tim.getPacketID());
			preparedStatement.setString(3, tim.getUrlB());
			preparedStatement.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));   	

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