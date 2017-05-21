package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.model.Path;
import com.trihydro.timCreator.DBUtility;
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
			
			preparedStatement.setString(1, path.getScale().toString());		
			preparedStatement.setString(2, path.getType());
			preparedStatement.setString(3, computedLaneId.toString());

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