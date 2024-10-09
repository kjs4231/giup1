package com.example.giup1.service;

import com.example.giup1.entity.TestEntity;
import com.example.giup1.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public TestEntity saveTestEntity(String name) {
        TestEntity entity = new TestEntity(name);
        return testRepository.save(entity);
    }

    public List<TestEntity> getAllTestEntities() {
        return testRepository.findAll();
    }
}
