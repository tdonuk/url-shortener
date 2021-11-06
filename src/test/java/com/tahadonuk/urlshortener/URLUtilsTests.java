package com.tahadonuk.urlshortener;

import com.tahadonuk.urlshortener.util.ShorteningHelper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class URLUtilsTests {
    @Test
    public void shouldReturnShortenedUrl() {
        Assert.assertNotNull(ShorteningHelper.createShortUrlFromId(855936));
    }
}
