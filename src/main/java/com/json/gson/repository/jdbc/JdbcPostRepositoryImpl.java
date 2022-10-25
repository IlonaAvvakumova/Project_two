package com.json.gson.repository.jdbc;

import com.json.gson.model.Label;
import com.json.gson.model.Post;
import com.json.gson.model.Writer;
import com.json.gson.repository.PostRepository;
import com.json.gson.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository {
    private final String GET_ALL_POSTS = "SELECT * FROM posts";
    private final String GET_POST_BY_ID = "SELECT * FROM posts WHERE id = %d";
    private final String UPDATE_POST = "UPDATE posts SET content = ('%s') where id = %d;";
    private final String CREATE_POST = "INSERT INTO posts (content) values ('%s');";
    private final String DELETE_POST = " DELETE FROM posts where id = %d;";


    private Post convertFromResult(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                Post p = new Post();
                p.setId(resultSet.getInt("id"));
                p.setContent(resultSet.getString("content"));
                return p;
            } catch (SQLException throwables) {
                System.out.println("Error occurred: " + throwables.getMessage());
                return null;
            }
        } else System.out.println("Нет данных для ввода");
        return null;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_ALL_POSTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
                       while (resultSet.next()) {
                posts.add(convertFromResult(resultSet));
            }

            return posts;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Post getById(Integer id) {
        String sql = String.format(GET_POST_BY_ID, id);
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return (convertFromResult(resultSet));
        } catch (SQLException throwables) {
            System.out.println("Error occurred: " + throwables.getMessage());
            return null;
        }

    }

    @Override
    public Post create(Post post) {

        String sql = String.format(CREATE_POST, post.getContent()) ;
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(sql)) {
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                post.setId(generatedKeys.getInt(1));

            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return post;
    }

    @Override
    public Post update(Post post) {
        String sql = String.format(UPDATE_POST, post.getContent(), post.getId());
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(sql)) {
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return post;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = String.format(DELETE_POST, id);
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }
}
