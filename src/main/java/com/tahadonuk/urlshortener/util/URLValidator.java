package com.tahadonuk.urlshortener.util;

import java.net.URL;

public class URLValidator {
    private URLValidator() {

    }

    public static boolean isValid(String urlString) {
        try {
            new URL(urlString).toURI(); // if an exception not occurs in this process, then the url is probably valid
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
