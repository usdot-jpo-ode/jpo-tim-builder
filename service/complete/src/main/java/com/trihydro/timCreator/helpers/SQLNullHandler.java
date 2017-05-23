package com.trihydro.timCreator.helpers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLNullHandler {

	 public static void setLongOrNull(PreparedStatement ps, int column, Long value) throws SQLException{
		 if(value != null)			
			 ps.setLong(column, value);			
		 else
			 ps.setNull(column, java.sql.Types.NUMERIC);
	 }
	 
	 public static void setIntegerOrNull(PreparedStatement ps, int column, Integer value) throws SQLException{
		 if(value != null)			
			 ps.setLong(column, value);			
		 else
			 ps.setNull(column, java.sql.Types.NUMERIC);
	 }
	 
	 public static void setStringOrNull(PreparedStatement ps, int column, String value) throws SQLException{
		 if(value != null)			
			 ps.setString(column, value);			
		 else
			 ps.setNull(column, java.sql.Types.NUMERIC);
	 }
	
	
}
