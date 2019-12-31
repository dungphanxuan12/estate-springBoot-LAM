package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {

}
