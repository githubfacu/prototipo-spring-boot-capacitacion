package com.example.tienda_demo.services;

import com.example.tienda_demo.domain.Post;
import com.example.tienda_demo.persistance.entities.PersonEntity;
import com.example.tienda_demo.persistance.entities.PostEntity;
import com.example.tienda_demo.persistance.repositories.PersonRepository;
import com.example.tienda_demo.persistance.repositories.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    PostRepository postRepository;
    PersonRepository personRepository;

    public PostServiceImpl(PostRepository postRepository, PersonRepository personRepository){
        this.postRepository = postRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Set<Post> listAllPost(Pageable pageable) {
        return this.postRepository.findAll(pageable)
                .stream()
                .map(postEntity -> new Post(
                        postEntity.getId(),
                        postEntity.getMessage(),
                        postEntity.getPerson().getId()
                ))
                .collect(Collectors.toSet());
    }

    @Override
    public Post savePost(Post post) {

        try {
            System.out.println("ENTRA AL SERVICE");

            PersonEntity personEntity = personRepository.getReferenceById(post.getPersonId());

            PostEntity entity = new PostEntity(
                    null,
                    post.getMessage(),
                    personEntity
            );

            System.out.println("ANTES DE GUARDAR");

            PostEntity saved = postRepository.save(entity); // 💥 ACÁ ROMPE

            System.out.println("DESPUES DE GUARDAR");

            return new Post(
                    saved.getId(),
                    saved.getMessage(),
                    saved.getPerson().getId()
            );

        } catch (Exception e) {
            System.out.println("ERROR EN SAVE:");
            e.printStackTrace(); // 🔥 ESTO ES LO IMPORTANTE
            throw e; // para que siga devolviendo 500
        }
    }

}
