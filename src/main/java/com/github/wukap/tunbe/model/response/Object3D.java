package com.github.wukap.tunbe.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
@Getter
@Setter
@AllArgsConstructor
public class Object3D {
    URI uri;
    String guid;
    Position position;
}
