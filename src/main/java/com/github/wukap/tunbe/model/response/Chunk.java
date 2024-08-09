package com.github.wukap.tunbe.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Chunk {
    private long chunkId;
    private int LOD;
    private List<Object3D> objects;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chunk chunk = (Chunk) o;
        return chunkId == chunk.chunkId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(chunkId);
    }
}
