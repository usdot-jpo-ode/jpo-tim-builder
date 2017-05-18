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
	private String[] items;
	private ItisCode[] itisCodes;

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

	public short getSspTimRights() {
		return sspTimRights;
	}
      
    public void setSspTimRights(short sspTimRights) {
		this.sspTimRights = sspTimRights;
	}

	public Integer getFrameType() {
		return frameType;
	}

	public void setFrameType(Integer frameType) {
		this.frameType = frameType;
	}

	public String getMsgID() {
		return msgID;
	}

    public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public String getFurtherInfoID() {
		return furtherInfoID;
	}

	public void setFurtherInfoID(String furtherInfoID) {
		this.furtherInfoID = furtherInfoID;
	}

	public J2735Position3D getPosition() {
		return position;
	}

	public void setPosition(J2735Position3D position) {
		this.position = position;
	}

	public String getViewAngle() {
		return viewAngle;
	}

	public void setViewAngle(String viewAngle) {
		this.viewAngle = viewAngle;
	}

	public Integer getMutcd() {
		return mutcd;
	}

	public void setMutcd(Integer mutcd) {
		this.mutcd = mutcd;
	}

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public String getStartDateTime() {
		System.out.println("------ get startDateTime");
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		System.out.println("------ set startDateTime " + startDateTime);
		this.startDateTime = startDateTime;
	}

	public Integer getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(Integer durationTime) {
		this.durationTime = durationTime;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}  

	public short getSspLocationRights() {
		return sspLocationRights;
	}

	public void setSspLocationRights(short sspLocationRights) {
		this.sspLocationRights = sspLocationRights;
	}  

	public short getSspMsgTypes() {
		return sspMsgTypes;
	}

	public void setSspMsgTypes(short sspMsgTypes) {
		this.sspMsgTypes = sspMsgTypes;
	}    

	public short getSspMsgContent() {
		return sspMsgContent;
	}

	public void setSspMsgContent(short sspMsgContent) {
		this.sspMsgContent = sspMsgContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}        

	public Region[] getRegions() {
		return this.regions;
	}             

	public void setRegions(Region[] regions) {
		this.regions = regions;
	} 

	public String[] getItems() {
		return this.items;
	}             

	public void setItems(String[] items) {
		this.items = items;
	} 

	public ItisCode[] getItisCodes() {
		return this.itisCodes;
	}             

	public void setItisCodes(ItisCode[] itisCodes) {
		this.itisCodes = itisCodes;
	} 

}