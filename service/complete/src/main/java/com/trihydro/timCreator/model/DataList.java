package com.trihydro.timCreator.model;

public class DataList {
	private Integer pathEndpointAngle;
    private Integer laneCrownCenter;
    private Integer laneCrownLeft;
    private Integer laneCrownRight;
    private Integer laneAngle;
    private SpeedLimits[] speedLimits;
    
    public Integer getPathEndpointAngle() {
       return pathEndpointAngle;
    }
    
    public void setPathEndpointAngle(Integer pathEndpointAngle) {
       this.pathEndpointAngle = pathEndpointAngle;
    }
    
    public Integer getLaneCrownCenter() {
       return laneCrownCenter;
    }
    
    public void setLaneCrownCenter(Integer laneCrownCenter) {
       this.laneCrownCenter = laneCrownCenter;
    }
    
    public Integer getLaneCrownLeft() {
       return laneCrownLeft;
    }
    
    public void setLaneCrownLeft(Integer laneCrownLeft) {
       this.laneCrownLeft = laneCrownLeft;
    }
    
    public Integer getLaneCrownRight() {
       return laneCrownRight;
    }
    
    public void setLaneCrownRight(Integer laneCrownRight) {
       this.laneCrownRight = laneCrownRight;
    }
    
    public Integer getLaneAngle() {
       return laneAngle;
    }
    
    public void setLaneAngle(Integer laneAngle) {
       this.laneAngle = laneAngle;
    }
    
    public SpeedLimits[] getSpeedLimits() {
       return speedLimits;
    }
    
    public void setSpeedLimits(SpeedLimits[] speedLimits) {
       this.speedLimits = speedLimits;
    }
    
}
