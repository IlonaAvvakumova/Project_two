package com.json.gson.controller;

import com.json.gson.model.Label;

import com.json.gson.repository.LabelRepository;

import com.json.gson.repository.gson.GsonLabelRepository;
import com.json.gson.service.LabelService;

import java.util.List;

public class LabelController {

    private final LabelService labelService = new LabelService();

    public List<Label> getAllLabels(){
        return  labelService.getAllLab();
    }
    public Label createLabel(String name){
        Label label = new Label();
        label.setName(name);
        return labelService.createLabel(label);
    }
    public void updateLabel(Integer id, String name){
        Label label = new Label();
        label.setId(id);
        label.setName(name);
        labelService.updateLabel(label);
    }
    public void deleteLabel(Integer id){
        labelService.deleteLabel(id);
    }

    public Label getLabelById(Integer id){
        return labelService.getLabel(id);
    }
}