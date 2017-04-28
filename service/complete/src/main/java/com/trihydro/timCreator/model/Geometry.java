package com.trihydro.timCreator.model;

public class Geometry
{
	private String direction;
	private Integer extent;
	private Integer laneWidth;
	private Circle circle;

	public String getDirection() 
	{
		return this.direction;
	}

	public void setDirection(String direction) 
	{
		this.direction = direction;
	}

	public Integer getExtent() 
	{
		return this.extent;
	}

	public void setExtent(Integer extent) 
	{
		this.extent = extent;
	}

	public Integer getLaneWidth() 
	{
		return this.laneWidth;
	}

	public void setLaneWidth(Integer laneWidth) 
	{
		this.laneWidth = laneWidth;
	}

	public Circle getCircle() 
	{
		return this.circle;
	}

	public void setCircle(Circle circle) 
	{
		this.circle = circle;
	}
}