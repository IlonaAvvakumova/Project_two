package com.json.gson;

import com.json.gson.model.Post;
import com.json.gson.model.Writer;
import com.json.gson.repository.jdbc.JdbcLabelRepositoryImpl;
import com.json.gson.model.Label;
import com.json.gson.repository.jdbc.JdbcPostRepositoryImpl;
import com.json.gson.repository.jdbc.JdbcWriterRepositoryImpl;
import com.json.gson.view.MainView;

import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
/*
        JdbcLabelRepositoryImpl jdbcLabelRepository = new JdbcLabelRepositoryImpl();
        // List<Label> lll = jdbcLabelRepository.getAll();
        Label jhgj = new Label();
        Label l = jdbcLabelRepository.getById(3);
        System.out.println(l);*/
      /*  MainView mainView = new MainView();
        mainView.menu();*/

       /* JdbcPostRepositoryImpl jdbcLabelRepository = new JdbcPostRepositoryImpl();
        // List<Label> lll = jdbcLabelRepository.getAll();
        Post jhgj = new Post();
        jdbcLabelRepository.deleteById(1);*/
    JdbcWriterRepositoryImpl jdbcLabelRepository = new JdbcWriterRepositoryImpl();
        // List<Label> lll = jdbcLabelRepository.getAll();
        Writer jhgj = new Writer();
       jdbcLabelRepository.deleteById(1);
     //   System.out.println(w);

    }
}
