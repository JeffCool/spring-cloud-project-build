package top.imau.microservice.shadowraze.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class TestController {

    @GetMapping("{id}")
    public Map<String, Object> testRestful(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", "jeff");
        map.put("gender", "男");
        return map;
    }
}
