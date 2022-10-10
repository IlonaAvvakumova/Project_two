package com.json.gson.jdbc;

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

    private Label convertLabel(ResultSet rs) {
        return null;
    }

    @Override
    public List<Label> getAll() {
        List<Label> labels = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.createStatement(GET_ALL_LABELS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            convertLabel(resultSet);
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
        return null;
    }

    @Override
    public Label create(Label label) {
        return null;
    }

    @Override
    public Label update(Label label) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
