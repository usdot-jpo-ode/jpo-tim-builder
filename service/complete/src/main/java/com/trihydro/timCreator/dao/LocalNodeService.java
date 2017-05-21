package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.LocalNode;

public class LocalNodeService {
	
	private Connection connection;

	public LocalNodeService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertLocalNode(LocalNode localNode, Long nodeXYId) {
    	try {
			
			String insertQueryStatement = "insert into local_node(node_xy_id, type) values (?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"local_node_id"});
			
			preparedStatement.setString(1, nodeXYId.toString());		
			preparedStatement.setString(2, localNode.getType().toString());

			// execute insert statement
 			Long localNodeId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					localNodeId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Local Node Id: " + localNodeId + " --------------");
 				}
 			}
 			
			return localNodeId;

	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return new Long(0);
    }
}
