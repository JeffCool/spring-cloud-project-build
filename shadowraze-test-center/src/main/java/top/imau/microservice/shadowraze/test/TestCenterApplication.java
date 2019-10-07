package top.imau.microservice.shadowraze.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TestCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCenterApplication.class, args);
    }

}
