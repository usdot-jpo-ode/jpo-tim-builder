package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.RSU;
import com.trihydro.timCreator.helpers.SQLNullHandler;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class RSUService
{
	private Connection connection;

	public RSUService(){
		connection = DBUtility.getConnection();	
	}

	public List<RSU> selectAll(){
		List<RSU> rsus = new ArrayList<RSU>();
		try {
			// select all RSUs from RSU table
   		    Statement statement = connection.createStatement();
   			ResultSet rs = statement.executeQuery("select * from RSU");
   			while (rs.next()) {
			    RSU rsu = new RSU();
			    rsu.setRsuId(rs.getInt("rsu_id"));
			    rsu.setRsuTarget(rs.getString("url"));
			    rsu.setRsuUsername(rs.getString("rsu_username"));    
			    rsu.setRsuPassword(rs.getString("rsu_password"));
			    rsu.setLatitude(rs.getDouble("latitude"));
			    rsu.setLongitude(rs.getDouble("longitude"));
			    rsus.add(rsu);
   			}
  		} 
  		catch (SQLException e) {
   			e.printStackTrace();
  		}
  		return rsus;
	}
}