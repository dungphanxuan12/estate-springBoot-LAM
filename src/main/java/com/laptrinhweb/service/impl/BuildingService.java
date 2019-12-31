package com.laptrinhweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.converter.BuildingConverter;
import com.laptrinhweb.dto.BuildingDTO;
import com.laptrinhweb.entity.BuildingEntity;
import com.laptrinhweb.entity.RentAreaEntity;
import com.laptrinhweb.repository.BuildingRepository;
import com.laptrinhweb.service.IBuildingService;

@SuppressWarnings("unused")
@Service
public class BuildingService implements IBuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	private BuildingConverter buildingConverter;

	@Override
	public BuildingDTO save(BuildingDTO newBuilding) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(newBuilding);
		buildingEntity.setCreatedDate(new Date());
		buildingEntity.setCreatedBy("Admin");
		buildingEntity.setType(StringUtils.join(newBuilding.getBuildingTypes(),","));
		buildingEntity = buildingRepository.save(buildingEntity);
		String[] areas = newBuilding.getRentArea().split(",");
		List<String> areaList = Stream.of(areas).collect(Collectors.toList());
		return null;
	}

}
