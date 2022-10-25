package com.json.gson.controller;

import com.json.gson.model.Post;
import com.json.gson.model.Writer;
import com.json.gson.service.WriterService;

import java.util.List;

public class WriterController {
    private final WriterService writerService = new WriterService();

    public Writer createWriter(String text1, String text2) {
        Writer writer = new Writer();
        writer.setFirstName(text1);
        writer.setLastName(text2);
        writerService.create(writer);
        return writer;
    }

    public Writer updateWriter(Integer id, String text1, String text2, List<Post> list) {
        Writer writer = new Writer();
        writer.setId(id);
        writer.setFirstName(text1);
        writer.setLastName(text2);
        writer.setPosts(list);

        writerService.update(writer);
        return writer;
    }

    public void deleteWriter(Integer id) {
        writerService.delete(id);
    }

    public List<Writer> getAll() {
        return writerService.getAll();
    }

    public Writer getById(Integer id) {
        return writerService.getById(id);
    }
}
