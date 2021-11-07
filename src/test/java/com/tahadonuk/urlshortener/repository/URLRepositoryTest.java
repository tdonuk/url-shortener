package com.tahadonuk.urlshortener.repository;

import com.tahadonuk.urlshortener.data.entity.URLEntity;
import com.tahadonuk.urlshortener.data.entity.User;
import com.tahadonuk.urlshortener.data.repository.URLRepository;
import com.tahadonuk.urlshortener.data.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class URLRepositoryTest {

    @Autowired
    URLRepository urlRepo;

    @Autowired
    UserRepository userRepo;

    @Test
    public void shouldNotNull() {
        Assert.assertNotNull(urlRepo);
    }

    @Test
    public void givenLongUrl_thenInsert_andCreateShortenedUrl() {
        URLEntity url = new URLEntity("https://www.google.com", userRepo.findById(2L).orElseThrow());

        urlRepo.save(url);

        Assert.assertNotNull(url.getUrlId());
        Assert.assertNotNull(url.getUrl());
        Assert.assertNotNull(url.getUser().getUserId());
    }

}
