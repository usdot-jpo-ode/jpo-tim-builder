package com.trihydro.timCreator.model;

import java.math.BigDecimal;

public class ComputedLane
{
	private int laneID;
	private int offsetSmallX;
	private int offsetLargeX;
	private int offsetSmallY;
	private int offsetLargeY;
	private int angle;
	private int xScale;
	private int yScale;

	public int getLaneID() {
	return laneID;
	}

	public void setLaneID(int laneID) {
		this.laneID = laneID;
	}

	public int getOffsetSmallX() {
		return offsetSmallX;
	}

	public void setOffsetSmallX(int offsetSmallX) {
		this.offsetSmallX = offsetSmallX;
	}

	public int getOffsetLargeX() {
		return offsetLargeX;
	}

	public void setOffsetLargeX(int offsetLargeX) {
		this.offsetLargeX = offsetLargeX;
	}

	public int getOffsetSmallY() {
		return offsetSmallY;
	}

	public void setOffsetSmallY(int offsetSmallY) {
		this.offsetSmallY = offsetSmallY;
	}

	public int getOffsetLargeY() {
		return offsetLargeY;
	}

	public void setOffsetLargeY(int offsetLargeY) {
		this.offsetLargeY = offsetLargeY;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getxScale() {
		return xScale;
	}

	public void setxScale(int xScale) {
		this.xScale = xScale;
	}

	public int getyScale() {
		return yScale;
	}

	public void setyScale(int yScale) {
		this.yScale = yScale;
	}
	
}