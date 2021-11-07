package com.tahadonuk.urlshortener.service;

import com.tahadonuk.urlshortener.data.entity.URLEntity;
import com.tahadonuk.urlshortener.data.repository.URLRepository;
import com.tahadonuk.urlshortener.exception.URLConflictException;
import com.tahadonuk.urlshortener.exception.URLNotFoundException;
import com.tahadonuk.urlshortener.util.ShorteningHelper;
import com.tahadonuk.urlshortener.util.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;

@Service
public class URLService {
    @Autowired
    URLRepository urlRepo;

    public URLEntity create(URLEntity url) throws Exception {

        if(! URLValidator.isValid(url.getUrl())) {
            throw new MalformedURLException("Given string '" + url.getUrl() + "' is not a valid url");
        }

        /*
         * Different users can shorten the same url
         * So check existence by both userId and long Url
         */
        if(! urlRepo.existsByUrlAndUser_UserId(url.getUrl(), url.getUser().getUserId())) {
            urlRepo.save(url);
            url.setShortenedUrl(ShorteningHelper.createShortUrlFromId(url.getUrlId()));
            urlRepo.updateShortenedUrl(url.getUrlId(),url.getShortenedUrl());
            return url;
        } else throw new URLConflictException("A url is already exists with given long url '" + url.getUrl() + "'");

    }

    public URLEntity getById(long id) throws Exception {
        if(urlRepo.existsById(id)) {
            return urlRepo.findById(id).get();
        } else throw new URLNotFoundException("No url found with given id '" + id + "'");
    }

    public List<URLEntity> getByUserId(long userId) {
        return urlRepo.findAllByUser_UserId(userId);
    }

    public void deleteById(long id) throws Exception {
        if(urlRepo.existsById(id)) {
            urlRepo.deleteById(id);
        } else throw new URLNotFoundException("No url found with given id'" + id + "'");
    }

    public URLEntity getByShortenedUrl(String shortenedUrl) throws Exception {
        if(urlRepo.existsByShortenedUrl(shortenedUrl)) {
            return urlRepo.findByShortenedUrl(shortenedUrl).get();
        } else throw new URLNotFoundException("No url found with given shortened url '" + shortenedUrl + "'");
    }
}
