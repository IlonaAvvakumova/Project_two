package com.json.gson.service;

import com.json.gson.repository.jdbc.JdbcLabelRepositoryImpl;
import com.json.gson.model.Label;
import com.json.gson.repository.LabelRepository;
import java.util.List;

public class LabelService {
    private final LabelRepository labelRepository;

    public LabelService() {
        this.labelRepository = new JdbcLabelRepositoryImpl();
    }

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Label createLabel(Label label) {
        return labelRepository.create(label);
    }

    public Label updateLabel(Label label) {
        return labelRepository.update(label);
    }

    public void deleteLabel(Integer id) {
        Label l = new Label();
        labelRepository.deleteById(id, l);
    }

    public Label getLabel(Integer id) {
        return labelRepository.getById(id);
    }

    public List<Label> getAllLab() {

        return labelRepository.getAll();
    }
}
