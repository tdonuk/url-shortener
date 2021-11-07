package com.tahadonuk.urlshortener.controller;

import com.tahadonuk.urlshortener.data.entity.URLEntity;
import com.tahadonuk.urlshortener.service.URLService;
import com.tahadonuk.urlshortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UrlController {

    @Autowired
    URLService urlService;
    @Autowired
    UserService userService;

    @PostMapping(path = "/user/{userId}/url/create")
    @ResponseBody
    public ResponseEntity<Object> createUrl(@PathVariable long userId, @RequestBody String longUrl) {
        try {
            URLEntity url = new URLEntity();
            url.setUser(userService.getById(userId));
            url.setUrl(longUrl);

            return ResponseEntity.ok().body(urlService.create(url));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/user/{userId}/url/list")
    @ResponseBody
    public ResponseEntity<List<URLEntity>> getUrlList(@PathVariable long userId, HttpServletRequest request) {
        return ResponseEntity.ok().body(urlService.getByUserId(userId));
    }

    @GetMapping(path = "/user/{userId}/url/detail/{urlId}")
    @ResponseBody
    public ResponseEntity<Object> getUrl(@PathVariable long userId, @PathVariable long urlId) {
        try {
            return ResponseEntity.ok().body(urlService.getById(urlId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/user/{userId}/url/detail/{urlId}")
    @ResponseBody
    public ResponseEntity<Object> deleteUrl(@PathVariable long userId, @PathVariable long urlId) {
        try {
            urlService.deleteById(urlId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/s/{shortenedUrl}")
    public Object redirect(@PathVariable String shortenedUrl) {
        try {
            String url = urlService.getByShortenedUrl(shortenedUrl).getUrl();
            return new ModelAndView("redirect:" + url);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
