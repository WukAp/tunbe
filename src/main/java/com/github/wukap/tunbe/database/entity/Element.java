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
public class Element {
    @Id
    @Column(name = "element_id")
    private String elementId;

    @ManyToOne
    @JoinColumn(name = "location_name")
    @Lazy
    private Location location;

    @Column(name = "path")
    private String path;

    @Column(name = "file_hash_code")
    private String fileHashCode;

    @Column(name = "x")
    private float x;

    @Column(name = "y")
    private float y;

    @Column(name = "z")
    private float z;

    @Column(name = "quaternion_1")
    private float quaternion1;

    @Column(name = "quaternion_2")
    private float quaternion2;

    @Column(name = "quaternion_3")
    private float quaternion3;

    @Column(name = "quaternion_4")
    private float quaternion4;

    @Column(name = "bounding_box_min_x")
    private float boundingBoxMinX;

    @Column(name = "bounding_box_max_x")
    private float boundingBoxMaxX;

    @Column(name = "bounding_box_min_y")
    private float boundingBoxMinY;

    @Column(name = "bounding_box_max_y")
    private float boundingBoxMaxY;

    @Column(name = "bounding_box_min_z")
    private float boundingBoxMinZ;

    @Column(name = "bounding_box_max_z")
    private float boundingBoxMaxZ;
}
