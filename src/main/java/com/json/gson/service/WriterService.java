package com.json.gson.service;

import com.json.gson.model.Writer;
import com.json.gson.repository.WriterRepository;
import com.json.gson.repository.jdbc.JdbcWriterRepositoryImpl;

import java.util.List;

public class WriterService {
    private final WriterRepository writerRepository;

    public WriterService(){
        this.writerRepository = new JdbcWriterRepositoryImpl();
    }

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer create(Writer writer) {
        return writerRepository.create(writer);
    }

    public Writer update(Writer writer) {
        return writerRepository.update(writer);
    }

    public void delete(Integer id) {
        writerRepository.deleteById(id);
    }
    public List<Writer> getAll( ) {
      return   writerRepository.getAll();
    }
}
