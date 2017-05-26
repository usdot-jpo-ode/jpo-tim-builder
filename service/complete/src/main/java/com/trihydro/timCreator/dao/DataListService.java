package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.DataList;
import com.trihydro.timCreator.helpers.SQLNullHandler;

public class DataListService {
	
	private Connection connection;

	public DataListService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertDataList(DataList dataList, Long nodeXYId) {
    	try {
			String insertQueryStatement = "insert into data_list(NODE_XY_ID, PATH_ENDPOINT_ANGLE, LANE_CROWN_CENTER, LANE_CROWN_LEFT, LANE_CROWN_RIGHT, LANE_ANGLE) values (?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"data_list_id"});
			
			SQLNullHandler.setLongOrNull(preparedStatement, 1, nodeXYId);
			SQLNullHandler.setIntegerOrNull(preparedStatement, 2, dataList.getPathEndpointAngle());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 3, dataList.getLaneCrownCenter());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 4, dataList.getLaneCrownLeft());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 5, dataList.getLaneCrownRight());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 6, dataList.getLaneAngle());
			
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
