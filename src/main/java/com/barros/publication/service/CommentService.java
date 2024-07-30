package com.barros.publication.service;

import com.barros.publication.client.CommentClient;
import com.barros.publication.domain.Comment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentClient commentClient;

    @Autowired
    private RedisService redisService;

    @CircuitBreaker(name = "comments", fallbackMethod = "getCommentsFallback")
    public List<Comment> getComments(String id) {
        final List<Comment> comments = commentClient.getComments(id);
        redisService.save(comments, id);
        return comments;
    }

    private List<Comment> getCommentsFallback(String id, Throwable cause) {
        log.warn("[WARN] Fallback with id {}", id);
        return redisService.findById(id);
    }
}
