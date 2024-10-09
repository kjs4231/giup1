package com.example.giup1.controller;

import com.example.giup1.entity.TestEntity;
import com.example.giup1.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/test")
    public TestEntity createTestEntity(@RequestParam String name) {
        return testService.saveTestEntity(name);
    }

    @GetMapping("/test")
    public List<TestEntity> getTestEntities() {
        return testService.getAllTestEntities();
    }
}
