package com.github.wukap.tunbe.database.keys;

import com.github.wukap.tunbe.database.entity.AttributeEntity;
import com.github.wukap.tunbe.database.entity.ElementEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ElementAttributeId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "element_id")
    private ElementEntity elementId;

    @ManyToOne
    @JoinColumn(name = "name")
    private AttributeEntity name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementAttributeId that = (ElementAttributeId) o;
        return Objects.equals(elementId, that.elementId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementId, name);
    }
}