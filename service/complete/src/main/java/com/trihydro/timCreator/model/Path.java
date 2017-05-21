package com.trihydro.timCreator.model;

public class Path
{
	private Integer pathId;
    private Integer scale;
    private String type;
    private NodeXY[] nodes;
    private ComputedLane computedLane;

    public Integer getPathId() {
		return this.pathId;
	}
	
	public void setPathId(Integer pathId) {
		this.pathId = pathId;
	}

	public Integer getScale() {
		return this.scale;
	}
	
	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public NodeXY[] getNodes() {
		return this.nodes;
	}
	
	public void setNodes(NodeXY[] nodes) {
		this.nodes = nodes;
	}
	
	public ComputedLane getComputedLane(){
		return computedLane;
	}
	
	public void setComputedLane(ComputedLane computedLane){
		this.computedLane = computedLane;
	}
}