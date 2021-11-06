package com.tahadonuk.urlshortener.data.repository;

import com.tahadonuk.urlshortener.data.entity.URLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URLEntity, Long> {
    @Modifying
    @Transactional
    @Query("update URLEntity url set url.shortenedUrl = :shortenedUrl where url.urlId = :urlId")
    void updateShortenedUrl(@Param("urlId") long urlId, @Param("shortenedUrl") String shortenedUrl);

    // Find by long url
    boolean existsByUrlAndUser_UserId(String url, long user_userId);
    Optional<URLEntity> findByUrlAndUser_UserId(String url, long userId);

    // Find by user information
    List<URLEntity> findAllByUser_UserId(long userId);
    List<URLEntity> findAllByUser_Username(String user_username);

    // Find by shortened url
    boolean existsByShortenedUrl(String shortenedUrl);
    Optional<URLEntity> findByShortenedUrl(String shortenedUrl);

}
