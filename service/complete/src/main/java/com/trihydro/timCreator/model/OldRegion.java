package com.trihydro.timCreator.model;

public class OldRegion {
	 private String direction;
     private Integer extent;
     private String area;
     private ShapePoint shapePoint;
     private Circle circle;
     private RegionPoint regionPoint;
     
     public RegionPoint getRegionPoint() {
    	 return regionPoint;
     }

     public void setRegionPoint(RegionPoint regionPoint) {
    	 this.regionPoint = regionPoint;
     }

	 public Circle getCircle() {
	     return circle;
	 }
	
	 public void setCircle(Circle circle) {
	     this.circle = circle;
	 }
	
	 public ShapePoint getShapePoint() {
	     return shapePoint;
	 }
	
	 public void setShapepoint(ShapePoint shapePoint) {
	     this.shapePoint = shapePoint;
	 }
	
	 public String getArea() {
	     return area;
	 }
	
	 public void setArea(String area) {
	     this.area = area;
	 }
	
	 public Integer getExtent() {
	     return extent;
	 }
	
	 public void setExtent(Integer extent) {
	     this.extent = extent;
	 }
	
	 public String getDirection() {
	     return direction;
	 }
	
	 public void setDirection(String direction) {
	     this.direction = direction;
	 }
}
