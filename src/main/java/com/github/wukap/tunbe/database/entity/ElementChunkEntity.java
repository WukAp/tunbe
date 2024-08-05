package com.github.wukap.tunbe.database.entity;

import com.github.wukap.tunbe.database.keys.ElementChunkId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "element_chunk")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ElementChunkEntity {
    @Id
    private ElementChunkId id;
}
