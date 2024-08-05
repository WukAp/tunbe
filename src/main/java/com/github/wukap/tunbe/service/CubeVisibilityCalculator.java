package com.github.wukap.tunbe.service;

import com.github.wukap.tunbe.model.Point;
import com.github.wukap.tunbe.model.request.Coords;
import lombok.Getter;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CubeVisibilityCalculator {
    private final double maxViewDistance;
    private final int cubeSize;
    private final double lodPercentage1;
    private final double lodPercentage2;

    public CubeVisibilityCalculator(@Value("${camera.maxViewDistance}") double maxViewDistance, @Value("${camera.cubeSize}") int cubeSize, @Value("${camera.lodPercentage1}") double lodPercentage1, @Value("${camera.lodPercentage2}") double lodPercentage2) {
        this.maxViewDistance = maxViewDistance;
        this.cubeSize = cubeSize;
        this.lodPercentage1 = lodPercentage1;
        this.lodPercentage2 = lodPercentage2;
    }

    public List<Cube> calculateVisibleCubes(Point characterPosition, Vector3D viewDirection, double verticalFOV, double aspectRatio) {
        List<Cube> visibleCubes = new ArrayList<>();

        double horizontalFOV = 2 * Math.atan(Math.tan(verticalFOV / 2) * aspectRatio);
        double maxDistanceX = Math.tan(horizontalFOV / 2) * maxViewDistance;
        double maxDistanceY = Math.tan(verticalFOV / 2) * maxViewDistance * aspectRatio;
        double maxDistanceZ = Math.abs(Math.floor((characterPosition.z() + maxViewDistance) / cubeSize) - Math.ceil((characterPosition.z() - maxViewDistance) / cubeSize)) * cubeSize;

        Frustum frustum = new Frustum(characterPosition, viewDirection, verticalFOV, horizontalFOV, maxViewDistance);

        for (int x = (int) (Math.ceil((characterPosition.x() - maxDistanceX) / cubeSize) * cubeSize); x <= Math.floor((characterPosition.x() + maxDistanceX) / cubeSize) * cubeSize; x += cubeSize) {
            for (int y = (int) (Math.ceil((characterPosition.y() - maxDistanceY) / cubeSize) * cubeSize); y <= Math.floor((characterPosition.y() + maxDistanceY) / cubeSize) * cubeSize; y += cubeSize) {
                for (int z = (int) (Math.ceil((characterPosition.z() - maxDistanceZ) / cubeSize) * cubeSize); z <= Math.floor((characterPosition.z() + maxDistanceZ) / cubeSize) * cubeSize; z += cubeSize) {
                    double distancePercentage = Math.sqrt(x * x + y * y + z * z) / maxViewDistance * 100;
                    int lod = calculateLOD(distancePercentage, lodPercentage1, lodPercentage2);
                    Cube cube = new Cube(x, y, z, cubeSize, lod);
                    if (frustum.intersectsCube(cube)) {
                        visibleCubes.add(cube);
                    }
                }
            }
        }

        return visibleCubes;
    }

    private int calculateLOD(double distancePercentage, double lodPercentage1, double lodPercentage2) {
        if (distancePercentage <= lodPercentage1) {
            return 1;
        } else if (distancePercentage > lodPercentage1 && distancePercentage <= lodPercentage2) {
            return 2;
        } else {
            return 3;
        }
    }

    @Getter
    public class Cube {
        private int x;
        private int y;
        private int z;
        private int size;
        private int lod;

        public Cube(int x, int y, int z, int size, int lod) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.size = size;
            this.lod = lod;
        }
    }


    public class Frustum {
        private Point position;
        private Vector3D viewDirection;
        private double verticalFOV;
        private double horizontalFOV;
        private double maxDistance;

        public Frustum(Point position, Vector3D viewDirection, double verticalFOV, double horizontalFOV, double maxDistance) {
            this.position = position;
            this.viewDirection = viewDirection;
            this.verticalFOV = verticalFOV;
            this.horizontalFOV = horizontalFOV;
            this.maxDistance = maxDistance;
        }

        public boolean intersectsCube(Cube cube) {
            double halfSize = cube.getSize() / 2.0;
            double cubeCenterX = cube.getX() + halfSize;
            double cubeCenterY = cube.getY() + halfSize;
            double cubeCenterZ = cube.getZ() + halfSize;

            // Check if cube center coordinates are aligned with cube size boundaries
            if (cubeCenterX % cube.getSize() != 0 || cubeCenterY % cube.getSize() != 0 || cubeCenterZ % cube.getSize() != 0) {
                return false; // Cube center not aligned with cube size boundaries
            }

            double deltaX = cubeCenterX - position.x();
            double deltaY = cubeCenterY - position.y();
            double deltaZ = cubeCenterZ - position.z();

            double distanceToCube = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

            double angleVertical = Math.acos((deltaX * viewDirection.getX() + deltaY * viewDirection.getY() + deltaZ * viewDirection.getZ()) / (distanceToCube * viewDirection.getNorm()));

            double angleHorizontal = Math.acos((deltaX * viewDirection.getY() - deltaY * viewDirection.getX()) / distanceToCube);

            return distanceToCube <= maxDistance && angleVertical <= verticalFOV / 2 && angleHorizontal <= horizontalFOV / 2;
        }
    }
}