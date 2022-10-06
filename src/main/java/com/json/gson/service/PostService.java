package com.json.gson.service;

import com.json.gson.model.Post;
import com.json.gson.repository.PostRepository;
import com.json.gson.repository.gson.GsonPostRepository;

public class PostService {
    private final PostRepository postRepository;

    public PostService() {
        this.postRepository = new GsonPostRepository();
    }

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(Post post ){
        return postRepository.create(post);
    }
    public Post update(Post post ){
        return postRepository.update(post);
    }
    public void delete(Integer id){
         postRepository.deleteById(id);
    }
}
