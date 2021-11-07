package com.tahadonuk.urlshortener.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class URLValidatingTest {

    @Test
    public void givenBadUrl_thenShouldReturnFalse() {
        String badUrl = "dummy-text";
        Assert.assertFalse(URLValidator.isValid(badUrl));
    }
}
