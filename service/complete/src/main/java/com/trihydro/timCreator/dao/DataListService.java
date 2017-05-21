package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.DataList;

public class DataListService {
	
	private Connection connection;

	public DataListService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertDataList(DataList dataList, Long nodeXYId) {
    	try {
			String insertQueryStatement = "insert into data_list(NODE_XY_ID, PATH_ENDPOINT_ANGLE, LANE_CROWN_CENTER, LANE_CROWN_LEFT, LANE_CROWN_RIGHT, LANE_ANGLE) values (?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"data_list_id"});
			
			preparedStatement.setString(1, nodeXYId.toString());	
			preparedStatement.setString(2, dataList.getPathEndpointAngle().toString());
			preparedStatement.setString(3, dataList.getLaneCrownCenter().toString());
			preparedStatement.setString(4, dataList.getLaneCrownLeft().toString());
			preparedStatement.setString(5, dataList.getLaneCrownRight().toString());
			preparedStatement.setString(6, dataList.getLaneAngle().toString());
			
			// execute insert statement
 			Long dataListId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					dataListId = generatedKeys.getLong(1);
 					System.out.println("------ Generated Data List Id: " + dataListId + " --------------");
 				}
 			}
 			
			return dataListId;

	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return new Long(0);
    }
}
