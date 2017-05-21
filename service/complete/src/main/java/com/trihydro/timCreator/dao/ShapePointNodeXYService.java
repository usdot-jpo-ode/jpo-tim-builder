package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ShapePointNodeXYService
{
	private Connection connection;

	public ShapePointNodeXYService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertShapePointNodeXY(Long shapePointId, Long nodeXYId) {
    	try {
			
			String insertQueryStatement = "insert into shape_point_node_xy(shape_point_id, node_xy_id values (?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"shape_point_node_xy_id"});				
			preparedStatement.setString(1, shapePointId.toString());		
			preparedStatement.setString(2, nodeXYId.toString());

			// execute insert statement
 			Long shapePointNodeXYId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					shapePointNodeXYId = generatedKeys.getLong(1);
 					System.out.println("------ Generated ShapePoint Node XY ID: " + shapePointNodeXYId + " --------------");
 				}
 			}

 			
			return shapePointNodeXYId;

	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return new Long(0);
    }

}