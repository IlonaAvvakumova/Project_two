package com.json.gson.controller;

import com.json.gson.model.Label;
import com.json.gson.service.LabelService;

import java.util.List;

public class LabelController {

    private final LabelService labelService = new LabelService();

    public List<Label> getAllLabels(){
        return  labelService.getAllLab();
    }
    public Label createLabel(String name){
        Label label = new Label();// создается сущность для передачи в БД
        label.setName(name);
        return labelService.createLabel(label);
    }
    public Label updateLabel(Integer id, String name){
        Label label = new Label();
        label.setId(id);
        label.setName(name);
       return labelService.updateLabel(label);
    }
    public void deleteLabel(Integer id){
        labelService.deleteLabel(id);
    }

    public Label getLabelById(Integer id){
        return labelService.getLabel(id);
    }
}
