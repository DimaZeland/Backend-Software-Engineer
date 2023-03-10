package com.demo.springblog.repository;

import com.demo.springblog.domain.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PostReactiveRepository extends ReactiveCrudRepository<Post, String> {
    Mono<Boolean> existsByTitle(String title);

    Mono<Post> findBySlug(String slug);
}
