package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.DataFrame;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class DataFrameItisCodeService
{
	private Connection connection;

	public DataFrameItisCodeService(){
		connection = DBUtility.getConnection();	
	}

    public void insertDataFrameItisCode(Long dataFrameId, DataFrame dataFrame) {
    	try {
			
			for(int i = 0; i < dataFrame.getItisCodes().length; i++) {

				String insertQueryStatement = "insert into data_frame_itis_code(itis_code_id, data_frame_id) values (?,?)";

				PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement, new String[] {"data_frame_itis_code_id"});

				
				System.out.println("------ Generated data frame itis code ID " + dataFrame.getItisCodes()[i].getItisCodeId().toString());
				preparedStatement.setString(1, dataFrame.getItisCodes()[i].getItisCodeId().toString());			
				preparedStatement.setString(2, dataFrameId.toString());			

				// execute insert statement
	 			Long dataFrameItisCodeId = null;

	 			if(preparedStatement.executeUpdate() > 0){
	 				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

	 				if(generatedKeys != null && generatedKeys.next()){
	 					dataFrameItisCodeId = generatedKeys.getLong(1);
	 					System.out.println("------ Generated data frame itis code ID: " + dataFrameItisCodeId + " --------------");
	 				}
	 			}
	 		}
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
    }

}