package com.github.wukap.tunbe.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Camera {
    private final double maxViewDistance;
    private final double maxLod1;
    private final double maxLod2;

    public Camera(@Value("${camera.maxViewDistance}") double maxViewDistance, @Value("${camera.maxLod1}") double maxLod1, @Value("${camera.maxLod2}") double maxLod2) {
        this.maxViewDistance = maxViewDistance;
        this.maxLod1 = maxLod1;
        this.maxLod2 = maxLod2;
    }
}
