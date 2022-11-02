package com.json.gson.repository.jdbc;

import com.json.gson.model.Label;
import com.json.gson.model.Post;

import com.json.gson.model.PostStatus;

import com.json.gson.model.Writer;
import com.json.gson.repository.PostRepository;
import com.json.gson.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JdbcPostRepositoryImpl implements PostRepository {
    //  private final String GET_ALL_POSTS = "SELECT * FROM posts";
    private final String GET_ALL_POSTS = "SELECT p.*, w.*, l.* FROM posts as p " +
            "LEFT JOIN post_labels as pl on p.id = pl.post_id " +
            "LEFT JOIN labels as l on pl.label_id = l.id " +
            "LEFT JOIN writers as w on p.writer_id = w.id ;";
    //   private final String GET_POST_BY_ID = "SELECT * FROM posts WHERE id = ?";
    private final String GET_POST_BY_ID = "SELECT p.*, w.*, l.* FROM posts as p " +
            "LEFT JOIN post_labels as pl on p.id = pl.post_id " +
            "LEFT JOIN labels as l on pl.label_id = l.id " +
            "LEFT JOIN writers as w on p.writer_id = w.id " +
            "WHERE p.id = ?;";
    private final String GET = "Select id from posts; ";
    private final String UPDATE_POST = "UPDATE posts SET content= (?), updated=?,  status=? where id = ?;";
    //private final String UPDATE_POST_LABEL = "UPDATE post_labels SET label_id= (?) where post_id = ?;";
     private final String UPDATE_POST_LABEL = "DELETE post_labels FROM post_labels where post_id = ?;";
    private final String CREATE_POST = "INSERT INTO posts (content,created, updated, status, writer_id) VALUES (?,?,?,?,?);";
    private final String CREATE_POST_LABEL = "INSERT INTO post_labels (post_id, label_id) VALUES (?,?);";
    private final String DELETE_POST = "UPDATE posts SET status= (?) where id = ?;";

    private Post convertFromResult(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                Writer writer = new Writer();
                writer.setId(resultSet.getInt("w.id"));
                writer.setFirstName(resultSet.getString("w.first_name"));
                writer.setLastName(resultSet.getString("w.last_name"));

                Post p = new Post();
                p.setId(resultSet.getInt("id"));
                p.setContent(resultSet.getString("content"));
                p.setCreated(resultSet.getLong("created"));
                p.setUpdated(resultSet.getLong("updated"));
                p.setStatus(PostStatus.valueOf(resultSet.getString("status")));
                p.setWriter(writer);

                List<Label> labelList = new ArrayList<>();
                resultSet.last();
                int row = resultSet.getRow();
                resultSet.first();
                while (row != 0) {
                    Label l = new Label();
                    l.setId(resultSet.getInt("l.id"));
                    l.setName(resultSet.getString("l.name"));
                    if (l.getId() != 0)
                        labelList.add(l);
                    row--;
                    resultSet.next();
                }

                p.setLabels(labelList);

                return p;
            } catch (SQLException throwables) {
                System.out.println("Error occurred: " + throwables.getMessage());
                return null;
            }
        } else System.out.println("Нет данных для ввода");
        return null;
    }

    /* @Override
     public List<Post> getAll() {
         List<Post> posts = new ArrayList<>();
         try (PreparedStatement preparedStatement = JdbcUtils.createStatement2(GET_ALL_POSTS, ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_UPDATABLE);
              ResultSet resultSet = preparedStatement.executeQuery()) {
             while (resultSet.next()) {
                 posts.add(convertFromResult(resultSet));
             }
             return posts;
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
         return null;
     }*/
