package com.github.wukap.tunbe.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "chank")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Chank {
    @Id
    @ManyToOne
    @JoinColumn(name = "element_id")
    @Lazy
    private Element element;

    @Id
    @Column(name = "x")
    private int x;

    @Id
    @Column(name = "y")
    private int y;

    @Id
    @Column(name = "z")
    private int z;
}