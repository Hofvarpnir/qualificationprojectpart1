package com.javamaster.sprboot.controller;

import com.javamaster.entity.Tests;
import com.javamaster.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.javamaster.service.UserService;
import org.springframework.ui.Model;
import com.javamaster.service.TestsService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private TestsService testsService;

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/loginfailed")
    public String getLoginFailed() {
        return "loginfailed";
    }


    @RequestMapping(value="/loginokay", params = {"id", "last"}, method=RequestMethod.GET)
    public String getLoginOkay(Model model, @RequestParam("id") long id, @RequestParam("last") long last) {
        Tests test = testsService.findById(last);
        if (test != null) {
            model.addAttribute("question", test.getQuestion());
            model.addAttribute("number", last);
            return "loginokay";
        }
        else
            return "index";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String postLogin(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        Users user = userService.findByNameAndPassword(username, password);
        if (user == null)
            return "redirect:/loginfailed";
        else
            return "redirect:/loginokay?id=" + user.getId() +"&last=" + user.getLastQuestion();
    }

    /*@RequestMapping(value="/get_answer", params = {"id", "answer_part"}, method= RequestMethod.GET)
    @ResponseBody
    public String getAnswer(@RequestParam("id") long id, @RequestParam("answer_part") long answer_part) {
        Tests test = testsService.findById(id);
        if (test != null) {
            return test.getAnswer();
        }
        else
            return "error";
    }*/

    @RequestMapping(value="/get_answer", params = {"id", "answer_part"}, method= RequestMethod.GET)
    @ResponseBody
    public String getAnswer(@RequestParam("id") long id, @RequestParam("answer_part") long answer_part) {
        Tests test = testsService.findById(id);
        if (test != null) {
            return test.getAnswerByMetric(answer_part);
        }
        else
            return "question error";
    }

    @RequestMapping(value="/next_question", params = {"id", "user_id"}, method= RequestMethod.GET)
    @ResponseBody
    public String getNextQuestion(@RequestParam("id") long id, @RequestParam("user_id") long user_id) {
        Tests test = testsService.findById(id);
        if (test != null) {
            userService.updateLastQuestionById(id, user_id);
            return test.getQuestion();
        }
        else
            return "No such question";
    }

    @RequestMapping(value="/prev_question", params = {"id", "user_id"}, method= RequestMethod.GET)
    @ResponseBody
    public String getPrevQuestion(@RequestParam("id") long id, @RequestParam("user_id") long user_id) {
        Tests test = testsService.findById(id);
        if (test != null) {
            userService.updateLastQuestionById(id, user_id);
            return test.getQuestion();
        }
        else
            return "No such question";
    }
}
