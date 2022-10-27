package com.json.gson.repository.jdbc;

import com.json.gson.model.Writer;
import com.json.gson.repository.WriterRepository;
import com.json.gson.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {
    private final String GET_ALL_WRITERS = "SELECT * FROM writers";
    private final String GET_WRITER_BY_ID = "SELECT * FROM writers WHERE id = ?";
    private final String UPDATE_WRITER = "UPDATE writers SET first_name = (?), last_name = (?) where id = ?;";
    private final String CREATE_WRITER = "INSERT INTO writers (first_name, last_name) VALUES (?,?);";
    private final String DELETE_WRITER = " DELETE FROM writers where id = ?;";

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
    public Writer getById(Integer id) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_WRITER_BY_ID)){
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
    public Writer create(Writer writer) {

        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(CREATE_WRITER)) {
            preparedStatement.setString(1,writer.getFirstName());
            preparedStatement.setString(2,writer.getLastName());
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

        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(UPDATE_WRITER)) {
            preparedStatement.setString(1,writer.getFirstName());
            preparedStatement.setString(2,writer.getLastName());
            preparedStatement.setInt(3,writer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer deleteById(Integer id, Writer writer) {

        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(DELETE_WRITER)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Нельзя удалить, используется как внешний ключ");
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();

            return null;
        }
        return writer;
    }
}
