package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.helpers.DBUtility;
import com.trihydro.timCreator.model.Milepost;
import com.trihydro.timCreator.helpers.SQLNullHandler;

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
public class MilepostService
{
	private DBUtility dbUtility;

	@Autowired
	MilepostService(DBUtility dbUtility) 
	{
		this.dbUtility = dbUtility;		
	}

	public List<Milepost> selectAll(){
		List<Milepost> mileposts = new ArrayList<Milepost>();
		try {
			// select all Mileposts from Milepost table
   		    Statement statement = dbUtility.getConnection().createStatement();
   			ResultSet rs = statement.executeQuery("select * from MILEPOST where MOD(milepost, 1) = 0 order by milepost asc");
   			while (rs.next()) {   				
			    Milepost milepost = new Milepost();
	    	    milepost.setRoute(rs.getString("route"));
	    	    milepost.setMilepost(rs.getDouble("milepost"));
			    milepost.setDirection(rs.getString("direction"));	
			    milepost.setLatitude(rs.getDouble("latitude"));
			    milepost.setLongitude(rs.getDouble("longitude"));
			    milepost.setElevation(rs.getDouble("elevation_ft"));	
			    milepost.setBearing(rs.getDouble("bearing"));
			    mileposts.add(milepost);
   			}
  		} 
  		catch (SQLException e) {
   			e.printStackTrace();
  		}
  		return mileposts;
	}	

	public List<Milepost> selectMilepostRange(String direction, Integer startingMilepost, Integer endingMilepost){
		List<Milepost> mileposts = new ArrayList<Milepost>();
		try {
			// select all Mileposts from Milepost table
   		    Statement statement = dbUtility.getConnection().createStatement();
   			ResultSet rs = statement.executeQuery("select * from MILEPOST where direction = '" + direction + "' and milepost >= " + startingMilepost + " and milepost <= "+ endingMilepost + " order by milepost asc");
   			while (rs.next()) {   				
			    Milepost milepost = new Milepost();
	    	    milepost.setRoute(rs.getString("route"));
	    	    milepost.setMilepost(rs.getDouble("milepost"));
			    milepost.setDirection(rs.getString("direction"));	
			    milepost.setLatitude(rs.getDouble("latitude"));
			    milepost.setLongitude(rs.getDouble("longitude"));
			    milepost.setElevation(rs.getDouble("elevation_ft"));	
			    milepost.setBearing(rs.getDouble("bearing"));
			    mileposts.add(milepost);
   			}
  		} 
  		catch (SQLException e) {
   			e.printStackTrace();
  		}
  		return mileposts;
	}	
}
