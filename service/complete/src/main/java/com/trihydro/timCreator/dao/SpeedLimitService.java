package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.SpeedLimits;

public class SpeedLimitService {
	
	private Connection connection;

	public SpeedLimitService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertSpeedLimit(SpeedLimits speedLimits, Long dataListId) {
    	try {
			
			String insertQueryStatement = "insert into speed_limits(data_list_id, type, velocity) values (?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"speed_limits_id"});
			
			preparedStatement.setString(1, dataListId.toString());		
			preparedStatement.setString(2, speedLimits.getType().toString());
			preparedStatement.setString(3, speedLimits.getVelocity().toString());

			// execute insert statement
 			Long speedLimitsId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					speedLimitsId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Speed Limits Id: " + speedLimitsId + " --------------");
 				}
 			}
 			
			return speedLimitsId;

	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return new Long(0);
    }
}
