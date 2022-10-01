package com.json.gson.controller;

import com.json.gson.model.Label;

import com.json.gson.repository.LabelRepository;

import com.json.gson.repository.gson.GsonLabelRepository;

public class LabelController {
    private final LabelRepository labelRepository = new GsonLabelRepository();
    public Label createLabel(String name){
        Label label = new Label();
        label.setName(name);
        return labelRepository.create(label);
    }
    public void updateLabel(Integer id, String name){
        Label label = new Label();
        label.setId(id);
        label.setName(name);
         labelRepository.update(label);
    }
    public void deleteLabel(Integer id){
             labelRepository.deleteById(id);
    }

    public Label getLabelById(Integer id){
        return labelRepository.getById(id);
    }
}
