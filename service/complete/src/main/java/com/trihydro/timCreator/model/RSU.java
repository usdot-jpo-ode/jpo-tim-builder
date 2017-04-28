package com.trihydro.timCreator.model;

public class RSU
{
	private Integer rsuId;
	private String rsuTarget;
	private String rsuUsername;
	private String rsuPassword;
	private String snmpUsername;
	private String snmpPassword;

	public Integer getRsuId() 
	{
		System.out.println("**** get *****");
		return this.rsuId;
	}

	public void setRsuId(Integer rsuId) 
	{
		System.out.println("**** set *****");
		this.rsuId = rsuId;
	}

	public String getRsuTarget() 
	{
		return this.rsuTarget;
	}

	public void setRsuTarget(String rsuTarget) 
	{
		this.rsuTarget = rsuTarget;
	}

	public String getRsuUsername() 
	{
		return this.rsuUsername;
	}

	public void setRsuUsername(String rsuUsername) 
	{
		this.rsuUsername = rsuUsername;
	}

	public String getRsuPassword() 
	{
		return this.rsuPassword;
	}

	public void setRsuPassword(String rsuPassword) 
	{
		this.rsuPassword = rsuPassword;
	}

	public String getSnmpUsername() 
	{
		return this.snmpUsername;
	}

	public void setSnmpUsername(String snmpUsername) 
	{
		this.snmpUsername = snmpUsername;
	}

	public String getSnmpPassword() 
	{
		return this.snmpPassword;
	}

	public void setSnmpPassword(String snmpPassword) 
	{
		this.snmpPassword = snmpPassword;
	}
}