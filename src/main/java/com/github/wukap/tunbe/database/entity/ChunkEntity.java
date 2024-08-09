package com.github.wukap.tunbe.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chunk", indexes = @Index(columnList = "x, y, z"))
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
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "z")
    private double z;

    public static ChunkEntity builder(int x, int y, int z) {
        ChunkEntity chunk = new ChunkEntity();
        chunk.setX(x);
        chunk.setY(y);
        chunk.setZ(z);
        //chunk.setChunkId(atomicInteger.getAndIncrement());
        return chunk;
    }
}