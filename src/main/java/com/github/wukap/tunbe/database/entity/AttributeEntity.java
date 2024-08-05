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
@Table(name = "attribute")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttributeEntity {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "description")
    private String description;
}