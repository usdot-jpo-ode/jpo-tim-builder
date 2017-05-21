package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.DisabledList;

public class DisabledListService {
	
	private Connection connection;

	public DisabledListService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertDisabledList(DisabledList disabledList, Long nodeXYId) {
    	try {
			
			String insertQueryStatement = "insert into disabled_list(node_xy_id, type) values (?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"disabled_list_id"});
			
			preparedStatement.setString(1, nodeXYId.toString());		
			preparedStatement.setString(2, disabledList.getType().toString());

			// execute insert statement
 			Long disabledListId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					disabledListId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Disabled List Id: " + disabledListId + " --------------");
 				}
 			}
 			
			return disabledListId;

	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return new Long(0);
    }
}
