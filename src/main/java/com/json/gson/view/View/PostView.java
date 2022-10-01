package com.json.gson.view.View;

import com.json.gson.controller.PostController;
import com.json.gson.model.Label;
import com.json.gson.model.Post;
import com.json.gson.repository.gson.GsonLabelRepository;

import java.util.List;
import java.util.Scanner;

public class PostView {
    private final Scanner scan = new Scanner(System.in);
    private final PostController controller = new PostController();
    private final GsonLabelRepository gsonLabelRepository = new GsonLabelRepository();
    public void createPostView(){
        System.out.println("Enter text: ");
        String next = scan.nextLine();
        Post post = controller.createPost(next);
        System.out.println("Created Post: " + post);
    }
    public void updatePostView(){
        System.out.println("Enter change text: ");
        String text = scan.nextLine();
        System.out.println("Enter id for change: ");
        Integer id = scan.nextInt();
        List<Label> labelList = gsonLabelRepository.getAll();

        controller.updatePost(id,text, labelList);

    }

    public void deletePostView(){
        System.out.println("Which id needs delete: ");
        Integer id = scan.nextInt();
        controller.deletePost(id);
    }

}
