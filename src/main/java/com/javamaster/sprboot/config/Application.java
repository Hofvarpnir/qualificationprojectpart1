package com.javamaster.sprboot.config;

import com.javamaster.service.TestsService;
import org.hibernate.service.spi.InjectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javamaster.entity.Users;
import com.javamaster.service.UserService;
import com.javamaster.entity.Tests;
import com.javamaster.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.javamaster.sprboot.config", "com.javamaster.sprboot.controller", "com.javamaster.service",
        "com.javamaster.repository", "com.javamaster.entity"}) //аннотация которая обозначает, что это spring boot приложение
//а значит, что программа должна быть запущена как spring boot
@Configuration

@EnableJpaRepositories(basePackages = "com.javamaster.repository")

@EntityScan(basePackages = "com.javamaster.entity")

@EnableTransactionManagement

@EnableAutoConfiguration

public class Application {

    @Autowired
    private UserService userService;

    @Autowired
    private TestsService testsService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);//запуск приложения

    }


    @EventListener(ApplicationReadyEvent.class)
    private void testJpaMethods(){
        /*Users users = new Users();
        users.setName("Smith");
        users.setPassword("1234");
        userService.createUsers(users);
        Users users1 = new Users();
        users1.setName("Jon Dorian");
        users1.setPassword("4321");
        userService.createUsers(users1);

        userService.findAll().forEach(it-> System.out.println(it));

        userService.findAllByName("Smith").forEach(it-> System.out.println(it));


        Tests tests = new Tests();
        tests.setQuestion("a + b in cpp");
        tests.setAnswer("int main(){\nint a, b, c;\nc = a + b;\nreturn (c);\n}");
        testsService.createTests(tests);

        testsService.findAll().forEach(it-> System.out.println(it));
        userService.findAll().forEach(it-> System.out.println(it));
        userService.updateLastQuestionById((long) 9458, (long) 1);
        userService.updateLastQuestionById((long) 123, (long) 2);
        userService.findAll().forEach(it-> System.out.println(it));
        userService.updateLastQuestionById((long) 1, (long) 1);
        userService.updateLastQuestionById((long) 1, (long) 2);
        userService.findAll().forEach(it-> System.out.println(it));*/
    }
}