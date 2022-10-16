package com.json.gson.repository.jdbc;

import com.json.gson.model.Writer;
import com.json.gson.repository.WriterRepository;
import com.json.gson.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {
    private final String GET_ALL_LABELS = "SELECT * FROM labels";
    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_ALL_LABELS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
           // convertLabel(resultSet);
            while (resultSet.next()) {
               Writer w = new Writer();
                w.setId(resultSet.getInt("id"));
                w.setFirstName(resultSet.getString("FirstName"));
                w.setLastName(resultSet.getString("LastName"));
                writers.add(w);
            }
            preparedStatement.close();
            resultSet.close();
            return writers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Writer getById(Integer integer) {
        return null;
    }

    @Override
    public Writer create(Writer writer) {
        return null;
    }

    @Override
    public Writer update(Writer writer) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
