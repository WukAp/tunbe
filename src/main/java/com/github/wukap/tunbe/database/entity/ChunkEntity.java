package com.github.wukap.tunbe.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chunk")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChunkEntity {
//private static final AtomicInteger atomicInteger= new AtomicInteger(0);
    @Id
    @Column(name = "chunk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chunkId;

    @Column(name = "x")
    private int x;

    @Column(name = "y")
    private int y;

    @Column(name = "z")
    private int z;

    public static ChunkEntity builder(int x, int y, int z) {
        ChunkEntity chunk = new ChunkEntity();
        chunk.setX(x);
        chunk.setY(y);
        chunk.setZ(z);
        //chunk.setChunkId(atomicInteger.getAndIncrement());
        return chunk;
    }
}