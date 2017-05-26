package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.DataFrame;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.trihydro.timCreator.helpers.SQLNullHandler;

public class DataFrameService {
	private Connection connection;

	public DataFrameService() {
		connection = DBUtility.getConnection();
	}

	public Long insertDataFrame(DataFrame dataFrame, Long timId) {
		try {
			String insertQueryStatement = "insert into data_frame(tim_id, ssp_tim_rights, frame_type, msg_id, further_info_id, position_lat, position_long, position_elev, view_angle, mutcd, crc, start_date_time, duration_time, priority, ssp_location_rights, ssp_msg_types, ssp_msg_content, content, url) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement,
					new String[] { "data_frame_id" });

			SQLNullHandler.setLongOrNull(preparedStatement, 1, timId);
			SQLNullHandler.setShortOrNull(preparedStatement, 2, dataFrame.getSspTimRights());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 3, dataFrame.getFrameType());
			SQLNullHandler.setStringOrNull(preparedStatement, 4, dataFrame.getMsgID());
			SQLNullHandler.setStringOrNull(preparedStatement, 5, dataFrame.getFurtherInfoID());
			
			if( dataFrame.getPosition() != null){
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 6, dataFrame.getPosition().getLatitude());
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 7, dataFrame.getPosition().getLongitude());
				SQLNullHandler.setBigDecimalOrNull(preparedStatement, 8, dataFrame.getPosition().getElevation());
			}
			else{
				preparedStatement.setNull(6, java.sql.Types.NUMERIC);
				preparedStatement.setNull(7, java.sql.Types.NUMERIC);
				preparedStatement.setNull(8, java.sql.Types.NUMERIC);
			}
				
			SQLNullHandler.setStringOrNull(preparedStatement, 9, dataFrame.getViewAngle());
			SQLNullHandler.setIntegerOrNull(preparedStatement, 10, dataFrame.getMutcd());
			SQLNullHandler.setStringOrNull(preparedStatement, 11, dataFrame.getCrc());		
			if(dataFrame.getStartDateTime() != null)
				SQLNullHandler.setTimestampOrNull(preparedStatement, 12, java.sql.Timestamp.valueOf(LocalDateTime.parse(dataFrame.getStartDateTime(), DateTimeFormatter.ISO_DATE_TIME)));
			else
				preparedStatement.setNull(12, java.sql.Types.TIMESTAMP);
		    SQLNullHandler.setIntegerOrNull(preparedStatement, 13, dataFrame.getDurationTime());
		    SQLNullHandler.setIntegerOrNull(preparedStatement, 14, dataFrame.getPriority());
		    SQLNullHandler.setShortOrNull(preparedStatement, 15, dataFrame.getSspLocationRights());
		    SQLNullHandler.setShortOrNull(preparedStatement, 16, dataFrame.getSspMsgTypes());
		    SQLNullHandler.setShortOrNull(preparedStatement, 17, dataFrame.getSspMsgContent());
		    SQLNullHandler.setStringOrNull(preparedStatement, 18, dataFrame.getContent());
		    SQLNullHandler.setStringOrNull(preparedStatement, 19, dataFrame.getUrl());

			// execute insert statement
			Long dataFrameId = null;

			if (preparedStatement.executeUpdate() > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

				if (generatedKeys != null && generatedKeys.next()) {
					dataFrameId = generatedKeys.getLong(1);
					System.out.println("------ Generated data frame ID: " + dataFrameId + " --------------");
				}
			}
			return dataFrameId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Long(0);
	}
}