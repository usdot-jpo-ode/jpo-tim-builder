package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.SpeedLimits;
import com.trihydro.timCreator.helpers.SQLNullHandler;

public class SpeedLimitService {
	
	private Connection connection;

	public SpeedLimitService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertSpeedLimit(SpeedLimits speedLimits, Long dataListId) {
    	try {
			
			String insertQueryStatement = "insert into speed_limits(data_list_id, type, velocity) values (?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"speed_limits_id"});
			
			SQLNullHandler.setLongOrNull(preparedStatement, 1, dataListId);
			SQLNullHandler.setLongOrNull(preparedStatement, 2, speedLimits.getType());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 3, speedLimits.getVelocity());

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
