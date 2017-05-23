package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.NodeXY;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class NodeXYService
{
	private Connection connection;

	public NodeXYService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertNodeXY(NodeXY nodeXY) {
    	try {
			
			String insertQueryStatement = "insert into path(delta, node_lat, node_long, x, y, attributes_dWidth, attributes_dLength) values (?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"path_id"});				
			preparedStatement.setString(1, nodeXY.getDelta());		
			preparedStatement.setString(2, String.valueOf(nodeXY.getNodeLat()));
			preparedStatement.setString(3, String.valueOf(nodeXY.getNodeLong()));
			preparedStatement.setString(4, String.valueOf(nodeXY.getX()));
			preparedStatement.setString(5, String.valueOf(nodeXY.getY()));
			preparedStatement.setString(6, String.valueOf(nodeXY.getDWidth()));
			preparedStatement.setString(7, String.valueOf(nodeXY.getDLength()));

			// execute insert statement
 			Long nodeXYId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					nodeXYId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Node XY ID: " + nodeXYId + " --------------");
 				}
 			}

 			
			return nodeXYId;

	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return new Long(0);
    }

}