package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.trihydro.timCreator.helpers.SQLNullHandler;

public class PathNodeXYService
{
	private Connection connection;

	public PathNodeXYService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertPathNodeXY(Long pathId, Long nodeXYId) {
    	try {
			
			String insertQueryStatement = "insert into path_node_xy(path_id, node_xy_id) values (?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"path_node_xy_id"});
			SQLNullHandler.setLongOrNull(preparedStatement, 1, pathId);
			SQLNullHandler.setLongOrNull(preparedStatement, 2, nodeXYId);

			// execute insert statement
 			Long pathNodeXYId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					pathNodeXYId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Path Node XY ID: " + pathNodeXYId + " --------------");
 				}
 			}
 			
			return pathNodeXYId;

	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return new Long(0);
    }

}