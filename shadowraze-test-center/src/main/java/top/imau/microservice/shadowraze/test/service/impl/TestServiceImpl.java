package top.imau.microservice.shadowraze.test.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.imau.microservice.shadowraze.test.service.TestService;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private RestTemplate restTemplate;

    @Override
    public String getUserNameById(Long id) {
        ResponseEntity<String> responseEntity =  restTemplate.getForEntity("http://USER-CENTER:28080/users/{id}", String.class, id);

        return responseEntity.getBody();
    }
}
