package com.github.wukap.tunbe.service;

import com.github.wukap.tunbe.database.repository.ElementChunkRepository;
import com.github.wukap.tunbe.model.Camera;
import com.github.wukap.tunbe.model.Frustum;
import com.github.wukap.tunbe.model.Point;
import com.github.wukap.tunbe.model.request.RequestBodyObject;
import com.github.wukap.tunbe.model.response.Chunk;
import com.github.wukap.tunbe.model.response.Object3D;
import com.github.wukap.tunbe.model.response.Position;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CubeVisibilityCalculator {
    private final int cubeSize;
    private final Camera camera;
    private final ElementChunkRepository elementChunkRepository;
    private final DataSource dataSource;

    private final String sqlQuery = "select el_ch.chunk_id, el_ch.element_id, ch.x as ch_x,  ch.y as ch_y,ch.z as ch_z, el.path, el.x, el.y,  el.z, el.quaternion_1, el.quaternion_2, el.quaternion_3, el.quaternion_4 " + "FROM element_chunk el_ch " + "JOIN chunk ch ON ch.chunk_id = el_ch.chunk_id " + "JOIN element el ON el.element_id = el_ch.element_id " + "WHERE ch.x >= ? AND ch.x < ? " + "AND ch.y >= ? AND ch.y < ? " + "AND ch.z >= ? AND ch.z < ?";

    public CubeVisibilityCalculator(@Value("${camera.cubeSize}") int cubeSize, Camera camera, ElementChunkRepository elementChunkRepository, DataSource dataSource) {
        this.cubeSize = cubeSize;
        this.camera = camera;
        this.elementChunkRepository = elementChunkRepository;
        this.dataSource = dataSource;
    }

    public List<Cube> calculateVisibleCubes(Frustum.BoundingBox boundingBox, Point characterPosition) {


        List<Cube> visibleCubes = new ArrayList<>();
        for (int x = (int) boundingBox.minX() / cubeSize; x <= (int) boundingBox.maxX() / cubeSize; x += cubeSize) {
            for (int y = (int) boundingBox.minY() / cubeSize; y <= (int) boundingBox.maxY() / cubeSize; y += cubeSize) {
                for (int z = (int) boundingBox.minZ() / cubeSize; z <= (int) boundingBox.maxZ() / cubeSize; z += cubeSize) {
                    visibleCubes.add(getCube(x, y, z, characterPosition));
                }
            }
        }
        return visibleCubes;
    }

    public Frustum.BoundingBox getBoundingBox(Point characterPosition, Vector3D axesX, Vector3D axesY, Vector3D axesZ, double verticalFOV, double aspectRatio) {
        Frustum frustum = new Frustum(characterPosition, axesX, axesY, axesZ, verticalFOV, aspectRatio, camera.getMaxViewDistance());
        return frustum.calculateBoundingBox();
    }

    private Cube getCube(int x, int y, int z, Point characterPosition) {
        return new Cube(x, y, z, calculateLOD(characterPosition, x, y, z));
    }

    public int calculateLOD(Point characterPosition, double x, double y, double z) {
        double distance = characterPosition.calculateDistance(x + cubeSize / 2.0, y + cubeSize / 2.0, z + cubeSize / 2.0);
        if (distance <= camera.getMaxLod1()) {
            return 1;
        } else if (distance <= camera.getMaxLod2()) {
            return 2;
        } else {
            return 3;
        }
    }

    public List<Chunk> getObjects(RequestBodyObject requestBody) throws SQLException {
        Frustum.BoundingBox boundingBox = getBoundingBox(requestBody.point(), requestBody.axesX().toVector(), requestBody.axesY().toVector(), requestBody.axesZ().toVector(), requestBody.verticalFov(), requestBody.ratio());

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {


            preparedStatement.setDouble(1, boundingBox.minX());
            preparedStatement.setDouble(2, boundingBox.maxX());
            preparedStatement.setDouble(3, boundingBox.minY());
            preparedStatement.setDouble(4, boundingBox.maxY());
            preparedStatement.setDouble(5, boundingBox.minZ());
            preparedStatement.setDouble(6, boundingBox.maxZ());

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<Chunk, List<Object3D>> chunks = new HashMap<>();
            while (resultSet.next()) {
                int chunkId = resultSet.getInt("chunk_id");
                String elementId = resultSet.getString("element_id");
                String path = resultSet.getString("path");
                double x = resultSet.getDouble("x");
                double y = resultSet.getDouble("y");
                double z = resultSet.getDouble("z");
                double chX = resultSet.getDouble("ch_x");
                double chY = resultSet.getDouble("ch_y");
                double chZ = resultSet.getDouble("ch_z");
                double quaternion1 = resultSet.getDouble("quaternion_1");
                double quaternion2 = resultSet.getDouble("quaternion_2");
                double quaternion3 = resultSet.getDouble("quaternion_3");
                double quaternion4 = resultSet.getDouble("quaternion_4");
                int lod = calculateLOD(requestBody.point(), chX, chY, chZ);

                Chunk chunk = new Chunk(chunkId, lod, null);
                Object3D object3D = new Object3D(path, elementId, new Position(x, y, z, quaternion1, quaternion2, quaternion3, quaternion4));
                // Process each row of the ResultSet here
                if (chunks.get(chunk) == null) {
                    chunks.put(chunk, new ArrayList<>() {{
                        add(object3D);
                    }});
                } else {
                    chunks.get(chunk).add(object3D);
                }
            }
            preparedStatement.close();
            List<Chunk> chunksList = chunks.entrySet().stream().map(e -> {
                e.getKey().setObjects(e.getValue());
                return e.getKey();
            }).toList();
            return chunksList;
        }
    }


    public record Cube(int x, int y, int z, int lod) {

    }

}