/*    private Post convertFromResult2(Map<Integer, Post> posts, ResultSet resultSet) {

        if (resultSet != null) {
            try {
                if (posts.containsKey(resultSet.getInt("id"))) {
                    Post p = posts.get(resultSet.getInt("id"));
                    Map<Integer, String> labelmap = p.getMapLab();
                    Label l = new Label();
                    l.setId(resultSet.getInt("l.id"));
                    l.setName(resultSet.getString("l.name"));
                    labelmap.put(l.getId(), l.getName());
                    p.setMapLab(labelmap);
                    posts.put(resultSet.getInt("id"), p);
                    return p;
                } else {
                    Map<Integer, String> labelMap = new HashMap();
                    Writer writer = new Writer();
                    writer.setId(resultSet.getInt("w.id"));
                    writer.setFirstName(resultSet.getString("w.first_name"));
                    writer.setLastName(resultSet.getString("w.last_name"));

                    Post p = new Post();
                    p.setId(resultSet.getInt("id"));
                    p.setContent(resultSet.getString("content"));
                    p.setCreated(resultSet.getLong("created"));
                    p.setUpdated(resultSet.getLong("updated"));
                    p.setStatus(PostStatus.valueOf(resultSet.getString("status")));
                    p.setWriter(writer);

                    Label l = new Label();
                    l.setId(resultSet.getInt("l.id"));
                    l.setName(resultSet.getString("l.name"));

                    labelMap.put(l.getId(), l.getName());
                    p.setMapLab(labelMap);
                    return p;
                }

            } catch (SQLException throwables) {
                System.out.println("Error occurred: " + throwables.getMessage());
                return null;
            }
        } else System.out.println("Нет данных для ввода");
        return null;
    }*/


/*    public Map<Integer, Post> getAllTwo() {
        Map<Integer, Post> posts = new HashMap<>();

        try (PreparedStatement preparedStatement = JdbcUtils.createStatement2(GET_ALL_POSTS, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                posts.put(convertFromResult2(posts, resultSet).getId(), convertFromResult2(posts, resultSet));

            }
            List<Label> list = new ArrayList(posts.values());
            return posts;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }*/

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement2(GET_ALL_POSTS, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            int indexList = 1;
            while (resultSet.next()) {
                posts.add(getById(indexList));
                indexList++;
            }
            return posts;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Post getById(Integer id) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement2(GET_POST_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
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
        long time = LocalDateTime.now().getNano() * 1000L;
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(UPDATE_POST)) {

            preparedStatement.setString(1, post.getContent());
            preparedStatement.setLong(2, time);
            preparedStatement.setString(3, post.getStatus().name());
            preparedStatement.setInt(4, post.getId());
            preparedStatement.executeUpdate();


        /*    for (Label l : post.getLabels()) {
                try (PreparedStatement preparedStatementGetID = JdbcUtils.createStatement(UPDATE_POST_LABEL)) {

                    preparedStatementGetID.setInt(1, l.getId());
                    preparedStatementGetID.setInt(2, post.getId());
                    preparedStatementGetID.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (PreparedStatement preparedStatementGetID = JdbcUtils.createStatement(UPDATE_POST_LABEL)) {
            preparedStatementGetID.setInt(1, post.getId());
            preparedStatementGetID.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Label l : post.getLabels()) {
            try (PreparedStatement preparedStatementGetID = JdbcUtils.createStatement(CREATE_POST_LABEL)) {

                preparedStatementGetID.setInt(1, post.getId());
                preparedStatementGetID.setInt(2, l.getId());
                preparedStatementGetID.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try (PreparedStatement preparedStatement2 = JdbcUtils.createStatement("SELECT * FROM posts WHERE id = " + post.getId() + " ;");
             ResultSet resultSet = preparedStatement2.executeQuery()) {
            while (resultSet.next()) {
                post.setCreated(resultSet.getLong("created"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        post.setUpdated(time);
        post.setLabels(post.getLabels());

        return post;
    }

    @Override
    public void deleteById(Integer id) {
        Post p = new Post();
        p.setStatus(PostStatus.DELETED);

        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(DELETE_POST)) {

            preparedStatement.setInt(2, id);
            preparedStatement.setString(1, p.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (PreparedStatement preparedStatementGetID = JdbcUtils.createStatement(UPDATE_POST_LABEL)) {
            preparedStatementGetID.setInt(1, id);
            preparedStatementGetID.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
