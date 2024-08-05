package com.github.wukap.tunbe.controller;

import com.github.wukap.tunbe.model.Point;
import com.github.wukap.tunbe.model.request.Coords;
import com.github.wukap.tunbe.model.request.RequestBodyObject;
import com.github.wukap.tunbe.model.request.Axes;
import com.github.wukap.tunbe.service.CubeVisibilityCalculator;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.github.wukap.tunbe.testDate.TestData.getChunksRequestData;
import static com.github.wukap.tunbe.testDate.TestData.getChunksRequestData2;

@Controller

@Slf4j
public class ChunkController {
    private static final Gson gson = new Gson();
    @Autowired
    private CubeVisibilityCalculator cubeVisibilityCalculator;

    @PostMapping("/chunk")
    @ResponseBody
    public ResponseEntity<String> getChunks(@RequestBody RequestBodyObject requestBody) {
        try {
            if (requestBody.point() == null || requestBody.axesX() == null || requestBody.axesY() == null || requestBody.axesZ() == null
                    || requestBody.chunks() == null) {
                log.error("Invalid request format {}", requestBody);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format");
            }
            System.out.println(requestBody);
            String data;

            if (requestBody.chunks().stream().anyMatch(i -> i == 4)) {
                data = gson.toJson(getChunksRequestData2());
            } else {
                data = gson.toJson(getChunksRequestData());
            }
            System.out.println();
            System.out.println(data);
            return ResponseEntity.ok(data);
        } catch (Exception e) {

            log.error("Invalid request format {}", requestBody);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format");
        }
    }

    @PostMapping("/chunkstr")
    @ResponseBody
    public String getChunks(@RequestBody String requestBody) {
        String data = gson.toJson(getChunksRequestData());
        System.out.println(requestBody);
        return data;
    }

    private List<CubeVisibilityCalculator.Cube> getCube(Coords characterPosition, Axes viewDirection, double verticalFOV, double aspectRatio) {
        Point point = new Point(characterPosition.x(), characterPosition.y(), characterPosition.z());
        Vector3D vector3D = new Vector3D(viewDirection.x(), viewDirection.y(), viewDirection.z());
        return cubeVisibilityCalculator.calculateVisibleCubes(point, vector3D, verticalFOV, aspectRatio);
    }

}
