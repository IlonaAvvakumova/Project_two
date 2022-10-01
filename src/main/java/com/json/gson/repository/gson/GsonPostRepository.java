package com.json.gson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.json.gson.model.Post;
import com.json.gson.repository.PostRepository;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.*;


public class GsonPostRepository implements PostRepository {
    private final String LABEL_PATH = "D:\\java\\home\\avvakumova\\CRUD_app\\src\\main\\resources\\post.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Post> getAll() {
          List<Post> posts = readJsonPost();
        return posts;
    }

    @Override
    public Post getById(Integer integer) {
        return readJsonPost().stream().filter(a -> a.getId().equals(integer)).findFirst().orElse(null);
    }

    @Override
    public Post create(Post post) {
        List<Post> posts = readJsonPost();
        post.setId(generateID(posts)); //сгенерировать ID и установить его для объекта Post
        posts.add(post);
        post.setCreated(new Date().getTime());
        post.setUpdated(new Date().getTime());

        writeJsonPost(posts);
        return post;
    }

    private Integer generateID(List<Post> posts) {
        Post maxIdPost = posts.stream().max(Comparator.comparing(Post::getId)).orElse(null);
        return Objects.nonNull(maxIdPost) ? maxIdPost.getId() + 1 : 1;
    }

    @Override
    public Post update(Post post) {

        List<Post> posts = readJsonPost();
        posts.forEach(p -> {
            if (p.getId().equals(post.getId())) {
                p.setContent(post.getContent());
                p.setUpdated(new Date().getTime());
                p.setListLab(post.getListLab());
               // p.setGetTine(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));

            }
        });
        writeJsonPost(posts);
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        List<Post> post = readJsonPost();
        post.removeIf(a -> a.getId().equals(id));
        writeJsonPost(post);
    }

    private void writeJsonPost(List<Post> post) {
        try (FileWriter fw = new FileWriter(LABEL_PATH)) {
            fw.write(gson.toJson(post));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка записи файла");
        }

    }

    private List<Post> readJsonPost() {
        Path filePath = Path.of(LABEL_PATH);
        try {
            String json = Files.readString(filePath);
            return gson.fromJson(json, new TypeToken<List<Post>>() {
            }.getType());

        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл");
            return Collections.emptyList();
        }
    }
}
