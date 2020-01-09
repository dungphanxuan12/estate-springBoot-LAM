package com.laptrinhweb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhweb.builder.BuildingSearchBuilder;
import com.laptrinhweb.entity.BuildingEntity;

public interface BuildingRepositoryCustom {

	List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable);

	Long count(BuildingSearchBuilder builder);

}
