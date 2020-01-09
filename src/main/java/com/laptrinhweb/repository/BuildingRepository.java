package com.laptrinhweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {

	List<BuildingEntity> findByNameAndWard(String name, String ward);

}
