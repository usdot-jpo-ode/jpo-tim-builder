package com.trihydro.timBuilder.dao;

import com.trihydro.timBuilder.helpers.DBUtility;
import com.trihydro.timBuilder.model.Milepost;

import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MilepostService
{
	private DBUtility dbUtility;

	@Autowired
	MilepostService(DBUtility dbUtility) {
		this.dbUtility = dbUtility;		
	}

	// select all mileposts
	public List<Milepost> selectAll() {

		List<Milepost> mileposts = new ArrayList<Milepost>();

		try {
			// build statement SQL query
   		    Statement statement = dbUtility.getConnection().createStatement();
   			ResultSet rs = statement.executeQuery("select * from MILEPOST where MOD(milepost, 1) = 0 order by milepost asc");
   			// convert result to milepost objects
   			while (rs.next()) {   				
			    Milepost milepost = new Milepost();
			    milepost.setMilepostId(rs.getInt("milepost_id"));
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

	// select all mileposts within a range in one direction
	public List<Milepost> selectMilepostRange(String direction, Integer startingMilepost, Integer endingMilepost) {

		List<Milepost> mileposts = new ArrayList<Milepost>();
		
		try {
			// build SQL query
   		    Statement statement = dbUtility.getConnection().createStatement();
   			ResultSet rs = statement.executeQuery("select * from MILEPOST where direction = '" + direction + "' and milepost >= " + startingMilepost + " and milepost <= "+ endingMilepost + " order by milepost asc");
   			// convert result to milepost objects
   			while (rs.next()) {   				
			    Milepost milepost = new Milepost();
			    milepost.setMilepostId(rs.getInt("milepost_id"));
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
