package com.github.wukap.tunbe.database.repository;

import com.github.wukap.tunbe.database.entity.ElementAttributeEntity;
import com.github.wukap.tunbe.database.keys.ElementAttributeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementAttributeRepository extends JpaRepository<ElementAttributeEntity, ElementAttributeId> {
}