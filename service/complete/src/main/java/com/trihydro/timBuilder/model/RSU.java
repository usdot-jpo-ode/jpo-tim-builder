package com.trihydro.timBuilder.model;

public class RSU
{
	private Integer rsuId;
	private String rsuTarget;
	private String rsuUsername;
	private String rsuPassword;
	private Double latitude;
	private Double longitude;

	public Integer getRsuId() {
		return this.rsuId;
	}

	public void setRsuId(Integer rsuId) {
		this.rsuId = rsuId;
	}

	public String getRsuTarget() {
		return this.rsuTarget;
	}

	public void setRsuTarget(String rsuTarget) {
		this.rsuTarget = rsuTarget;
	}

	public String getRsuUsername() {
		return this.rsuUsername;
	}

	public void setRsuUsername(String rsuUsername) {
		this.rsuUsername = rsuUsername;
	}

	public String getRsuPassword() {
		return this.rsuPassword;
	}

	public void setRsuPassword(String rsuPassword) {
		this.rsuPassword = rsuPassword;
	}
	
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}