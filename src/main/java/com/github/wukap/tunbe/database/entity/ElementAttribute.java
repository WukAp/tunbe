package com.github.wukap.tunbe.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import java.sql.Date;

@Entity
@Table(name = "element_attribute")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ElementAttribute {
    @Id
    @ManyToOne
    @JoinColumn(name = "element_id")
    @Lazy
    private Element element;

    @Id
    @ManyToOne
    @JoinColumn(name = "name")
    Attribute attribute;

    private String value;

    private Date datetime;
}