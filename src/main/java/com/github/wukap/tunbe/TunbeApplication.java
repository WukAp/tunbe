package com.github.wukap.tunbe;

import com.github.wukap.tunbe.testDate.TestData;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TunbeApplication {

    @SneakyThrows
    public static void main(String[] args) {

        var app = SpringApplication.run(TunbeApplication.class, args);

        	TestData data = new TestData(app);
        	data.generateDataInBd();
        // ElementChunkRepository elementChunkRepository = app.getBean(ElementChunkRepository.class);
//		var ans = elementChunkRepository.findElementChunksInBoundingBox(0, 10, 0, 10, 0, 10);
//
//		ans.stream().forEach(en->{
//			System.out.println(en.getId().getChunkId().getX() + " " + en.getId().getChunkId().getY() + " " + en.getId().getChunkId().getZ());
//			System.out.println(en.getId().getChunkId());
//			System.out.println();
//		});
        //TestData testData = new TestData();
        //testData.generateDataInBd(app);
    }

}
