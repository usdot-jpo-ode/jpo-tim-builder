package com.trihydro.timCreator.model;

public class Region
{
	private Integer regionId;
	private Integer dataFrameId;
	private String name;
	private Integer regulatorID;
	private Integer segmentID;

	private J2735Position3D anchorPosition;

	private Integer laneWidth;
	private Long directionality;
	private Boolean closedPath;
	private String direction;
	private String regionType;
	private String description;

	private Integer pathId;
	private Integer oldRegionId;

	private Geometry geometry;	
	private Path path;
	private OldRegion oldRegion;

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getDataFrameId() {
		return dataFrameId;
	}

	public void setDataFrameId(Integer dataFrameId) {
		this.dataFrameId = dataFrameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRegulatorID() {
		return regulatorID;
	}

	public void setRegulatorID(Integer regulatorID) {
		this.regulatorID = regulatorID;
	}

	public Integer getSegmentID() {
		return segmentID;
	}

	public void setSegmentID(Integer segmentID) {
		this.segmentID = segmentID;
	}

	public J2735Position3D getAnchorPosition() {
		return this.anchorPosition;
	}

   	public void setAnchorPosition(J2735Position3D anchorPosition) {
		this.anchorPosition = anchorPosition;
	}

	public Integer getLaneWidth() {
		return this.laneWidth;
	}

	public void setLaneWidth(Integer laneWidth) {
		this.laneWidth = laneWidth;
	}

	public Long getDirectionality() {
	    return this.directionality;
	 }

	 public void setDirectionality(Long directionality) {
	    this.directionality = directionality;
	 }

	public Boolean getClosedPath() {
		return this.closedPath;
	}

	public void setClosedPath(Boolean closedPath) {
		this.closedPath = closedPath;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getRegionType() {
		return this.regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}     

    public Integer getPathId() {
		return this.pathId;
	}

	public void setPathId(Integer pathId) {
		this.pathId = pathId;
	}

	public Integer getOldRegionId() {
		return oldRegionId;
	}

	public void setOldRegionId(Integer oldRegionId) {
		this.oldRegionId = oldRegionId;
	}

	public Geometry getGeometry() {
		return this.geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Path getPath() {
		return this.path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public OldRegion getOldRegion() {
        return oldRegion;
    }

    public void setOldRegion(OldRegion oldRegion) {
        this.oldRegion = oldRegion;
    }

}