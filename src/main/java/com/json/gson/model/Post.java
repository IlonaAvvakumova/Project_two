package com.json.gson.model;

import java.util.List;


public class Post {
   private Integer id;
   private String content;
  private   long updated; // дата создания
  private   long created; // дата изменения
//    private String getTine;
  private   List<Label> listLab;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", updated=" + updated +
                ", created=" + created +

                ", listLab=" + listLab +
                '}';
    }


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

    public List<Label> getListLab() {
        return listLab;
    }

    public void setListLab(List<Label> listLab) {
        this.listLab = listLab;
    }

}
