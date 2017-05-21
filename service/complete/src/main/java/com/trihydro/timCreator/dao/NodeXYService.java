package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.model.NodeXY;
import com.trihydro.timCreator.DBUtility;
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
			preparedStatement.setString(2, nodeXY.getNodeLat().toString());
			preparedStatement.setString(3, nodeXY.getNodeLong().toString());
			preparedStatement.setString(4, nodeXY.getX().toString());
			preparedStatement.setString(5, nodeXY.getY().toString());
			preparedStatement.setString(6, nodeXY.getDWidth().toString());
			preparedStatement.setString(7, nodeXY.getDLength().toString());

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