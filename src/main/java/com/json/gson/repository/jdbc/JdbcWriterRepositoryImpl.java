package com.json.gson.repository.jdbc;

import com.json.gson.model.Label;
import com.json.gson.model.Writer;
import com.json.gson.repository.WriterRepository;
import com.json.gson.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {
    private final String GET_ALL_WRITERS = "SELECT * FROM writers";
    private final String UPDATE_WRITER = "UPDATE writers SET first_name = '%s', last_name = '%s' where id = %d;";
    private final String CREATE_WRITER = "INSERT INTO writers (first_name, last_name) VALUES ('%s','%s');";
    private final String DELETE_WRITER = " DELETE FROM writers where id = %d;";

    private Writer convertFromResult(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                Writer w = new Writer();
                w.setId(resultSet.getInt("id"));
                w.setFirstName(resultSet.getString("first_name"));
                w.setLastName(resultSet.getString("last_name"));
                return w;
            } catch (SQLException throwables) {
                System.out.println("Error occurred: " + throwables.getMessage());
                return null;
            }
        } else System.out.println("Нет данных для ввода");
        return null;
    }

    @Override
    public List<Writer> getAll() {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_ALL_WRITERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Writer> writers = new ArrayList<>();
            while (resultSet.next()) {
                writers.add(convertFromResult(resultSet));
            }

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
        String sql = String.format(CREATE_WRITER, writer.getFirstName(), writer.getLastName());
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(sql)) {
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                writer.setId(generatedKeys.getInt(1));

            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        String sql = String.format(UPDATE_WRITER, writer.getFirstName(), writer.getLastName(), writer.getId());
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = String.format(DELETE_WRITER, id);
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
