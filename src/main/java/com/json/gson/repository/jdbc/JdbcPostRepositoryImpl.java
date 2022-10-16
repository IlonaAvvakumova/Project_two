package com.json.gson.repository.jdbc;

import com.json.gson.model.Post;
import com.json.gson.repository.PostRepository;
import com.json.gson.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository {
    private final String GET_ALL_LABELS = "SELECT * FROM labels";
    @Override
    public List<Post> getAll() {

        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_ALL_LABELS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post p = new Post();
                p.setId(resultSet.getInt("id"));
                p.setContent(resultSet.getString("Content"));
                p.setCreated(resultSet.getLong("Created"));
                p.setUpdated(resultSet.getLong("Updated"));
                posts.add(p);
            }
            preparedStatement.close();
            resultSet.close();
            return posts;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Post getById(Integer integer) {

        return null;
    }

    @Override
    public Post create(Post post) {
        return null;
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
