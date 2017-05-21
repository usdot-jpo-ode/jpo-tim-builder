package com.trihydro.timCreator.model;

public class Attributes
{
	private Integer pathId;
	private String delta;
    private Integer x;
    private Integer y;
    private LocalNode[] localNodes;
    private DisabledList[] disabledLists;
    private EnabledList[] enabledLists;
    private DataList[] dataLists;
    
    public Integer getPathId() {
		return this.pathId;
	}
	
	public void setPathId(Integer pathId) {
		this.pathId = pathId;
	}

	public String getDelta() {
		return this.delta;
	}
	
	public void setDelta(String delta) {
		this.delta = delta;
	}

	public Integer getX() {
		return this.x;
	}
	
	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return this.y;
	}
	
	public void setY(Integer y) {
		this.y = y;
	}
	
	public LocalNode[] getLocalNodes() {
		return localNodes;
	}
	
    public void setLocalNodes(LocalNode[] localNodes) {
    	this.localNodes = localNodes;
    }
    
    public DisabledList[] getDisabledLists() {
    	return disabledLists;
    }
    
    public void setDisabledLists(DisabledList[] disabledLists) {
    	this.disabledLists = disabledLists;
    }
    
    public EnabledList[] getEnabledLists() {
    	return enabledLists;
    }
    
    public void setEnabledLists(EnabledList[] enabledLists) {
    	this.enabledLists = enabledLists;
    }
    
    public DataList[] getDataLists() {
    	return dataLists;
    }
    
    public void setDataLists(DataList[] dataLists) {
    	this.dataLists = dataLists;
    }
}