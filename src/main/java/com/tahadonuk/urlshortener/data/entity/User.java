package com.tahadonuk.urlshortener.data.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "USERNAME", unique = true)
    @NotNull
    private String username;

    @Column(name = "PASSWORD")
    @NotNull
    private char[] password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private List<URLEntity> urls;

    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public long getUserId() {
        return userId;
    }

    public List<URLEntity> getUrls() {
        return urls;
    }

    public void setUrls(List<URLEntity> urls) {
        this.urls = urls;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
