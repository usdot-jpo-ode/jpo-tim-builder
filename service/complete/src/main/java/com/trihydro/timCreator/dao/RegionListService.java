package com.trihydro.timCreator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.RegionList;;

public class RegionListService {
	
	private Connection connection;

	public RegionListService(){
		connection = DBUtility.getConnection();	
	}	

    public Long insertRegionList(RegionList regionList, Long oldRegionId) {
    	try {
    		
			String insertQueryStatement = "insert into shape_point(OLD_REGION_ID, X_OFFSET, Y_OFFSET, Z_OFFSET) values (?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"region_list_id"});
			
			preparedStatement.setString(1, oldRegionId.toString());
			preparedStatement.setString(2, regionList.getxOffset().toString());
			preparedStatement.setString(3, regionList.getyOffset().toString());
			preparedStatement.setString(4, regionList.getzOffset().toString());
		
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
