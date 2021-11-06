package com.tahadonuk.urlshortener;

import com.tahadonuk.urlshortener.data.entity.URLEntity;
import com.tahadonuk.urlshortener.data.repository.URLRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class URLEntityTest {

    @Autowired
    URLRepository urlRepo;

    @Test
    public void shouldGetShortenedUrlWhenInsert() {
        URLEntity url = new URLEntity();
        url.setUrl("https://www.google.com");

        urlRepo.save(url);

        Assert.assertNotNull(url.getShortenedUrl());

    }

    @Test
    public void shouldGetUrlIdWhenInsert() {
        URLEntity url = new URLEntity();
        url.setUrl("https://www.google2.com");

        urlRepo.save(url);

        Assert.assertNotNull(url.getUrlId());

    }

}
