package com.javamaster.repository;

import com.javamaster.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    List<Users> findAllByName(String name);
    Users findByNameAndPassword(String name, String password);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query("update Users u set u.last_question = :last_question where u.id = :id")
    int updateLastQuestionById(@Param("last_question") Long last_question, @Param("id") Long id);

}
