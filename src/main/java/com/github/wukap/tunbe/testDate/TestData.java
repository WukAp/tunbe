package com.github.wukap.tunbe.testDate;

import com.github.wukap.tunbe.database.entity.*;
import com.github.wukap.tunbe.database.keys.ElementAttributeId;
import com.github.wukap.tunbe.database.keys.ElementChunkId;
import com.github.wukap.tunbe.database.keys.MappingId;
import com.github.wukap.tunbe.database.repository.*;
import org.springframework.context.ApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class TestData {
    private final List<LocationEntity> locations;
    private final List<ChunkEntity> chunks;

    private final List<ElementEntity> elements;
    private final List<AttributeEntity> attributes;
    private final List<ElementChunkEntity> elementChunks;
    private final List<MappingEntity> mappings;
    private final List<ElementAttributeEntity> elementAttributes;
    private final int CUBE_SIZE = 5;
    private final ApplicationContext app;

    public TestData(ApplicationContext app) {
        this.app = app;
        locations = List.of(new LocationEntity("KOG"), new LocationEntity("KOG_2"));
        chunks = new ArrayList<>();

        elements = new ArrayList<>();
        attributes = new ArrayList<>();
        elementChunks = new ArrayList<>();
        mappings = new ArrayList<>();
        elementAttributes = new ArrayList<>();
        int count = 0;
        for (int i = -10; i < 10; i++)
            for (int j = -10; j < 10; j++)
                for (int k = -10; k < 10; k++) {
                    double random = Math.random();
                    chunks.add(ChunkEntity.builder(i * CUBE_SIZE, j * CUBE_SIZE, k * CUBE_SIZE));

                    elements.add(new ElementEntity("squirrel_" + count, locations.getFirst(), "/external/animals_test/squirrel/squirrel_*.fbx", "hash_sqrl", i * CUBE_SIZE + random, j * CUBE_SIZE + random, k * CUBE_SIZE + random, random, random, random, random, 1.0, 1.3, 1.0, 1.3, 1.0, 1.3));
                    elementChunks.add(new ElementChunkEntity(new ElementChunkId(elements.getLast(), chunks.getLast())));
                    elements.add(new ElementEntity("cat_" + count++, locations.getFirst(), "/external/animals_test/cat/cat_*.fbx", "hash_cat", i * CUBE_SIZE, j * CUBE_SIZE, k * CUBE_SIZE, -random, -random, -random, -random, 1.0, 1.3, 1.0, 1.3, 1.0, 1.3));
                    elementChunks.add(new ElementChunkEntity(new ElementChunkId(elements.getLast(), chunks.getLast())));
                }
        for (int i = 0; i < 20; i += 2) {
            mappings.add(new MappingEntity(new MappingId(elements.get(i), elements.get(i + 1))));
        }
        for (int i = 0; i < 100; i++) {
            attributes.add(new AttributeEntity("name_" + i, i % 2 == 0, "description_" + i));
            var k = i;
            attributes.stream().filter(AttributeEntity::isEnabled).forEach(a -> elementAttributes.add(new ElementAttributeEntity(new ElementAttributeId(elements.get(k), a), "value_" + k, new Date(System.currentTimeMillis()), "Sasha")));


        }
    }

/*    @SneakyThrows
    public static Chunk[] getChunksRequestData() {
        Position position1 = new Position(10.1, 2.2, 3.3, 1.0, 2.2, 3.0, 4.0);
        Position position2 = new Position(-2.1, -2.2, -5.3, 7.4, 8.0, 9.0, 8.0);
        Position position3 = new Position(5.1, 1.2, 8.3, 5.0, 7.0, 2.1, 5.0);
        Position position4 = new Position(4.1, 10.2, 8.3, 5.0, 7.0, 2.1, 3.0);
        Position position5 = new Position(4.1, 1.2, 6.3, 5.0, 7.0, 2.1, 5.0);
        Position position6 = new Position(4.1, 2.2, 7.3, 9.0, 8.0, 2.1, 3.0);
        Position position7 = new Position(4.1, -2.2, 1.3, 75.0, 7.0, 2.1, 5.0);
        Position position8 = new Position(1.1, -2.2, -1.3, 7.0, 7.0, 9.1, 5.0);

        Object3D object3D1 = new Object3D("/external/animals_test/cat/cat_*.fbx", "guid_1", position1);
        Object3D object3D2 = new Object3D("/external/animals_test/squirrel/squirrel_*.fbx", "guid_2", position2);
        Object3D object3D3 = new Object3D("/external/animals_test/cat/cat_*.fbx", "guid_3", position3);
        Object3D object3D4 = new Object3D("/external/animals_test/squirrel/squirrel_*.fbx", "guid_4", position4);
        Object3D object3D5 = new Object3D("/external/animals_test/squirrel/squirrel_*.fbx", "guid_5", position5);

        Object3D object3D6 = new Object3D("/external/animals_test/cat/cat_*.fbx", "guid_6", position6);
        Object3D object3D7 = new Object3D("/external/animals_test/squirrel/squirrel_*.fbx", "guid_7", position7);
        Object3D object3D8 = new Object3D("/external/animals_test/squirrel/squirrel_*.fbx", "guid_8", position8);

        Chunk chunk1 = new Chunk(1, 3, List.of(object3D1, object3D2, object3D4, object3D5));
        Chunk chunk2 = new Chunk(2, 2, List.of(object3D2, object3D3, object3D6, object3D7, object3D8));
        Chunk chunk3 = new Chunk(3, 3, List.of());
        Chunk chunk4 = new Chunk(4, 3, List.of(object3D2, object3D3, object3D6, object3D7, object3D8));
        return new Chunk[]{chunk1, chunk2, chunk3, chunk4};
    }

    @SneakyThrows
    public static Chunk[] getChunksRequestData2() {
        Position position1 = new Position(10.1, 2.2, 3.3, 1.0, 2.2, 3.0, 4.0);
        Position position2 = new Position(-2.1, -2.2, -5.3, 7.4, 8.0, 9.0, 8.0);
        Position position3 = new Position(5.1, 1.2, 8.3, 5.0, 7.0, 2.1, 5.0);
        Position position4 = new Position(4.1, 10.2, 8.3, 5.0, 7.0, 2.1, 3.0);
        Position position5 = new Position(4.1, 1.2, 6.3, 5.0, 7.0, 2.1, 5.0);
        Position position6 = new Position(4.1, 2.2, 7.3, 9.0, 8.0, 2.1, 3.0);
        Position position7 = new Position(4.1, -2.2, 1.3, 75.0, 7.0, 2.1, 5.0);
        Position position8 = new Position(1.1, -2.2, -1.3, 7.0, 7.0, 9.1, 5.0);

        Object3D object3D1 = new Object3D("/external/animals_test/cat/cat_*.fbx", "guid_1", position1);
        Object3D object3D2 = new Object3D("/external/animals_test/squirrel/squirrel_*.fbx", "guid_2", position2);
        Object3D object3D3 = new Object3D("/external/animals_test/cat/cat_*.fbx", "guid_3", position3);
        Object3D object3D4 = new Object3D("/external/animals_test/squirrel/squirrel_*.fbx", "guid_4", position4);
        Object3D object3D5 = new Object3D("/external/animals_test/squirrel/squirrel_*.fbx", "guid_5", position5);

        Object3D object3D6 = new Object3D("/external/animals_test/cat/cat_*.fbx", "guid_6", position6);
        Object3D object3D7 = new Object3D("/external/animals_test/squirrel/squirrel_*.fbx", "guid_7", position7);
        Object3D object3D8 = new Object3D("/external/animals_test/squirrel/squirrel_*.fbx", "guid_8", position8);

        Chunk chunk1 = new Chunk(5, 2, List.of(object3D2, object3D4, object3D5));
        Chunk chunk2 = new Chunk(6, 2, List.of(object3D2, object3D3, object3D6, object3D7, object3D8));
        Chunk chunk3 = new Chunk(7, 3, List.of(object3D2, object3D3, object3D6, object3D7, object3D8));
        Chunk chunk4 = new Chunk(4, 2, null);
        return new Chunk[]{chunk1, chunk2, chunk3, chunk4};
    }*/

    public void generateDataInBd() {
        try {
            app.getBean(ChunkRepository.class).saveAll(chunks);
            System.out.println("Chunk: " + chunks.size());
            app.getBean(LocationRepository.class).saveAll(locations);
            System.out.println("Location: " + locations.size());
            app.getBean(ElementRepository.class).saveAll(elements);
            System.out.println("Element: " + elements.size());
            app.getBean(AttributeRepository.class).saveAll(attributes);
            System.out.println("Attribute: " + attributes.size());
            app.getBean(ElementChunkRepository.class).saveAll(elementChunks);
            System.out.println("ElementChunk: " + elementChunks.size());
            app.getBean(MappingRepository.class).saveAll(mappings);
            System.out.println("Mapping: " + mappings.size());
            app.getBean(ElementAttributeRepository.class).saveAll(elementAttributes);
            System.out.println("ElementAttribute: " + elementAttributes.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
