package com.json.gson.view;

import com.json.gson.controller.LabelController;
import com.json.gson.model.Label;

import java.util.List;
import java.util.Scanner;

public class LabelView {
    private final Scanner scan = new Scanner(System.in);
    private final LabelController controller = new LabelController();

    public void createLabelView() {
        System.out.println("Enter name: ");
        String name = scan.nextLine();
        Label label = controller.createLabel(name);
        System.out.println("Created label: " + label);
    }

    public void updateLabelView() {
        System.out.println("Enter change name: ");
        String name = scan.nextLine();
        System.out.println("Enter id for change: ");
        Integer id = scan.nextInt();
        Label label = controller.updateLabel(id, name);
        System.out.println("Update label, new label: " + label);
    }

    public void deleteLabelView() {
        System.out.println("Which id needs delete: ");
        Integer id = scan.nextInt();
        controller.deleteLabel(id);
        System.out.println("Удаление прошло успешно");
    }

    public void getAll() {
        System.out.println("Все labels:\n");
        List<Label> labelList = controller.getAllLabels();
        System.out.println(labelList);
    }

    public void getById() {
        System.out.println("Which id needs show");
        Integer id = scan.nextInt();
        Label label = controller.getById(id);
        System.out.println(label);
    }

}
