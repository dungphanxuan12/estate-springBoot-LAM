package com.laptrinhweb.builder;

public class BuildingSearchBuilder {

	private String name;
	private String ward;
	private String street;
	private String costRentFrom;
	private String costRentTo;
	private String rentAreaFrom;
	private String rentAreaTo;
	private String[] buildingTypes = new String[] {};

	public String getName() {
		return name;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public String getCostRentFrom() {
		return costRentFrom;
	}

	public String getCostRentTo() {
		return costRentTo;
	}

	public String getRentAreaFrom() {
		return rentAreaFrom;
	}

	public String getRentAreaTo() {
		return rentAreaTo;
	}

	public String[] getBuildingTypes() {
		return buildingTypes;
	}

	public BuildingSearchBuilder(Builder builder) {
		super();
		this.name = builder.name;
		this.ward = builder.ward;
		this.street = builder.street;
		this.costRentFrom = builder.costRentFrom;
		this.costRentTo = builder.costRentTo;
		this.rentAreaFrom = builder.rentAreaFrom;
		this.rentAreaTo = builder.rentAreaTo;
		this.buildingTypes = builder.buildingTypes;
	}

	public static class Builder {

		private String name;
		private String ward;
		private String street;
		private String costRentFrom;
		private String costRentTo;
		private String rentAreaFrom;
		private String rentAreaTo;
		private String[] buildingTypes = new String[] {};

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setCostRentFrom(String costRentFrom) {
			this.costRentFrom = costRentFrom;
			return this;
		}

		public Builder setCostRentTo(String costRentTo) {
			this.costRentTo = costRentTo;
			return this;
		}

		public Builder setRentAreaFrom(String rentAreaFrom) {
			this.rentAreaFrom = rentAreaFrom;
			return this;
		}

		public Builder setRentAreaTo(String rentAreaTo) {
			this.rentAreaTo = rentAreaTo;
			return this;
		}

		public Builder setBuildingTypes(String[] buildingTypes) {
			this.buildingTypes = buildingTypes;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}

	}

}
