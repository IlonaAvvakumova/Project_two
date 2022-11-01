package com.json.gson.controller;

import com.json.gson.model.Label;
import com.json.gson.model.Post;
import com.json.gson.model.Writer;
import com.json.gson.service.PostService;
import java.util.List;

public class PostController {
    private final PostService postService = new PostService();

    public Post createPost(String text, List<Label> labels, Writer writer) {
        Post post = new Post();
        post.setContent(text);
        post.setLabels(labels);
        post.setWriter(writer);
        postService.create(post);
        return post;
    }

    public Post updatePost(Integer id, String text, List<Label> labelList) {
        Post post = new Post();
        post.setId(id);
        post.setContent(text);
       post.setLabels(labelList);
        postService.update(post);
        return post;
    }

    public void deletePost(Integer id) {
         postService.delete(id);
    }

    public List<Post> getAll() {
        return postService.getAll();
    }
    public Post getById(Integer id){
        return  postService.getById(id);
    }
}




