package top.imau.microservice.shadowraze.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.imau.microservice.shadowraze.test.service.TestService;

import javax.annotation.Resource;

@RestController
@RequestMapping("tests")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("{id}")
    public String getTestResult(@PathVariable Long id) {

        return testService.getUserNameById(id);
    }
}
