package com.json.gson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.json.gson.model.Label;
import com.json.gson.repository.LabelRepository;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class GsonLabelRepository implements LabelRepository {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String LABEL_PATH = "D:\\java\\home\\avvakumova\\CRUD_app\\src\\main\\resources\\labels.json";

    @Override
    public List<Label> getAll() {

        List<Label> labels = readJsonLabel();
        return labels;
    }

    @Override
    public Label getById(Integer id) {
        return readJsonLabel().stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Label create(Label label) {
        List<Label> labels = readJsonLabel();
        label.setId(generateID(labels)); //сгенерировать ID и установить его для объекта Label
        labels.add(label);
        writeJsonLabel(labels);
        return label;
    }

    private Integer generateID(List<Label> labels) {
        Label maxIdLabel = labels.stream().max(Comparator.comparing(Label::getId)).orElse(null);
        return Objects.nonNull(maxIdLabel) ? maxIdLabel.getId() + 1 : 1;
    }

    @Override
    public Label update(Label label) {
        List<Label> labels = readJsonLabel();
        labels = labels.stream().map(a -> {
            if (a.getId().equals(label.getId()))
                a.setName(label.getName());
            return a;
        })
                .collect(Collectors.toList());
        writeJsonLabel(labels);
        return label;
    }

    @Override
    public void deleteById(Integer id) {
        List<Label> label2 = readJsonLabel();
           label2.removeIf(a->a.getId().equals(id));
        writeJsonLabel(label2);

    }
    /*public void showById(Integer id){
        List<Label> label2 = readJsonLabel();
        label2 = label2.stream().filter(a->a.getId().equals(id)).collect(Collectors.toList());
        System.out.println(label2);
    }*/

    private void writeJsonLabel(List<Label> label) {
        try (FileWriter fw = new FileWriter(LABEL_PATH)) {
            fw.write(gson.toJson(label));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка записи файла");
        }

    }

    private List<Label> readJsonLabel() {
        Path filePath = Path.of(LABEL_PATH);
        try {
            String json = Files.readString(filePath);
            return gson.fromJson(json, new TypeToken<List<Label>>() {
            }.getType());

        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл");
            return Collections.emptyList();
        }
    }
}
