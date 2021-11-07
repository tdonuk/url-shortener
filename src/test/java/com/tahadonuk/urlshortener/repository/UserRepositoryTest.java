package com.tahadonuk.urlshortener.repository;

import com.tahadonuk.urlshortener.data.entity.URLEntity;
import com.tahadonuk.urlshortener.data.entity.User;
import com.tahadonuk.urlshortener.data.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest {
    @Autowired
    UserRepository userRepo;

    @Test
    public void shouldNotNull() {
        Assert.assertNotNull(userRepo);
    }

    @Test
    public void givenUsernameAndPassword_thenInsert_andCreateId() {
        User u = new User("testuser", "testpassword".toCharArray());

        userRepo.save(u);

        Assert.assertNotNull(u.getUserId());
        Assert.assertNotNull(u.getUrls());
    }
}
