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
    private final String GET_ALL_LABELS = "SELECT * FROM writers";
    private final String UPDATE_ALL_LABELS = "UPDATE writers SET first_name = 'Igor' where id = 1;";
    private final String CREATE_ALL_LABELS = " insert into writers (first_name, last_name) values ('Ivan','Morozov');";
    private final String DELETE_ALL_LABELS = " DELETE FROM writers where id = 1;";
    List<Writer> writers = new ArrayList<>();

    @Override
    public List<Writer> getAll() {
               try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_ALL_LABELS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               Writer w = new Writer();
                w.setId(resultSet.getInt("id"));
                w.setFirstName(resultSet.getString("first_name"));
                w.setLastName(resultSet.getString("last_name"));
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
        getAll();
        return writers.stream().filter(a -> a.getId().equals(integer)).findFirst().orElse(null);
    }

    @Override
    public Writer create(Writer writer) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(CREATE_ALL_LABELS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(UPDATE_ALL_LABELS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writer;
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
