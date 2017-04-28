package com.trihydro.timCreator.model;

import java.math.BigDecimal;

public class DataFrame
{
	private Integer dataFrameId;
	private Integer timId;
	private short sspTimRights;// Start Header Information
	private Integer frameType;
	private String msgID;
	private String furtherInfoID;
	private J2735Position3D position;	
	private String viewAngle;
	private Integer mutcd;
	private String crc;
	private String startDateTime;
	private Integer durationTime;
	private Integer priority;// End header Information
	private short sspLocationRights;// Start Region Information
	private short sspMsgTypes;// Start content Information
	private short sspMsgContent;
	private String content;
	private String url;// End content Information
	private Region[] regions;

	public Integer getDataFrameId() {
		return dataFrameId;
	}

	public void setDataFrameId(Integer dataFrameId) {
		this.dataFrameId = dataFrameId;
	}

	public Integer getTimId() {
		return timId;
	}

	public void setTimId(Integer timId) {
		this.timId = timId;
	}

	public short getsspTimRights() {
		return sspTimRights;
	}
      
    public void setsspTimRights(short sspTimRights) {
		this.sspTimRights = sspTimRights;
	}

	public Integer getframeType() {
		return frameType;
	}

	public void setframeType(Integer frameType) {
		this.frameType = frameType;
	}

	public String getmsgID() {
		return msgID;
	}

    public void setmsgID(String msgID) {
		this.msgID = msgID;
	}

	public String getfurtherInfoID() {
		return furtherInfoID;
	}

	public void setfurtherInfoID(String furtherInfoID) {
		this.furtherInfoID = furtherInfoID;
	}

	public J2735Position3D getposition() {
		return position;
	}

	public void setposition(J2735Position3D position) {
		this.position = position;
	}

	public String getviewAngle() {
		return viewAngle;
	}

	public void setviewAngle(String viewAngle) {
		this.viewAngle = viewAngle;
	}

	public Integer getmutcd() {
		return mutcd;
	}

	public void setmutcd(Integer mutcd) {
		this.mutcd = mutcd;
	}

	public String getcrc() {
		return crc;
	}

	public void setcrc(String crc) {
		this.crc = crc;
	}

	public String getstartDateTime() {
		return startDateTime;
	}

	public void setstartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Integer getdurationTime() {
		return durationTime;
	}

	public void setdurationTime(Integer durationTime) {
		this.durationTime = durationTime;
	}

	public Integer getpriority() {
		return priority;
	}

	public void setpriority(Integer priority) {
		this.priority = priority;
	}  

	public short getsspLocationRights() {
		return sspLocationRights;
	}

	public void setsspLocationRights(short sspLocationRights) {
		this.sspLocationRights = sspLocationRights;
	}  

	public short getsspMsgTypes() {
		return sspMsgTypes;
	}

	public void setsspMsgTypes(short sspMsgTypes) {
		this.sspMsgTypes = sspMsgTypes;
	}    

	public short getsspMsgContent() {
		return sspMsgContent;
	}

	public void setsspMsgContent(short sspMsgContent) {
		this.sspMsgContent = sspMsgContent;
	}

	public String getcontent() {
		return content;
	}

	public void setcontent(String content) {
		this.content = content;
	}

	public String geturl() {
		return url;
	}

	public void seturl(String url) {
		this.url = url;
	}        

	public Region[] getRegions() {
		return this.regions;
	}             

	public void setRegions(Region[] regions) {
		this.regions = regions;
	} 
}