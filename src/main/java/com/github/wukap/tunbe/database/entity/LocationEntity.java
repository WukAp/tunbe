package com.github.wukap.tunbe.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "location")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationEntity {
    @Id
    @Column(name = "location_name")
    private String name;

}