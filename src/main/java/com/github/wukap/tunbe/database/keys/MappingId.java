package com.github.wukap.tunbe.database.keys;

import com.github.wukap.tunbe.database.entity.ElementEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class MappingId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "element_id1")
    @Lazy
    private ElementEntity element1;


    @ManyToOne
    @JoinColumn(name = "element_id2")
    @Lazy
    private ElementEntity element2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MappingId mappingId = (MappingId) o;
        return Objects.equals(element1, mappingId.element1) && Objects.equals(element2, mappingId.element2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element1, element2);
    }
}