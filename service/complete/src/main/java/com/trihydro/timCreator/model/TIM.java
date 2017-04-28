package com.trihydro.timCreator.model;

public class TIM
{
	private Integer timId;
	private Integer msgCnt;
	private String timeStamp;
	private String packetID;
	private String urlB;
	private DataFrame dataframes[];

	public Integer getTimId() 
	{
		return this.timId;
	}

	public void setTimId(Integer timId) 
	{
		this.timId = timId;
	}

	public Integer getMsgCnt() 
	{
		return this.msgCnt;
	}

	public void setMsgCnt(Integer msgCnt) 
	{
		this.msgCnt = msgCnt;
	}

	public String getTimeStamp() 
	{
		return this.timeStamp;
	}

	public void setTimeStamp(String timeStamp) 
	{
		System.out.println("set timeStamp: " + timeStamp);
		this.timeStamp = timeStamp;
	}

	public String getPacketID() 
	{
		return this.packetID;
	}

	public void setPacketID(String packetID) 
	{
		this.packetID = packetID;
	}

	public String getUrlB() 
	{
		return this.urlB;
	}

	public void setUrlB(String urlB) 
	{
		this.urlB = urlB;
	}

	public DataFrame[] getdataframes() 
	{
		return this.dataframes;
	}

	// public void setDataFrames(String urlB) 
	// {
	// 	this.urlB = urlB;
	// }
}