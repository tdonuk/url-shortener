package com.tahadonuk.urlshortener.controller;

import com.tahadonuk.urlshortener.data.entity.User;
import com.tahadonuk.urlshortener.exception.UserConflictException;
import com.tahadonuk.urlshortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/user/signup")
    @ResponseBody
    public ResponseEntity<Object> signUp(@RequestBody User user, HttpServletRequest request) {
        System.out.println(request.getRequestURL());
        try {
            userService.saveUser(user);
            return ResponseEntity.ok().body(user);
        } catch (UserConflictException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable long userId) {
        try {
            return ResponseEntity.ok().body(userService.getById(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
