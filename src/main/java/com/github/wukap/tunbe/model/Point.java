package com.github.wukap.tunbe.model;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.Objects;

public record Point(double x, double y, double z) {
    public Vector3D toVector() {
        return new Vector3D(x, y, z);
    }

    public Point add(Vector3D vector) {
        return new Point(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }


    public double calculateDistance(Point otherPoint) {
        return Math.sqrt(Math.pow(otherPoint.x - x, 2) + Math.pow(otherPoint.y - y, 2) + Math.pow(otherPoint.z - z, 2));
    }

    public double calculateDistance(double x, double y, double z) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) + Math.pow(z - this.z, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(x, point.x) == 0 && Double.compare(y, point.y) == 0 && Double.compare(z, point.z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
