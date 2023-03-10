package com.demo.springblog.service;

import com.demo.springblog.domain.Post;
import com.demo.springblog.dto.PostDto;
import com.demo.springblog.mapper.PostMapper;
import com.demo.springblog.repository.PostReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostReactiveRepository postReactiveRepository;
    private final PostMapper postMapper;

    public Mono<PostDto> save(PostDto postDto) {
        Post post = postMapper.mapToPost(postDto);
        post.setCreatedOn(LocalDateTime.now());
        post.setUpdatedOn(LocalDateTime.now());
        return postReactiveRepository.save(post).map(p -> {
                    postDto.setId(p.getId());
                    return postDto;
                }
        );
    }

    public Flux<PostDto> findAllPosts() {
        return postReactiveRepository.findAll()
                .map(postMapper::mapToPostDto)
                .switchIfEmpty(Flux.empty());
    }

    public Boolean postExistsWithTitle(String title) {
        return postReactiveRepository.existsByTitle(title).block();
    }

    public Mono<PostDto> update(PostDto postDto) {
        return postReactiveRepository.findById(postDto.getId())
                .flatMap(savedPost -> {
                    Post post = postMapper.mapToPost(postDto);
                    post.setId(savedPost.getId());
                    return postReactiveRepository.save(post);
                })
                .map(postMapper::mapToPostDto);
    }

    public Mono<Void> delete(Long id) {
        return postReactiveRepository.deleteById(id);
    }

    public Mono<PostDto> findBySlug(String slug) {
        Mono<Post> postMono = postReactiveRepository.findBySlug(slug);
        return postMono.map(postMapper::mapToPostDto);
    }
}
