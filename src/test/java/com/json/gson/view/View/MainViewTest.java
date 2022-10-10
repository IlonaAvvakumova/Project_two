package com.json.gson.view.View;

import com.json.gson.model.Label;
import com.json.gson.view.LabelView;
import com.json.gson.view.MainView;
import com.json.gson.view.PostView;
import com.json.gson.view.WriterView;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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