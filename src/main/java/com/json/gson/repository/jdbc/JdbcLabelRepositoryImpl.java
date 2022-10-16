package com.json.gson.repository.jdbc;

import com.json.gson.model.Label;
import com.json.gson.repository.LabelRepository;
import com.json.gson.utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcLabelRepositoryImpl implements LabelRepository {
    private final String GET_ALL_LABELS = "SELECT * FROM labels";
    private final String UPDATE_ALL_LABELS = "UPDATE labels SET name = 'Igor' where id = 3;";
    private final String CREATE_ALL_LABELS = " insert into labels (name) values (\"Morozov\");";
    private final String DELETE_ALL_LABELS = " DELETE FROM labels where id = 4;";
    private List<Label> labels = new ArrayList<>();

    @Override
    public List<Label> getAll() {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_ALL_LABELS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Label l = new Label();
                l.setId(resultSet.getInt("id"));
                l.setName(resultSet.getString("name"));
                labels.add(l);
            }
            preparedStatement.close();
            resultSet.close();
            return labels;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Label getById(Integer integer) {
        getAll();
        return labels.stream().filter(a -> a.getId().equals(integer)).findFirst().orElse(null);
    }

    @Override
    public Label create(Label label) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(CREATE_ALL_LABELS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return label;
    }

    @Override
    public Label update(Label label) {
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(UPDATE_ALL_LABELS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return label;
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
