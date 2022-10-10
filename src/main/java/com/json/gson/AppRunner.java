package com.json.gson;

import com.json.gson.jdbc.JdbcLabelRepositoryImpl;
import com.json.gson.model.Label;

import java.util.List;

public class AppRunner {
    public static void main(String[] args) {

        JdbcLabelRepositoryImpl jdbcLabelRepository = new JdbcLabelRepositoryImpl();
        List<Label> lll = jdbcLabelRepository.getAll();
        System.out.println(lll);

    }
}
