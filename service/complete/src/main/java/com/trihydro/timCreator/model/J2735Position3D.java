package com.trihydro.timCreator.model;

import java.math.BigDecimal;

public class J2735Position3D
{
	private BigDecimal latitude; // in degrees
    private BigDecimal longitude; // in degrees
    private BigDecimal elevation; // in meters

	public BigDecimal getLatitude() {
		return latitude;
	}

   	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getElevation() {
		return elevation;
	}

	public void setElevation(BigDecimal elevation) {
		this.elevation = elevation;
	}
}