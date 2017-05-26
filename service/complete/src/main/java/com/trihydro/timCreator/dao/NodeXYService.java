package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.NodeXY;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.trihydro.timCreator.helpers.SQLNullHandler;

public class NodeXYService
{
	private Connection connection;

	public NodeXYService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertNodeXY(NodeXY nodeXY) {
    	try {
			
			String insertQueryStatement = "insert into node_xy(delta, node_lat, node_long, x, y, attributes_dWidth, attributes_delevation) values (?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"node_xy_id"});
			SQLNullHandler.setStringOrNull(preparedStatement, 1, nodeXY.getDelta());
			SQLNullHandler.setLongOrNull(preparedStatement, 2, nodeXY.getNodeLat());
			SQLNullHandler.setLongOrNull(preparedStatement, 3, nodeXY.getNodeLong());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 4, nodeXY.getX());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 5, nodeXY.getY());
			if(nodeXY.getAttributes() != null){
				SQLNullHandler.setIntegerOrNull(preparedStatement, 6, nodeXY.getAttributes().getdWidth());
				SQLNullHandler.setIntegerOrNull(preparedStatement, 7, nodeXY.getAttributes().getdElevation());
			}
			else{
				preparedStatement.setNull(6, java.sql.Types.NUMERIC);
				preparedStatement.setNull(7, java.sql.Types.NUMERIC);
			}

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