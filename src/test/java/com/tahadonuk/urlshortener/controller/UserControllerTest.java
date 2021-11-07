package com.tahadonuk.urlshortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tahadonuk.urlshortener.controller.UserController;
import com.tahadonuk.urlshortener.exception.UserConflictException;
import com.tahadonuk.urlshortener.exception.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    UserController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldNotNull() {
        Assert.assertNotNull(controller);
    }

    @Test
    public void givenBadUserId_thenReturnError_andThrowException() {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}",547851).accept(MediaType.APPLICATION_JSON))
                    .andDo(result -> MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            Assert.assertTrue(e instanceof UserNotFoundException);
        }
    }

    @Test
    public void givenDuplicatedUsername_thenReturnError_andThrowException() {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{" +
                    "\"username\":\"testuser\"," +
                    "\"password\":\"testpassword\"" +
                    "}")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());

            this.mockMvc.perform(MockMvcRequestBuilders.post("/user/signup").contentType(MediaType.APPLICATION_JSON).content("{" +
                    "\"username\":\"testuser\"," +
                    "\"password\":\"testpassword\"" +
                    "}").accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }
         catch (Exception e) {
            Assert.assertTrue(e instanceof UserConflictException);
        }
    }

    @Test
    public void givenUserId_thenShouldReturnUserInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}",1).accept(MediaType.APPLICATION_JSON))
                .andDo(result -> MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenUserInfo_thenShouldSaveAndReturnUserId() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/signup").contentType(MediaType.APPLICATION_JSON).content("{" +
                "\"username\":\"testuser\"," +
                "\"password\":\"testpassword\"" +
                "}").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("userId").exists());
    }
}
