package com.github.wukap.tunbe.controller;

import com.github.wukap.tunbe.database.repository.ElementChunkRepository;
import com.github.wukap.tunbe.model.request.RequestBodyObject;
import com.github.wukap.tunbe.service.CubeVisibilityCalculator;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

@Slf4j
public class ChunkController {
    private static final Gson gson = new Gson();
    @Autowired
    private CubeVisibilityCalculator cubeVisibilityCalculator;
    @Autowired
    private ElementChunkRepository elementChunkRepository;

    @PostMapping("/chunk")
    @ResponseBody
    public ResponseEntity<String> getChunks(@RequestBody RequestBodyObject requestBody) {
        long startTime = System.nanoTime();

// Код, время выполнения которого вы хотите замерить
        try {
            if (requestBody.point() == null || requestBody.axesX() == null || requestBody.axesY() == null || requestBody.axesZ() == null) {
                log.error("Invalid request format {}", requestBody);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format");
            }
            // log.info(String.valueOf(requestBody));
            var chunksResponse = cubeVisibilityCalculator.getObjects(requestBody);
            String data = gson.toJson(chunksResponse);
            //log.info(data);
            return ResponseEntity.ok(data);
        } catch (Exception e) {

            log.error("Invalid request format {}", requestBody);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format");
        } finally {

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            log.info("Duration: " + duration / 1000000 + " ms");
        }
    }

    @PostMapping("/chunkstr")
    @ResponseBody
    public String getChunks(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return null;
    }


}
