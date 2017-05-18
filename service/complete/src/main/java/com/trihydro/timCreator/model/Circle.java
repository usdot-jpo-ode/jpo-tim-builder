package com.trihydro.timCreator.model;

import java.math.BigDecimal;

public class Circle
{
	private J2735Position3D position;
	private Integer radius;
	private Integer units;

	public J2735Position3D getPosition() {
		return this.position;
	}

   	public void setPosition(J2735Position3D position) {
		this.position = position;
	}

	public Integer getRadius() {
		return this.radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public Integer getUnits() {
		return this.units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}
}