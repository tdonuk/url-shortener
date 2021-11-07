package com.tahadonuk.urlshortener.util;

import com.tahadonuk.urlshortener.util.ShorteningHelper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class URLShorteningTest {

    @Test
    public void givenMaxLong_ShouldWork() {
        String shortUrl = ShorteningHelper.createShortUrlFromId(Long.MAX_VALUE);
        Assert.assertNotNull(shortUrl);
    }

    @Test
    public void givenId_shouldReturnShortenedUrl() {
        Assert.assertNotNull(ShorteningHelper.createShortUrlFromId(855936));
    }
}
