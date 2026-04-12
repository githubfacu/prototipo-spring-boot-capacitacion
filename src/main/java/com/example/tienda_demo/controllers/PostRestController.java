package com.example.tienda_demo.controllers;

import com.example.tienda_demo.domain.Post;
import com.example.tienda_demo.services.PostService;
import com.example.tienda_demo.validators.groups.OnCreate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/post")
public class PostRestController {
    private PostService postService;
    public PostRestController(PostService postService){
        this.postService = postService;
    }
    @GetMapping
    public ResponseEntity<?> getAllPosts (
            @PageableDefault(size = 10)
            Pageable pageable){
        return ResponseEntity.ok(postService.listAllPost(pageable));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(
            @RequestBody @Validated(OnCreate.class) Post post){
        Post saved = postService.savePost(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }


}
