package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.EnabledList;
import com.trihydro.timCreator.helpers.SQLNullHandler;

public class EnabledListService {
	
	private Connection connection;

	public EnabledListService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertEnabledList(EnabledList enabledList, Long nodeXYId) {
    	try {
			
			String insertQueryStatement = "insert into enabled_list(node_xy_id, type) values (?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"enabled_list_id"});
			
			SQLNullHandler.setLongOrNull(preparedStatement, 1, nodeXYId);
			SQLNullHandler.setLongOrNull(preparedStatement, 2, enabledList.getType());

			// execute insert statement
 			Long enabledListId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					enabledListId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Enabled List Id: " + enabledListId + " --------------");
 				}
 			}
 			
			return enabledListId;

	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return new Long(0);
    }
}
