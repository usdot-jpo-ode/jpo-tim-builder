package com.trihydro.timCreator.model;

public class MileMarker
{
    private String route;
    private Double milepost;
    private String direction;
    private Double latitude;
    private Double longitude;
    private Double elevation;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Double getMilepost() {
        return milepost;
    }

    public void setMilepost(Double milepost) {
        this.milepost = milepost;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }
}