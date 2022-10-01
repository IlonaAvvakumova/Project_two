package com.json.gson.controller;

import com.json.gson.model.Label;
import com.json.gson.model.Post;
import com.json.gson.repository.PostRepository;
import com.json.gson.repository.gson.GsonPostRepository;

import java.util.List;


public class PostController {
    private final PostRepository postRepository = new GsonPostRepository();



    public Post createPost(String text) {
        Post post = new Post();
        post.setContent(text);
        postRepository.create(post);
        return post;
    }

    public Post updatePost(Integer id, String text, List<Label> labelList) {
        Post post = new Post();
        post.setId(id);
        post.setContent(text);
        post.setListLab(labelList);
        postRepository.update(post);
        return post;
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }


}

