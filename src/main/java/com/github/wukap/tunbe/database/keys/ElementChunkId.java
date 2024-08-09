package com.github.wukap.tunbe.database.keys;

import com.github.wukap.tunbe.database.entity.ChunkEntity;
import com.github.wukap.tunbe.database.entity.ElementEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ElementChunkId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "element_id")
    private ElementEntity element;

    @ManyToOne
    @JoinColumn(name = "chunk_id")
    private ChunkEntity chunkId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementChunkId that = (ElementChunkId) o;
        return Objects.equals(element, that.element) && Objects.equals(chunkId, that.chunkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, chunkId);
    }
}