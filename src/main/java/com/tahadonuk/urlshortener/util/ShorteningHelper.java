package com.tahadonuk.urlshortener.util;

public class ShorteningHelper {

    private ShorteningHelper(){} // private constructor

    public static final String ALPHABET = "zTuprEQc21htfHjA0qF3SZbO5yPkIdXWosUeL67aiJCxmRGMKVnN8B4lgYDvw9"; //Deranged alphanumeric characters
    public static final int BASE = ALPHABET.length();

    //10luk tabandaki sayı olan id, 62 tabanlı karakter dizisi ile şifrelenir
    public static String createShortUrlFromId(long id) {
        StringBuilder shortUrl = new StringBuilder();
        int mod;
        while(id > 0) {
            mod = (int) id % BASE;
            shortUrl.insert(0,ALPHABET.charAt(mod));
            id = id / BASE;
        }
        return shortUrl.toString();
    }
}
