package com.laptrinhweb.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.api.output.building.TotalItem;
import com.laptrinhweb.builder.BuildingSearchBuilder;
import com.laptrinhweb.dto.BuildingDTO;
import com.laptrinhweb.service.IBuildingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BuildingAPI {

	@Autowired
	private IBuildingService buildingService;

	@PostMapping(value = { "api/building" })
	public BuildingDTO saveBuilding(@RequestBody BuildingDTO model) {
		return buildingService.save(model);
	}

	@SuppressWarnings("deprecation")
	@GetMapping(value = { "api/building" })
	public List<BuildingDTO> findAll(@RequestParam Map<String, Object> buildingQuery) {
		BuildingSearchBuilder builder = initBuildingBuilder(buildingQuery);
		Pageable pageable = new PageRequest((int) buildingQuery.get("page"), (int) buildingQuery.get("maxPageItem"));
		return buildingService.findAll(builder, pageable);
	}

	@GetMapping(value = { "api/building/totalItem" })
	public TotalItem getToTalItem(@RequestParam Map<String, Object> buildingQuery) {
		BuildingSearchBuilder builder = initBuildingBuilder(buildingQuery);
		return new TotalItem(buildingService.count(builder));
	}

	private BuildingSearchBuilder initBuildingBuilder(Map<String, Object> buildingQuery) {
		String[] buildingTypes = new String[] {};
		if (StringUtils.isNotBlank((String) buildingQuery.get("buildingTypes"))) {
			buildingTypes = ((String) buildingQuery.get("buildingTypes")).split(",");
		}
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder().setName((String) buildingQuery.get("name"))
				.setWard((String) buildingQuery.get("ward")).setStreet((String) buildingQuery.get("street"))
				.setRentAreaFrom((String) buildingQuery.get("rentAreaFrom"))
				.setRentAreaTo((String) buildingQuery.get("rentAreaTo"))
				.setCostRentFrom((String) buildingQuery.get("costRentFrom"))
				.setCostRentTo((String) buildingQuery.get("costRentTo")).setBuildingTypes(buildingTypes).build();
		return builder;
	}
}
