package com.javamaster.service;

import com.javamaster.entity.Users;
import com.javamaster.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public void createUsers(Users users) {
        usersRepository.save(users);
    }

    public List<Users> findAll(){
        return usersRepository.findAll();
    }

    public Users findById(Long userId){
        return usersRepository.findById(userId).orElse(null);
    }

    public List<Users> findAllByName(String name){
        return usersRepository.findAllByName(name);
    }

    public Users findByNameAndPassword(String name, String password){
        return usersRepository.findByNameAndPassword(name, password);
    }

    public int updateLastQuestionById(Long last_question, Long id){
        return usersRepository.updateLastQuestionById(last_question, id);
    }
}
