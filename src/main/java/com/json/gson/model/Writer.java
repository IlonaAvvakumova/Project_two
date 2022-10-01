package com.json.gson.model;

import java.util.List;

//Writer (id, firstName, lastName, List<Post> posts)
public class Writer {
    Integer id;
    String firstName;
    String lastName;
List<Post> writerList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getWriterList() {
        return writerList;
    }

    public void setWriterList(List<Post> writerList) {
        this.writerList = writerList;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", writerList=" + writerList +
                '}';
    }
}
