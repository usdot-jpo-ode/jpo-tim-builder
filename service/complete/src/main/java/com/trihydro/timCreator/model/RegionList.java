package com.trihydro.timCreator.model;

public class RegionList {
	private Integer xOffset;
    private Integer yOffset;
    private Integer zOffset;
    
    public Integer getzOffset() {
       return zOffset;
    }
    
    public void setzOffset(Integer zOffset) {
       this.zOffset = zOffset;
    }
    
    public Integer getyOffset() {
       return yOffset;
    }
    
    public void setyOffset(Integer yOffset) {
       this.yOffset = yOffset;
    }
    
    public Integer getxOffset() {
       return xOffset;
    }
    
    public void setxOffset(Integer xOffset) {
       this.xOffset = xOffset;
    }
}
