package com.trihydro.timCreator.dao;

import com.trihydro.timCreator.DBUtility;
import com.trihydro.timCreator.model.DataFrame;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

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

			preparedStatement.setString(1, String.valueOf(timId));
			preparedStatement.setString(2, String.valueOf(dataFrame.getSspTimRights()));
			preparedStatement.setString(3, String.valueOf(dataFrame.getFrameType()));
			preparedStatement.setString(4, dataFrame.getMsgID());
			preparedStatement.setString(5, dataFrame.getFurtherInfoID());
			preparedStatement.setString(6, String.valueOf(dataFrame.getPosition().getLatitude()));
			preparedStatement.setString(7, String.valueOf(dataFrame.getPosition().getLongitude()));
			preparedStatement.setString(8, String.valueOf(dataFrame.getPosition().getElevation()));
			preparedStatement.setString(9, dataFrame.getViewAngle());
			preparedStatement.setString(10, String.valueOf(dataFrame.getMutcd()));
			preparedStatement.setString(11, dataFrame.getCrc());			
			preparedStatement.setTimestamp(12, java.sql.Timestamp.valueOf(LocalDateTime.parse(dataFrame.getStartDateTime(), DateTimeFormatter.ISO_DATE_TIME)));
			preparedStatement.setString(13, String.valueOf(dataFrame.getDurationTime()));
			preparedStatement.setString(14, String.valueOf(dataFrame.getPriority()));
			preparedStatement.setString(15, String.valueOf(dataFrame.getSspLocationRights()));
			preparedStatement.setString(16, String.valueOf(dataFrame.getSspMsgTypes()));
			preparedStatement.setString(17, String.valueOf(dataFrame.getSspMsgContent()));
			preparedStatement.setString(18, dataFrame.getContent());
			preparedStatement.setString(19, dataFrame.getUrl());

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