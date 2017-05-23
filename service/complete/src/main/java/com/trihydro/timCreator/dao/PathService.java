package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.helpers.SQLNullHandler;
import com.trihydro.timCreator.model.Path;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class PathService
{
	private Connection connection;

	public PathService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertPath(Path path, Long computedLaneId) {
    	try {
			
			String insertQueryStatement = "insert into path(scale, type, computed_lane_id) values (?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"path_id"});
			
			SQLNullHandler.setIntegerOrNull(preparedStatement, 1, path.getScale());
			SQLNullHandler.setStringOrNull(preparedStatement, 2, path.getType());
			SQLNullHandler.setLongOrNull(preparedStatement, 3, computedLaneId);

			// execute insert statement
 			Long pathId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					pathId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Path ID: " + pathId + " --------------");
 				}
 			}
 			
			return pathId;

	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return new Long(0);
    }
}