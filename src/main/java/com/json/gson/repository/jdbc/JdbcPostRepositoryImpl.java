package com.json.gson.repository.jdbc;

import com.json.gson.model.Post;
import com.json.gson.model.PostStatus;
import com.json.gson.model.Writer;
import com.json.gson.repository.PostRepository;
import com.json.gson.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class JdbcPostRepositoryImpl implements PostRepository {
    private final String GET_ALL_POSTS = "SELECT * FROM posts";
    private final String GET_POST_BY_ID = "SELECT * FROM posts WHERE id = ?";
    private final String UPDATE_POST = "UPDATE posts SET content= (?), updated=?,  status=? where id = ?;";
    private final String CREATE_POST = "INSERT INTO posts (content,created, updated, status, writer_id) values (?,?, ?,?, ?);";
    private final String DELETE_POST = " DELETE FROM posts where id = ?;";


    private Post convertFromResult(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                Post p = new Post();
                p.setId(resultSet.getInt("id"));
                p.setContent(resultSet.getString("content"));
                p.setCreated(resultSet.getLong("created"));
                p.setUpdated(resultSet.getLong("updated"));

               // p.setWriter((Writer) resultSet.getObject("writer_id"));
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

        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_POST_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return (convertFromResult(resultSet));
            }
            return null;
        } catch (SQLException throwables) {
            System.out.println("Error occurred: " + throwables.getMessage());
            return null;
        }
    }

    @Override
    public Post create(Post post) {

        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(CREATE_POST)) {
            long time = LocalDateTime.now().getNano() * 1000L;
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setLong(2, time);
            preparedStatement.setLong(3, time);
            preparedStatement.setString(4, post.getStatus().name());
            preparedStatement.setInt(5, post.getWriter().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                post.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            post.setCreated(time);
            post.setUpdated(time);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(UPDATE_POST)) {
            long time = LocalDateTime.now().getNano() * 1000L;
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setLong(2, time);
            preparedStatement.setString(3, post.getStatus().name());
            preparedStatement.setInt(4, post.getId());

            preparedStatement.executeUpdate();
             post.setUpdated(time);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (PreparedStatement preparedStatement2 = JdbcUtils.createStatement("SELECT * FROM posts WHERE id = "+ post.getId()+" ;");
             ResultSet resultSet = preparedStatement2.executeQuery()) {
            while (resultSet.next()) {
                post.setCreated(resultSet.getLong("created"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post deleteById(Integer id, Post post) {

        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(DELETE_POST)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            return post;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
