package com.laptrinhweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.builder.BuildingSearchBuilder;
import com.laptrinhweb.dto.BuildingDTO;

public interface IBuildingService {
	
	BuildingDTO save(BuildingDTO newBuilding);

	List<BuildingDTO> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable);

	int count(BuildingSearchBuilder builder);
}
