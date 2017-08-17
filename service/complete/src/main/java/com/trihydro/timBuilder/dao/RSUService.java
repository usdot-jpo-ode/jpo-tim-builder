package com.trihydro.timBuilder.dao;

import com.trihydro.timBuilder.helpers.DBUtility;
import com.trihydro.timBuilder.model.RSU;
import com.trihydro.timBuilder.helpers.SQLNullHandler;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class RSUService
{
	private DBUtility dbUtility;

	@Autowired
	RSUService(DBUtility dbUtility) 
	{
		this.dbUtility = dbUtility;		
	}

	public List<RSU> selectAll(){
		List<RSU> rsus = new ArrayList<RSU>();
		try {
			// select all RSUs from RSU table
   		    Statement statement = dbUtility.getConnection().createStatement();
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