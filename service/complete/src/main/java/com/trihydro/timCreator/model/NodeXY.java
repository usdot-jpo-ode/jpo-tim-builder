package com.trihydro.timCreator.model;

public class NodeXY
{
	private Integer nodeXYId;
	private String delta;
    private Long nodeLat;
    private Long nodeLong;
    private Integer x;
    private Integer y;
    private Integer dWidth;
    private Integer dLength;
    private Attributes attributes;

    public Integer getNodeXYId() {
		return this.nodeXYId;
	}
	
	public void setNodeXYId(Integer nodeXYId) {
		this.nodeXYId = nodeXYId;
	}

	public String getDelta() {
		return this.delta;
	}
	
	public void setDelta(String delta) {
		this.delta = delta;
	}

	public Long getNodeLat() {
		return this.nodeLat;
	}
	
	public void setNodeLat(Long nodeLat) {
		this.nodeLat = nodeLat;
	}

	public Long getNodeLong() {
		return this.nodeLong;
	}
	
	public void setNodeLong(Long nodeLong) {
		this.nodeLong = nodeLong;
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

	public Integer getDWidth() {
		return this.dWidth;
	}
	
	public void setDWidth(Integer dWidth) {
		this.dWidth = dWidth;
	}

	public Integer getDLength() {
		return this.dLength;
	}
	
	public void setDLength(Integer dLength) {
		this.dLength = dLength;
	}
	
	public Attributes getAttributes() {
        return attributes;
    }
	
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

}