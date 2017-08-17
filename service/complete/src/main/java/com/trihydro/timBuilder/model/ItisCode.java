package com.trihydro.timBuilder.model;

public class ItisCode
{
	private Integer itisCodeId;
	private Integer itisCode;
	private String description;
	private Integer categoryId;

	public Integer getItisCodeId() 
	{
		return this.itisCodeId;
	}

	public void setItisCodeId(Integer itisCodeId) 
	{
		this.itisCodeId = itisCodeId;
	}

	public Integer getItisCode() 
	{
		return this.itisCode;
	}

	public void setItisCode(Integer itisCode) 
	{
		this.itisCode = itisCode;
	}

	public String getDescription() 
	{
		return this.description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public Integer getCategoryId() 
	{
		return this.categoryId;
	}
	public void setCategoryId(Integer categoryId) 
	{
		this.categoryId = categoryId;
	}
}