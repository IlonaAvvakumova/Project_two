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
    private final String GET_ALL_LABELS = "SELECT * FROM posts";
    private final String UPDATE_ALL_LABELS = "UPDATE posts SET content = 'Хороший день' where id = 1;";
    private final String CREATE_ALL_LABELS = " insert into posts (content) values ('Солнечный день');";
    private final String DELETE_ALL_LABELS = " DELETE FROM posts where id = 1;";

    List<Post> posts = new ArrayList<>();
    @Override
    public List<Post> getAll() {


        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_ALL_LABELS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post p = new Post();
                p.setId(resultSet.getInt("id"));
                p.setContent(resultSet.getString("content"));
                /*p.setCreated(resultSet.getLong("created"));
                p.setUpdated(resultSet.getLong("updated"));*/
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
        getAll();
        return posts.stream().filter(a -> a.getId().equals(integer)).findFirst().orElse(null);

    }

    @Override
    public Post create(Post post) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(CREATE_ALL_LABELS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(UPDATE_ALL_LABELS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(DELETE_ALL_LABELS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
