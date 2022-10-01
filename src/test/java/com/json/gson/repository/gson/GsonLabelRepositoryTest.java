package com.json.gson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.json.gson.model.Label;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GsonLabelRepositoryTest {
    @Spy
    GsonLabelRepository testlab = new GsonLabelRepository();
    @Spy
     Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Spy
    Label label = new Label();
    @Spy
    String LABEL_PATH = "D:\\java\\home\\avvakumova\\CRUD_app\\src\\main\\resources\\labels.json";

    @Test
    void getAll() {
       // List<Label> labels = testlab.readJsonLabel();
    }

    @Test
    void getById() {
     //   assertEquals(new Label(),5);
    }

    @Test
    void testCreate() {
        assertEquals(label,label);
    }

    @Test
    void testUpdate() {

    }

    @Test
    void deleteById() {
    }
}