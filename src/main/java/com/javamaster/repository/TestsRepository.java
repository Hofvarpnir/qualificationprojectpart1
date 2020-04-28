package com.javamaster.repository;

import com.javamaster.entity.Tests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestsRepository extends JpaRepository<Tests, Long> {

}
