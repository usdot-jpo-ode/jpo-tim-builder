package com.trihydro.timCreator.model;

public class TIMRSU
{
	private Integer timRsuId;
	private Integer rsuId;
	private Integer timId;
	private String dateSent;
	private String dateReceived;

	public Integer getTimRsuId() 
	{
		return this.timRsuId;
	}

	public void setTimRsuId(Integer timRsuId) 
	{
		this.timRsuId = timRsuId;
	}

	public Integer getRsuId() 
	{
		return this.rsuId;
	}

	public void setRsuId(Integer rsuId) 
	{
		this.rsuId = rsuId;
	}

	public Integer getTimId() 
	{
		return this.timId;
	}

	public void setTimId(Integer timId) 
	{
		this.timId = timId;
	}

	public String getDateSent() 
	{
		return this.dateSent;
	}

	public void setDateSent(String dateSent) 
	{
		this.dateSent = dateSent;
	}

	public String getDateReceived() 
	{
		return this.dateReceived;
	}

	public void setDateReceived(String dateReceived) 
	{
		this.dateReceived = dateReceived;
	}
}