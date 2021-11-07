package com.tahadonuk.urlshortener.controller;

import com.tahadonuk.urlshortener.controller.UrlController;
import com.tahadonuk.urlshortener.exception.URLConflictException;
import com.tahadonuk.urlshortener.exception.URLNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.MalformedURLException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class URLControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    UrlController controller;

    @Test
    public void shouldNotNull() {
        Assert.assertNotNull(controller);
    }

    @Test
    public void givenBadUrlId_thenShouldReturnError_andThrowException() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/user/{userId}/url/detail/{urlId}",2,13135))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            Assert.assertTrue(e instanceof URLNotFoundException);
        }
    }

    @Test
    public void givenUserId_andGivenUrlId_thenShouldReturnUrlDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/{userId}/url/detail/{urlId}",2,1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("urlId").exists());
    }

    @Test
    public void givenBadUrl_thenShouldReturnError_andThrowException() {
        try {
            mvc.perform(MockMvcRequestBuilders.post("/user/{userId}/url/create",2)
                    .contentType(MediaType.APPLICATION_JSON).content("dummy text").accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            Assert.assertTrue(e instanceof MalformedURLException);
        }
    }

    @Test
    public void givenLongUrl_thenShouldSave_andCreateShortenedUrl_andReturnUrlDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/user/{userId}/url/create",2)
                .contentType(MediaType.APPLICATION_JSON).content("https://www.google.com").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("urlId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("shortenedUrl").exists());
    }

    @Test
    public void givenDuplicatedUrlWithId_thenShouldReturnError_andThrowException() {
        try {
            mvc.perform(MockMvcRequestBuilders.post("/user/{userId}/url/create",2)
                    .contentType(MediaType.APPLICATION_JSON).content("duplicated-dumb-url").accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());

            mvc.perform(MockMvcRequestBuilders.post("/user/{userId}/url/create",2)
                    .contentType(MediaType.APPLICATION_JSON).content("duplicated-dumb-url").accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            Assert.assertTrue(e instanceof URLConflictException);
        }
    }

    @Test
    public void givenBadShortenedUrl_thenShouldNotRedirect_andReturnError_andThrowException() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/s/{shortUrl}","dummy-url").accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            Assert.assertTrue(e instanceof URLNotFoundException);
        }
    }

    @Test
    public void givenUserId_thenShouldReturnUrlList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/{userId}/url/list",2).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

}
