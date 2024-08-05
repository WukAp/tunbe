package com.github.wukap.tunbe.database.repository;

import com.github.wukap.tunbe.database.entity.ElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<ElementEntity, String> {
}