package com.trihydro.timCreator.model;

public class ShapePoint {
	
	private J2735Position3D position;
    private Integer laneWidth;
    private Integer directionality;
    private String nodeType;
    private ComputedLane computedLane;
    private NodeXY[] nodexy;
    
    public NodeXY[] getNodexy() {
       return nodexy;
    }
    
    public void setNodexy(NodeXY[] nodexy) {
       this.nodexy = nodexy;
    }
    
    public ComputedLane getComputedLane() {
       return computedLane;
    }
    
    public void setComputedLane(ComputedLane computedLane) {
       this.computedLane = computedLane;
    }
    
    public String getNodeType() {
       return nodeType;
    }
    
    public void setNodeType(String nodeType) {
       this.nodeType = nodeType;
    }
    
    public Integer getDirectionality() {
       return directionality;
    }
    
    public void setDirectionality(Integer directionality) {
       this.directionality = directionality;
    }
    
    public Integer getLaneWidth() {
       return laneWidth;
    }
    
    public void setLaneWidth(Integer laneWidth) {
       this.laneWidth = laneWidth;
    }
    
    public J2735Position3D getPosition() {
       return position;
    }
    
    public void setPosition(J2735Position3D position) {
       this.position = position;
    }
}
