package com.github.wukap.tunbe.database.repository;

import com.github.wukap.tunbe.database.entity.MappingEntity;
import com.github.wukap.tunbe.database.keys.MappingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MappingRepository extends JpaRepository<MappingEntity, MappingId> {
}