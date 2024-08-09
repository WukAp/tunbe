package com.github.wukap.tunbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Position {
    private double x;
    private double y;
    private double z;
    private double quaternion1;
    private double quaternion2;
    private double quaternion3;
    private double quaternion4;

}
