package com.json.gson.repository.jdbc;

import com.json.gson.model.Label;
import com.json.gson.repository.LabelRepository;
import com.json.gson.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcLabelRepositoryImpl implements LabelRepository {
    private final String GET_ALL_LABELS = "SELECT * FROM labels";
    private final String GET_LABEL_BY_ID = "SELECT * FROM labels WHERE id = %d";
    private final String UPDATE_LABEL = "UPDATE labels SET name = %s where id = %d;";
    private final String CREATE_LABEL = "INSERT INTO labels (name) VALUES ('%s');";
    private final String DELETE_LABEL = " DELETE FROM labels where id = %d;";
    private List<Label> labels = new ArrayList<>();

    private Label convertFromResult(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                Label l = new Label();
                l.setId(resultSet.getInt("id"));
                l.setName(resultSet.getString("name"));
                return l;
            } catch (SQLException throwables) {
                System.out.println("Error occurred: " + throwables.getMessage());
                return null;
            }
        } else System.out.println("Нет данных для ввода");
        return null;
    }

    @Override
    public List<Label> getAll() {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_ALL_LABELS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Label> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(convertFromResult(resultSet));
            }
            return result;
        } catch (SQLException throwables) {
            System.out.println("Error occurred: " + throwables.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Label getById(Integer id) {
        String sql = String.format(GET_LABEL_BY_ID, id);
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return (convertFromResult(resultSet));
        } catch (SQLException throwables) {
            System.out.println("Error occurred: " + throwables.getMessage());
            return null;
        }
    }

    @Override
    public Label create(Label label) {
        String sql = String.format(CREATE_LABEL, label.getName());
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(sql)) {
          preparedStatement.executeUpdate();
                 ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    label.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return label;
    }

    @Override
    public Label update(Label label) {
        String sql = String.format(UPDATE_LABEL, label.getName());
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(sql)) {
            preparedStatement.executeUpdate();
            label.setId(Statement.RETURN_GENERATED_KEYS); //?????
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return label;
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(DELETE_LABEL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
