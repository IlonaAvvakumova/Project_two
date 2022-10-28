package com.json.gson.repository.jdbc;

import com.json.gson.model.Label;
import com.json.gson.model.Post;

import com.json.gson.model.PostStatus;

import com.json.gson.repository.PostRepository;
import com.json.gson.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class JdbcPostRepositoryImpl implements PostRepository {
  //  private final String GET_ALL_POSTS = "SELECT * FROM posts";
    private final String GET_ALL_POSTS = "SELECT *FROM posts as p" +
          " LEFT JOIN post_labels as pl on p.id = pl.post_id" +
          " LEFT JOIN labels as l on pl.label_id = l.id";
 //   private final String GET_POST_BY_ID = "SELECT * FROM posts WHERE id = ?";
    private final String GET_POST_BY_ID = "SELECT *FROM posts as p" +
         " LEFT JOIN post_labels as pl on p.id = pl.post_id " +
         "LEFT JOIN labels as l on pl.label_id = l.id WHERE p.id =?;";
    private final String UPDATE_POST = "UPDATE posts SET content= (?), updated=?,  status=? where id = ?;";
    private final String CREATE_POST = "INSERT INTO posts (content,created, updated, status, writer_id) VALUES (?,?, ?,?, ?);";
    private final String CREATE_POST_LABEL = "INSERT INTO post_labels (post_id, label_id) VALUES (?,?);";
    private final String UPDATE_POST_LABEL = "UPDATE post_labels SET post_id = ?, label_id=? where post_id = ?;";
    private final String DELETE_POST = " DELETE FROM posts where id = ?;";


    private Post convertFromResult(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                Post p = new Post();
                p.setId(resultSet.getInt("id"));
                p.setContent(resultSet.getString("content"));
                p.setCreated(resultSet.getLong("created"));
                p.setUpdated(resultSet.getLong("updated"));
                p.setStatus(PostStatus.valueOf(resultSet.getString("status")));
                p.setWriterID(resultSet.getInt("writer_id"));
                p.setPostId(resultSet.getInt("post_id"));
                p.setLabelId(resultSet.getInt("label_id"));
                p.setNameLabel(resultSet.getString("name"));
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
            if (resultSet.next()) {

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

            for (Label l : post.getLabels()
            ) {
                try (PreparedStatement preparedStatementGetID = JdbcUtils.createStatement(CREATE_POST_LABEL)) {

                    preparedStatementGetID.setInt(1, post.getId());
                    preparedStatementGetID.setInt(2, l.getId());
                    preparedStatementGetID.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            post.setCreated(time);
            post.setUpdated(time);
            post.setLabels(post.getLabels());
            post.setWriter(post.getWriter());

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
            for (Label l : post.getLabels()
            ) {
                try (PreparedStatement preparedStatementGetID = JdbcUtils.createStatement(UPDATE_POST_LABEL)) {

                    preparedStatementGetID.setInt(1, post.getId());
                    preparedStatementGetID.setInt(2, l.getId());
                    preparedStatementGetID.setInt(3, post.getId());
                    preparedStatementGetID.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            post.setUpdated(time);
            post.setLabels(post.getLabels());
            post.setWriter(post.getWriter());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (PreparedStatement preparedStatement2 = JdbcUtils.createStatement("SELECT * FROM posts WHERE id = " + post.getId() + " ;");
             ResultSet resultSet = preparedStatement2.executeQuery()) {
            while (resultSet.next()) {
                post.setCreated(resultSet.getLong("created"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post deleteById(Integer id, Post post) {

        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(DELETE_POST)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return post;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
