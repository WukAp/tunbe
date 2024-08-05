package com.github.wukap.tunbe.database.repository;

import com.github.wukap.tunbe.database.entity.ChunkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChunkRepository extends JpaRepository<ChunkEntity, Integer> {
}