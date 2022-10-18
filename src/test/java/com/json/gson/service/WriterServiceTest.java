package com.json.gson.service;

import com.json.gson.model.Writer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WriterServiceTest {

    Writer writer = new Writer();
    WriterService writerService = new WriterService();

    @Test
    void create() {
        /*WriterService writerService = Mockito.mock(WriterService.class);
when(writerService.create(writer)).thenReturn(writer);*/
        writer.setFirstName("Володя");
        writerService.create(writer);
        assertEquals(true, writer.getFirstName().equals("Володя"));

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}