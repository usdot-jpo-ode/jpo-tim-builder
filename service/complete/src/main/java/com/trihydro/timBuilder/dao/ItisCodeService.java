package com.trihydro.timBuilder.dao;

import com.trihydro.timBuilder.helpers.DBUtility;
import com.trihydro.timBuilder.model.ItisCode;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ItisCodeService 
{	
	private DBUtility dbUtility;
	
	@Autowired
	ItisCodeService(DBUtility dbUtility) 
	{
		this.dbUtility = dbUtility;		
	}

	public List<ItisCode> selectAll(){
		List<ItisCode> itisCodes = new ArrayList<ItisCode>();
		try {
			// select all Itis Codes from ItisCode table   			
   		    Statement statement = dbUtility.getConnection().createStatement();
   			ResultSet rs = statement.executeQuery("select * from ITIS_CODE");
   			// convert to ItisCode objects   			
   			while (rs.next()) {   			
			    ItisCode itisCode = new ItisCode();
			    itisCode.setItisCodeId(rs.getInt("itis_code_id"));
			    itisCode.setItisCode(rs.getInt("itis_code"));
			    itisCode.setDescription(rs.getString("description"));    
			    itisCode.setCategoryId(rs.getInt("category_id"));
			    itisCodes.add(itisCode);
   			}
  		} 
  		catch (SQLException e) {
   			e.printStackTrace();
  		}
  		return itisCodes;
	}
}