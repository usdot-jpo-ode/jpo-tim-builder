package com.trihydro.timBuilder.dao;

import com.trihydro.timBuilder.helpers.DBUtility;
import com.trihydro.timBuilder.model.Category;

import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CategoryService 
{	
	private DBUtility dbUtility;
	
	@Autowired
	CategoryService(DBUtility dbUtility) {
		this.dbUtility = dbUtility;		
	}

	// select all ITIS Codes from the database
	public List<Category> selectAll() {

		List<Category> categories = new ArrayList<Category>();
		
		try {
			// build SQL statement
   		    Statement statement = dbUtility.getConnection().createStatement();
   			ResultSet rs = statement.executeQuery("select * from CATEGORY");
   			// convert to Category objects   			
   			while (rs.next()) {   			
			    Category category = new Category();
			    category.setCategoryId(rs.getInt("category_id"));
			    category.setCategory(rs.getString("category"));			   
			    categories.add(category);
   			}
  		} 
  		catch (SQLException e) {
   			e.printStackTrace();
  		}
  		return categories;
	}
}