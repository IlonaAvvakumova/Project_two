package com.json.gson.controller;

import com.json.gson.model.Post;
import com.json.gson.model.Writer;
import com.json.gson.repository.WriterRepository;
import com.json.gson.repository.gson.GsonWriterRepository;

import java.util.List;

public class WriterController {
    private final WriterRepository writerRepository = new GsonWriterRepository();



    public Writer createWriter(String text1, String text2) {
        Writer writer = new Writer();
        writer.setFirstName(text1);
        writer.setLastName(text2);
        writerRepository.create(writer);
        return writer;
    }

    public Writer updateWriter(Integer id, String text1,String text2, List<Post>list) {
        Writer writer = new Writer();
        writer.setId(id);
        writer.setFirstName(text1);
        writer.setLastName(text2);
        writer.setWriterList( list);

        writerRepository.update(writer);
        return writer;
    }

    public void deleteWriter(Integer id) {
        writerRepository.deleteById(id);
    }

}
