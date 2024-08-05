package com.github.wukap.tunbe.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "element")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ElementEntity {
    @Id
    @Column(name = "element_id")
    private String elementId;

    @ManyToOne
    @JoinColumn(name = "location_name")
    @Lazy
    private LocationEntity location;

    @Column(name = "path")
    private String path;

    @Column(name = "file_hash_code")
    private String fileHashCode;

    @Column(name = "x")
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "z")
    private double z;

    @Column(name = "quaternion_1")
    private double quaternion1;

    @Column(name = "quaternion_2")
    private double quaternion2;

    @Column(name = "quaternion_3")
    private double quaternion3;

    @Column(name = "quaternion_4")
    private double quaternion4;

    @Column(name = "bounding_box_min_x")
    private double boundingBoxMinX;

    @Column(name = "bounding_box_max_x")
    private double boundingBoxMaxX;

    @Column(name = "bounding_box_min_y")
    private double boundingBoxMinY;

    @Column(name = "bounding_box_max_y")
    private double boundingBoxMaxY;

    @Column(name = "bounding_box_min_z")
    private double boundingBoxMinZ;

    @Column(name = "bounding_box_max_z")
    private double boundingBoxMaxZ;
}
