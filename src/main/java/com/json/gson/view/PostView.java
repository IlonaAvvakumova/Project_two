package com.json.gson.view;

import com.json.gson.controller.LabelController;
import com.json.gson.controller.PostController;
import com.json.gson.model.Label;
import com.json.gson.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {
    private final Scanner scan = new Scanner(System.in);
    private final PostController controller = new PostController();
    private final LabelController labelController = new LabelController();

    public void createPostView() {
        System.out.println("Enter text: ");
        String next = scan.nextLine();
        Post post = controller.createPost(next);
        System.out.println("Created Post: " + post);
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
    private List<Label> selectLabels() {
        List<Label> result = new ArrayList<>();
        List<Label> labelList = labelController.getAllLabels();
        System.out.println("All labels: " + labelList);
        System.out.println("Enter ID");
        while (true) {
            Integer choice = scan.nextInt();
            if (choice == -1) {
                return result;
            }
            Label currentLabel = labelList.stream().filter(label -> label.getId().equals(choice)).findFirst().orElse(null);
        }

    }
    public void deletePostView() {
        System.out.println("Which id needs delete: ");
        Integer id = scan.nextInt();
        controller.deletePost(id);
        System.out.println("Удаление прошло успешно");
    }
    public void getAll(){
        System.out.println("Все posts:\n");
        List<Post> postsList =  controller.getAll();
        System.out.println(postsList);
    }
}


