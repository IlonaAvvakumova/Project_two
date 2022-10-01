package com.json.gson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.json.gson.model.Writer;
import com.json.gson.repository.WriterRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GsonWriterRepository implements WriterRepository {
    private final String LABEL_PATH = "D:\\java\\home\\avvakumova\\CRUD_app\\src\\main\\resources\\writer.json";
    private final Path filePath = Path.of(LABEL_PATH);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Writer> getAll() {
        return new ArrayList<>();
    }

    @Override
    public Writer getById(Integer integer) {

        return readJsonWriter().stream().filter(a -> a.getId().equals(integer)).findFirst().orElse(null);
    }

    @Override
    public Writer create(Writer writer) {
        List<Writer> writers = readJsonWriter();
        writer.setId(generateID(writers)); //сгенерировать ID и установить его для объекта Post
        writers.add(writer);
        writeJsonWriter(writers);
        return writer;
    }

    private Integer generateID(List<Writer> writer) {
        Writer maxIdPost = writer.stream().max(Comparator.comparing(Writer::getId)).orElse(null);
        return Objects.nonNull(maxIdPost) ? maxIdPost.getId() + 1 : 1;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> writers = readJsonWriter();
        writers.forEach(w -> {
            if(w.getId().equals(writer.getId())) {
                w.setFirstName(writer.getFirstName());
                w.setLastName(writer.getLastName());
                w.setWriterList(writer.getWriterList());
            }
        });
        writeJsonWriter(writers);
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        List<Writer> writer = readJsonWriter();
        writer.removeIf(a -> a.getId().equals(id));
        writeJsonWriter(writer);
    }

    private void writeJsonWriter(List<Writer> writer) {
        try (FileWriter fw = new FileWriter(LABEL_PATH)) {
            fw.write(gson.toJson(writer));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка записи файла");
        }

    }

    private List<Writer> readJsonWriter() {

        try {
            String json = Files.readString(filePath);
            return gson.fromJson(json, new TypeToken<List<Writer>>() {
            }.getType());

        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл");
            return Collections.emptyList();
        }
    }


}
