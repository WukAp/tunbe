package com.github.wukap.tunbe.database.repository;

import com.github.wukap.tunbe.database.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<AttributeEntity, String> {
}