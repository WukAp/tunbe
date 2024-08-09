package com.github.wukap.tunbe.database.entity;

import com.github.wukap.tunbe.database.keys.MappingId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mapping")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MappingEntity {
    @Id
    private MappingId id;
}