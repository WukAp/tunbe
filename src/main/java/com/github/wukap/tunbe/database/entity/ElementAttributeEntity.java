package com.github.wukap.tunbe.database.entity;

import com.github.wukap.tunbe.database.keys.ElementAttributeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "element_attribute")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ElementAttributeEntity {
    @Id
    ElementAttributeId id;

    private String value;

    private Date datetime;
    private String Author;
}