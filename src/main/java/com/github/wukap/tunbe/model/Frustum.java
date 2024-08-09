package com.github.wukap.tunbe.model;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.Arrays;

public class Frustum {
    private final Point characterPosition;
    private final Vector3D axesX;
    private final Vector3D axesY;
    private final Vector3D axesZ;
    private final double verticalFov;
    private final double aspectRatio;
    private final double maxViewDistance;


    public Frustum(Point characterPosition, Vector3D axesX, Vector3D axesY, Vector3D axesZ, double verticalFov, double aspectRatio, double maxViewDistance) {
        this.characterPosition = characterPosition;
        this.axesX = axesX;
        this.axesY = axesY;
        this.axesZ = axesZ;
        this.verticalFov = verticalFov;
        this.aspectRatio = aspectRatio;
        this.maxViewDistance = maxViewDistance;
    }

    public BoundingBox calculateBoundingBox() {
        Point[] cameraVertices = calculateCameraVertices();
        double minX = Arrays.stream(cameraVertices).mapToDouble(Point::x).min().getAsDouble();
        double minY = Arrays.stream(cameraVertices).mapToDouble(Point::y).min().getAsDouble();
        double minZ = Arrays.stream(cameraVertices).mapToDouble(Point::z).min().getAsDouble();
        double maxX = Arrays.stream(cameraVertices).mapToDouble(Point::x).max().getAsDouble();
        double maxY = Arrays.stream(cameraVertices).mapToDouble(Point::y).max().getAsDouble();
        double maxZ = Arrays.stream(cameraVertices).mapToDouble(Point::z).max().getAsDouble();
        return new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public Point[] calculateCameraVertices() {
        Point[] cameraVertices = new Point[5];

        // Calculate the camera vertices based on the frustum parameters
        // Use characterPosition, axesX, axesY, axesZ, verticalFOV, aspectRatio, and maxViewDistance

        double halfVerticalFOV = verticalFov / 2.0;

        double maxDistanceX = Math.tan(halfVerticalFOV) * maxViewDistance * aspectRatio;
        double maxDistanceY = Math.tan(halfVerticalFOV) * maxViewDistance;
        double maxDistanceZ = maxViewDistance;

        Vector3D scaledAxesX = axesX.normalize().scalarMultiply(maxDistanceX);
        Vector3D scaledAxesY = axesY.normalize().scalarMultiply(maxDistanceY);
        Vector3D scaledAxesZ = axesZ.normalize().scalarMultiply(maxDistanceZ);


        // Calculate the camera vertices
        cameraVertices[0] = characterPosition; // Camera position
        cameraVertices[1] = characterPosition.add(scaledAxesX).add(scaledAxesY).add(scaledAxesZ);
        cameraVertices[2] = characterPosition.add(scaledAxesX.negate()).add(scaledAxesY).add(scaledAxesZ);
        cameraVertices[3] = characterPosition.add(scaledAxesX).add(scaledAxesY.negate()).add(scaledAxesZ);
        cameraVertices[4] = characterPosition.add(scaledAxesX.negate()).add(scaledAxesY.negate()).add(scaledAxesZ);
        return cameraVertices;

    }

    public static record BoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {

    }
}
