package com.github.wukap.tunbe.testDate;

import com.github.wukap.tunbe.database.entity.*;
import com.github.wukap.tunbe.database.keys.ElementAttributeId;
import com.github.wukap.tunbe.database.keys.ElementChunkId;
import com.github.wukap.tunbe.database.keys.MappingId;
import com.github.wukap.tunbe.database.repository.*;
import com.github.wukap.tunbe.model.response.Chunk;
import com.github.wukap.tunbe.model.response.Object3D;
import com.github.wukap.tunbe.model.response.Position;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;

import java.net.URI;
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

    public TestData() {

        locations = List.of(new LocationEntity("KOG"), new LocationEntity("KOG_2"));
        chunks = getChunks();

        elements = getElements();
        attributes = getAttributes();
        elementChunks = getElementChunks();
        mappings = getMappings();
        elementAttributes = getElementAttribures();
    }

    @SneakyThrows
    public static Chunk[] getChunksRequestData() {
        Position position1 = new Position(10.1, 2.2, 3.3, 1.0, 2.2, 3.0, 4.0);
        Position position2 = new Position(-2.1, -2.2, -5.3, 7.4, 8.0, 9.0, 8.0);
        Position position3 = new Position(5.1, 1.2, 8.3, 5.0, 7.0, 2.1, 5.0);
        Position position4 = new Position(4.1, 10.2, 8.3, 5.0, 7.0, 2.1, 3.0);
        Position position5 = new Position(4.1, 1.2, 6.3, 5.0, 7.0, 2.1, 5.0);
        Position position6 = new Position(4.1, 2.2, 7.3, 9.0, 8.0, 2.1, 3.0);
        Position position7 = new Position(4.1, -2.2, 1.3, 75.0, 7.0, 2.1, 5.0);
        Position position8 = new Position(1.1, -2.2, -1.3, 7.0, 7.0, 9.1, 5.0);

        Object3D object3D1 = new Object3D(new URI("/external/animals_test/cat/cat_*.fbx"), "guid_1", position1);
        Object3D object3D2 = new Object3D(new URI("/external/animals_test/squirrel/squirrel_*.fbx"), "guid_2", position2);
        Object3D object3D3 = new Object3D(new URI("/external/animals_test/cat/cat_*.fbx"), "guid_3", position3);
        Object3D object3D4 = new Object3D(new URI("/external/animals_test/squirrel/squirrel_*.fbx"), "guid_4", position4);
        Object3D object3D5 = new Object3D(new URI("/external/animals_test/squirrel/squirrel_*.fbx"), "guid_5", position5);

        Object3D object3D6 = new Object3D(new URI("/external/animals_test/cat/cat_*.fbx"), "guid_6", position6);
        Object3D object3D7 = new Object3D(new URI("/external/animals_test/squirrel/squirrel_*.fbx"), "guid_7", position7);
        Object3D object3D8 = new Object3D(new URI("/external/animals_test/squirrel/squirrel_*.fbx"), "guid_8", position8);

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

        Object3D object3D1 = new Object3D(new URI("/external/animals_test/cat/cat_*.fbx"), "guid_1", position1);
        Object3D object3D2 = new Object3D(new URI("/external/animals_test/squirrel/squirrel_*.fbx"), "guid_2", position2);
        Object3D object3D3 = new Object3D(new URI("/external/animals_test/cat/cat_*.fbx"), "guid_3", position3);
        Object3D object3D4 = new Object3D(new URI("/external/animals_test/squirrel/squirrel_*.fbx"), "guid_4", position4);
        Object3D object3D5 = new Object3D(new URI("/external/animals_test/squirrel/squirrel_*.fbx"), "guid_5", position5);

        Object3D object3D6 = new Object3D(new URI("/external/animals_test/cat/cat_*.fbx"), "guid_6", position6);
        Object3D object3D7 = new Object3D(new URI("/external/animals_test/squirrel/squirrel_*.fbx"), "guid_7", position7);
        Object3D object3D8 = new Object3D(new URI("/external/animals_test/squirrel/squirrel_*.fbx"), "guid_8", position8);

        Chunk chunk1 = new Chunk(5, 2, List.of(object3D1, object3D2, object3D4, object3D5));
        Chunk chunk2 = new Chunk(6, 2, List.of(object3D2, object3D3, object3D6, object3D7, object3D8));
        Chunk chunk3 = new Chunk(7, 3, List.of(object3D2, object3D3, object3D6, object3D7, object3D8));
        Chunk chunk4 = new Chunk(4, 2, null);
        return new Chunk[]{chunk1, chunk2, chunk3, chunk4};
    }

    public void generateDataInBd(ApplicationContext app) {
        try {
            app.getBean(ChunkRepository.class).saveAll(chunks);
            app.getBean(LocationRepository.class).saveAll(locations);
            app.getBean(ElementRepository.class).saveAll(elements);
            app.getBean(AttributeRepository.class).saveAll(attributes);
            app.getBean(ElementChunkRepository.class).saveAll(elementChunks);
            app.getBean(MappingRepository.class).saveAll(mappings);
            app.getBean(ElementAttributeRepository.class).saveAll(elementAttributes);

        }   catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(
                    "Chunk: " + getChunks().size() +
                            " Element: " + getElements().size() +
                            " Attribute: " + getAttributes().size() +
                            " ElementChunk: " + getElementChunks().size() +
                            " Mapping: " + getMappings().size() +
                            " ElementAttribute: " + getElementAttribures().size()
            );
        }
    }

    private List<ChunkEntity> getChunks() {
        var chunks = new ArrayList<ChunkEntity>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                for (int k = 0; k < 10; k++) {
                    chunks.add(ChunkEntity.builder(i, j, k));
                }
        return chunks;
    }

    private List<ElementEntity> getElements() {
        var elements = new ArrayList<ElementEntity>();
        for (int i = 0; i < 100; i++) {

            elements.add(new ElementEntity("squirrel_" + i, locations.getFirst(), "/external/animals_teat/cat/cat_*.fbx", "hash_sqrl", 1.3, 1.0, 1.3, 1.0, 1.3, 1.0, 1.3, 1.0, 1.3, 1.0, 1.3, 1.0, 1.3));
            elements.add(new ElementEntity("cat_" + i, locations.getFirst(), "/external/animals_teat/cat/cat_*.fbx", "hashcat", 1.3, 1.0, 1.3, 1.0, 1.3, 1.0, 1.3, 1.0, 1.3, 1.0, 1.3, 1.0, 1.3));
        }
        return elements;
    }

    private List<AttributeEntity> getAttributes() {
        var attributes = new ArrayList<AttributeEntity>();
        for (int i = 0; i < 100; i++) {
            attributes.add(new AttributeEntity("name_" + i, i % 2 == 0, "description_" + i));
        }
        return attributes;
    }

    private List<ElementChunkEntity> getElementChunks() {
        var elementChunks = new ArrayList<ElementChunkEntity>();
        for (int i = 0; i < 100; i++) {
            elementChunks.add(new ElementChunkEntity(new ElementChunkId(elements.get(i), chunks.get(i))));
            if (i % 2 == 0) elementChunks.add(new ElementChunkEntity(new ElementChunkId(elements.get(i + 1), chunks.get(i))));
        }
        return elementChunks;
    }

    private List<MappingEntity> getMappings() {
        var mappings = new ArrayList<MappingEntity>();
        for (int i = 0; i < 20; i += 2) {
            mappings.add(new MappingEntity(new MappingId(elements.get(i), elements.get(i + 1))));
        }
        return mappings;
    }

    private List<ElementAttributeEntity> getElementAttribures() {
        var elementAttributes = new ArrayList<ElementAttributeEntity>();
        for (int i = 0; i < 100; i++) {
            var k = i;
            attributes.stream().filter(AttributeEntity::isEnabled).forEach(a -> elementAttributes.add(new ElementAttributeEntity(new ElementAttributeId(elements.get(k), a), "value_" + k, new Date(System.currentTimeMillis()), "Sasha")));

        }
        return elementAttributes;
    }
}
