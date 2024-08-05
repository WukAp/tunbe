package com.github.wukap.tunbe.model.request;

import com.github.wukap.tunbe.model.Point;

import java.util.List;

public record RequestBodyObject(Point point, Point axesX, Point axesY, Point axesZ, double verticalFov, double ratio, List<Integer> chunks) {
    @Override
    public String toString() {
        return "{\"point\":" + point + ", \n\"axesX\":" + axesX + ", \n\"axesY\":" + axesY + ", \n\"axesZ\":" + axesZ + ", \n\"verticalFov\":" + verticalFov + ", \n\"ratio\":" + ratio + ", \n\"chunks\":" + chunks + "}";
    }
}