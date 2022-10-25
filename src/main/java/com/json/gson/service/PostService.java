package com.json.gson.service;

import com.json.gson.model.Post;
import com.json.gson.repository.PostRepository;
import com.json.gson.repository.jdbc.JdbcPostRepositoryImpl;
import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    public PostService() {
        this.postRepository = new JdbcPostRepositoryImpl();
    }

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(Post post) {
        return postRepository.create(post);
    }

    public Post update(Post post) {
        return postRepository.update(post);
    }

    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }
    public Post getById(Integer id){
        return postRepository.getById(id);
    }
}
