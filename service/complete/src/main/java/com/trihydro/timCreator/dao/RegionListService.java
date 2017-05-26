package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.RegionList;
import com.trihydro.timCreator.helpers.SQLNullHandler;

public class RegionListService {
	
	private Connection connection;

	public RegionListService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertRegionList(RegionList regionList, Long oldRegionId) {
    	try {
    		
			String insertQueryStatement = "insert into region_list(OLD_REGION_ID, X_OFFSET, Y_OFFSET, Z_OFFSET) values (?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"region_list_id"});
			
			SQLNullHandler.setLongOrNull(preparedStatement, 1, oldRegionId);
			SQLNullHandler.setIntegerOrNull(preparedStatement, 2, regionList.getxOffset());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 3, regionList.getyOffset());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 4, regionList.getzOffset());
		
			// execute insert statement
 			Long regionListId = null;

 			if(preparedStatement.executeUpdate() > 0){
 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

 				if(generatedKeys != null && generatedKeys.next()){
 					regionListId = generatedKeys.getLong(1);
 					System.out.println("------ Generated RegionList Id: " + regionListId + " --------------");
 				}
 			}
 			
			return regionListId;

	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	  return new Long(0);
    }
}
