package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.MileMarker;
import com.trihydro.timCreator.helpers.SQLNullHandler;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class MileMarkerService
{
	private Connection connection;

	public MileMarkerService(){
		connection = DBUtility.getConnection();	
	}

	public List<MileMarker> selectAll(){
		List<MileMarker> mileMarkers = new ArrayList<MileMarker>();
		try {
			// select all Mile Markers from MileMarkers table
   		    Statement statement = connection.createStatement();
   			ResultSet rs = statement.executeQuery("select * from MILEPOST_VW where MOD(milepost, 1) = 0 order by milepost asc");
   			while (rs.next()) {   				
			    MileMarker mileMarker = new MileMarker();
	    	    mileMarker.setRoute(rs.getString("route"));
	    	    mileMarker.setMilepost(rs.getDouble("milepost"));
			    mileMarker.setDirection(rs.getString("direction"));	
			    mileMarker.setLatitude(rs.getDouble("latitude"));
			    mileMarker.setLongitude(rs.getDouble("longitude"));
			    mileMarker.setElevation(rs.getDouble("elevation_ft"));	
			    mileMarkers.add(mileMarker);
   			}
  		} 
  		catch (SQLException e) {
   			e.printStackTrace();
  		}
  		return mileMarkers;
	}	

	public List<MileMarker> selectMileMarkerRange(String direction, Integer startingMilepost, Integer endingMilepost){
		List<MileMarker> mileMarkers = new ArrayList<MileMarker>();
		try {
			// select all Mile Markers from MileMarkers table
   		    Statement statement = connection.createStatement();
   			ResultSet rs = statement.executeQuery("select * from MILEPOST_VW where direction = '" + direction + "' and milepost >= " + startingMilepost + " and milepost <= "+ endingMilepost + " order by milepost asc");
   			while (rs.next()) {   				
			    MileMarker mileMarker = new MileMarker();
	    	    mileMarker.setRoute(rs.getString("route"));
	    	    mileMarker.setMilepost(rs.getDouble("milepost"));
			    mileMarker.setDirection(rs.getString("direction"));	
			    mileMarker.setLatitude(rs.getDouble("latitude"));
			    mileMarker.setLongitude(rs.getDouble("longitude"));
			    mileMarker.setElevation(rs.getDouble("elevation_ft"));	
			    mileMarkers.add(mileMarker);
   			}
  		} 
  		catch (SQLException e) {
   			e.printStackTrace();
  		}
  		return mileMarkers;
	}	
}
