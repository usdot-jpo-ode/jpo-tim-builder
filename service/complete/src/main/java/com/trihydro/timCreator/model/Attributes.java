package com.trihydro.timCreator.model;

public class Attributes
{
    private LocalNode[] localNodes;
    private DisabledList[] disabledLists;
    private EnabledList[] enabledLists;
    private DataList[] dataLists;
    private Integer dWidth;
    private Integer dElevation;
    
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
    
    public Integer getdWidth() {
 		return this.dWidth;
 	}
 	
 	public void setdWidth(Integer dWidth) {
 		this.dWidth = dWidth;
 	}
 	
    public Integer getdElevation() {
 		return this.dElevation;
 	}
 	
 	public void setdElevation(Integer dElevation) {
 		this.dElevation = dElevation;
 	}
    
    
}