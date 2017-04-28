package com.trihydro.timCreator.model;

public class SNMP
{
	private String rsuId;
	private Integer msgId;
	private Integer mode;
	private Integer channel;
	private Integer interval;	
    private String deliveryStart;
    private String deliveryStop;
    private Integer enable;
    private Integer status;

	public String getRsuId() 
	{
		System.out.println("get rsu id " + this.rsuId);
		return this.rsuId;
	}

	public void setRsuid(String rsuid) 
	{
		System.out.println("set rsu id " + rsuid );
		this.rsuId = rsuid;
	}

	public Integer getMsgId() 
	{
		System.out.println("set mg id");
		return this.msgId;
	}

	public void setMsgid(Integer msgid) 
	{

		System.out.println("set mg id");
		this.msgId = msgid;
	}

	public Integer getMode() 
	{
		return this.mode;
	}

	public void setMode(Integer mode) 
	{
		this.mode = mode;
	}

	public Integer getChannel() 
	{
		return this.channel;
	}

	public void setChannel(Integer channel) 
	{
		this.channel = channel;
	}

	public Integer getInterval() 
	{
		return this.interval;
	}

	public void setInterval(Integer interval) 
	{
		this.interval = interval;
	}

	public String getDeliveryStart() 
	{
		return this.deliveryStart;
	}

	public void setDeliveryStart(String deliverystart) 
	{
		this.deliveryStart = deliverystart;
	}

	public String getDeliveryStop() 
	{
		return this.deliveryStop;
	}

	public void setDeliveryStop(String deliverystop) 
	{
		this.deliveryStop = deliverystop;
	}

	public Integer getEnable() 
	{
		return this.enable;
	}

	public void setEnable(Integer enable) 
	{
		this.enable = enable;
	}

	public Integer getStatus() 
	{
		return this.status;
	}

	public void setStatus(Integer status) 
	{
		this.status = status;
	}
}