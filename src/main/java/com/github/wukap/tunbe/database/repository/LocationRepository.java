package com.github.wukap.tunbe.database.repository;

import com.github.wukap.tunbe.database.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, String> {
}