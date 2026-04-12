package com.example.tienda_demo.services;

import com.example.tienda_demo.domain.Post;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PostService {

    public Set<Post> listAllPost(Pageable pageable);
    public Post savePost(Post post);
}
