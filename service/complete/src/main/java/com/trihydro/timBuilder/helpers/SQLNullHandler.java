package com.trihydro.timBuilder.helpers;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

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
			 ps.setNull(column, java.sql.Types.VARCHAR);
	 }
	 
	 public static void setBigDecimalOrNull(PreparedStatement ps, int column, BigDecimal value) throws SQLException{
		 if(value != null)			
			 ps.setBigDecimal(column, value);			
		 else
			 ps.setNull(column, java.sql.Types.NUMERIC);
	 }
	 
	 public static void setTimestampOrNull(PreparedStatement ps, int column, Timestamp value) throws SQLException{
		 if(value != null)			
			 ps.setTimestamp(column, value);			
		 else
			 ps.setNull(column, java.sql.Types.TIMESTAMP);
	 }
	 
	 public static void setShortOrNull(PreparedStatement ps, int column, Short value) throws SQLException{
		 if(value != null)			
			 ps.setShort(column, value);			
		 else
			 ps.setNull(column, java.sql.Types.NUMERIC);
	 }
	
	
}
