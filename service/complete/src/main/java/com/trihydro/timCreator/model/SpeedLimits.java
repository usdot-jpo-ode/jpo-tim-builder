package com.trihydro.timCreator.model;

public class SpeedLimits {
	private Long type;
    private Integer velocity;
    
    public Long getType() {
       return type;
    }
    public void setType(Long type) {
       this.type = type;
    }
    
    public Integer getVelocity() {
       return velocity;
    }
    
    public void setVelocity(Integer velocity) {
       this.velocity = velocity;
    }
}
