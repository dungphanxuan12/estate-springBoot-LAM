package com.laptrinhweb.dto;

public class RenAreaDTO extends AbstractDTO<RenAreaDTO> {

	private String value;
	private Long buildingId;

	public RenAreaDTO() {
		super();
	}

	public RenAreaDTO(String value, Long buildingId) {
		super();
		this.value = value;
		this.buildingId = buildingId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

}
