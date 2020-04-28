package com.javamaster.service;

import com.javamaster.entity.Tests;
import com.javamaster.repository.TestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestsService {

    @Autowired
    private final TestsRepository testsRepository;

    public TestsService(TestsRepository testsRepository){
        this.testsRepository = testsRepository;
    }

    public void createTests(Tests tests) {
        testsRepository.save(tests);
    }

    public List<Tests> findAll(){
        return testsRepository.findAll();
    }

    public Tests findById(Long testId){
        return testsRepository.findById(testId).orElse(null);
    }
}
