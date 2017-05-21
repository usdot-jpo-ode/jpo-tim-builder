package com.trihydro.timCreator.model;

public class RegionPoint {
	 private J2735Position3D position;
     private Integer scale;
     private RegionList[] regionList;
     
     public RegionList[] getRegionList() {
    	 return regionList;
     }

     public void setRegionList(RegionList[] regionList) {
         this.regionList = regionList;
     }

     public Integer getScale() {
         return scale;
     }

     public void setScale(Integer scale) {
         this.scale = scale;
     }

     public J2735Position3D getPosition() {
         return position;
     }

     public void setPosition(J2735Position3D position) {
         this.position = position;
     }
}
