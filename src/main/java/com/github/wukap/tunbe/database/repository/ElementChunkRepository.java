package com.github.wukap.tunbe.database.repository;

import com.github.wukap.tunbe.database.entity.ElementChunkEntity;
import com.github.wukap.tunbe.database.keys.ElementChunkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementChunkRepository extends JpaRepository<ElementChunkEntity, ElementChunkId> {
}