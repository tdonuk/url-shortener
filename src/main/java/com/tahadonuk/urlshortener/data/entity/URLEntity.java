package com.tahadonuk.urlshortener.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "urls")
public class URLEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "URL_ID")
    private long urlId;

    @Column(name = "LONG_URL")
    private String  url;

    @Column(name = "SHORTENED_URL")
    private String shortenedUrl;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JsonIgnore // this is required to prevent it from returning a recursive json loop
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUrlId() {
        return urlId;
    }

    public void setUrlId(long urlId) {
        this.urlId = urlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }
}
