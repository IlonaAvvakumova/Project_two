package com.json.gson.model;

import java.util.List;
import java.util.Map;


public class Post {
    private Integer id;
    private String content;
    private long updated;
    private long created;
    private PostStatus status;
    private List<Label> labels;
    private Writer writer;
    private Map<Integer, String> mapLab;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public Map<Integer, String> getMapLab() {
        return mapLab;
    }

    public void setMapLab(Map<Integer, String> mapLab) {
        this.mapLab = mapLab;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", updated=" + updated +
                ", created=" + created +
                ", status=" + status +
                ", labels=" + labels +
                ", writer=" + writer +
                ", mapLab=" + mapLab +
                '}';
    }
}
