package com.laptrinhweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
