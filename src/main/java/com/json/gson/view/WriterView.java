package com.json.gson.view;

import com.json.gson.controller.PostController;
import com.json.gson.controller.WriterController;
import com.json.gson.model.Post;
import com.json.gson.model.Writer;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final Scanner scan = new Scanner(System.in);
    private final WriterController controller = new WriterController();
    PostController postController = new PostController();

    public void createWriterView() {
        System.out.println("Enter First Name: ");
        String next1 = scan.nextLine();
        System.out.println("Enter Last Name: ");
        String next2 = scan.nextLine();
        Writer writer = controller.createWriter(next1, next2);
        System.out.println("Created Post: " + writer);
    }

    public void updateWriterView() {
        System.out.println("Enter change First Name: ");
        String next1 = scan.nextLine();
        System.out.println("Enter change Last Name: ");
        String next2 = scan.nextLine();
        System.out.println("Enter id for change: ");
        Integer id = scan.nextInt();
        List<Post> list = postController.getAll();
       Writer writer = controller.updateWriter(id, next1, next2, list);
        System.out.println("Update writer, new writer: " + writer);
    }

    public void deleteWriterView() {
        System.out.println("Which id needs delete: ");
        Integer id = scan.nextInt();
        controller.deleteWriter(id);

        System.out.println("Удаление прошло успешно");
    }
    public void getAll(){
        System.out.println("Все writers:\n");
        List<Writer> writerList =  controller.getAll();
        System.out.println(writerList);
    }
}
