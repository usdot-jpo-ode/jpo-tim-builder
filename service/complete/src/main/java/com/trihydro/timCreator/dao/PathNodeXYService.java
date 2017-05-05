package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.model.NodeXY;
import com.trihydro.timCreator.DBUtility;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class PathNodeXYService
{
	private Connection connection;

	public PathNodeXYService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertPathNodeXY(Long pathId, Long nodeXYId) {
    	try {
			
			String insertQueryStatement = "insert into path_node_xy(path_id, node_xy_id values (?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"path_node_xy_id"});				
			preparedStatement.setString(1, pathId.toString());		
			preparedStatement.setString(2, nodeXYId.toString());

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