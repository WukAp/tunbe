package com.github.wukap.tunbe.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "mapping")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mapping {
    @Id
    @ManyToOne
    @JoinColumn(name = "element_id1")
    @Lazy
    private Element element1;


    @Id
    @ManyToOne
    @JoinColumn(name = "element_id2")
    @Lazy
    private Element element2;
}