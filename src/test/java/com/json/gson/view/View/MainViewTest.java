package com.json.gson.view.View;

import com.json.gson.model.Label;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MainViewTest {
    @Mock
            MainView main;
    LabelView labelView;
     PostView postView;
    @Spy
     WriterView writerView = new WriterView();
    List<Label> labels;



    @org.junit.jupiter.api.Test
    void TestmainMenuLabel() {

        Mockito.doNothing().when(main).mainMenuLabel();
    }

    @org.junit.jupiter.api.Test
    void TestmainMenuPost() {
    }

    @org.junit.jupiter.api.Test
    void TestmainMenuWriter() {
    }
}