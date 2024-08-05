package com.github.wukap.tunbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class Chunk {
    private int chunkId;
    private int LOD;
    private List<Object3D> objects;
}
