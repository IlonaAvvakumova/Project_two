package com.json.gson.view;

import com.json.gson.controller.LabelController;
import com.json.gson.controller.PostController;
import com.json.gson.controller.WriterController;
import com.json.gson.model.Label;
import com.json.gson.model.Post;
import com.json.gson.model.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {
    private final Scanner scan = new Scanner(System.in);
    private final PostController controller = new PostController();
    private final LabelController labelController = new LabelController();
    private final WriterController writerController = new WriterController();

    public void createPostView() {
        System.out.println("Enter text: ");
        String next = scan.nextLine();
        Writer writer = selectWriter();
        List<Label> labels = selectLabels();
        Post post = controller.createPost(next, labels,  writer);
        System.out.println("Created Post: " + post);
    }

    private Writer selectWriter() {
        System.out.println("Все писатели");
        List<Writer> writers = writerController.getAll();
        System.out.println(writers);
        System.out.println("Выберите id писателя");
        Integer writerId = scan.nextInt();
        Writer writer = new Writer();
        for (Writer w : writers
        ) {
            if (w.getId().equals(writerId) ) {
                writer = w;
            }
        }
        return writer;
    }

    private List<Label> selectLabels() {
        List<Label> result = new ArrayList<>();
         List<Label> labelList = labelController.getAllLabels();
         System.out.println("All labels: " + labelList);
        System.out.println("Введите ID, цифра -1 делает окончательный выбор");
        while (true) {
            Integer choice = scan.nextInt();
            if (choice == -1) {
                return result;
            }
            Label currentLabel = labelList.stream().filter(label -> label.getId().equals(choice)).findFirst().orElse(null);
            result.add(currentLabel);
        }
    }

    public void updatePostView() {
        System.out.println("Enter change text: ");
        String text = scan.nextLine();
        System.out.println("Enter id for change: ");
        Integer id = scan.nextInt();
        List<Label> labelList = selectLabels();
        Post post = controller.updatePost(id, text, labelList);
        System.out.println("Update post, new post: " + post);

    }

    public void deletePostView() {
        System.out.println("Which id needs delete: ");
        Post post = new Post();
        Integer id = scan.nextInt();
        post.setId(id);
        System.out.println("Удаление прошло успешно");
       controller.deletePost(id);

    }

    public void getAll() {
        System.out.println("Все posts:\n");
        List<Post> postsList = controller.getAll();
        System.out.println(postsList);
    }

    public void getById() {
        System.out.println("Which id needs show");
        Integer id = scan.nextInt();
        Post post = controller.getById(id);
        System.out.println(post);
    }
}


