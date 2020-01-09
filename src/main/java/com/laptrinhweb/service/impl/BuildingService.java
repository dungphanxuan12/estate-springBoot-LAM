package com.laptrinhweb.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhweb.builder.BuildingSearchBuilder;
import com.laptrinhweb.converter.BuildingConverter;
import com.laptrinhweb.dto.BuildingDTO;
import com.laptrinhweb.entity.BuildingEntity;
import com.laptrinhweb.repository.BuildingRepository;
import com.laptrinhweb.service.IBuildingService;

@Service
public class BuildingService implements IBuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BuildingConverter buildingConverter;

	@Override
	public BuildingDTO save(BuildingDTO newBuilding) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(newBuilding);
		buildingEntity.setCreatedDate(new Date());
		buildingEntity.setCreatedBy("Admin");
		buildingEntity.setType(StringUtils.join(newBuilding.getBuildingTypes(), ","));
		buildingEntity = buildingRepository.save(buildingEntity);
		String[] areas = newBuilding.getRentArea().split(",");
		List<String> areaList = Stream.of(areas).collect(Collectors.toList());
		return buildingConverter.convertToDTO(buildingEntity);
	}

	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
		List<BuildingEntity> buildingentities = buildingRepository.findAll(buildingSearchBuilder, pageable);
		List<BuildingDTO> results = buildingentities.stream().map(item -> buildingConverter.convertToDTO(item))
				.collect(Collectors.toList());
		return results;
	}

	@Override
	public int count(BuildingSearchBuilder builder) {
		return buildingRepository.count(builder).intValue();
	}

}
