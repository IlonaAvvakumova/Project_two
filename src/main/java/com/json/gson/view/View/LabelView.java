package com.json.gson.view.View;

import com.json.gson.controller.LabelController;
import com.json.gson.model.Label;

import java.util.Scanner;

public class LabelView {
    private final Scanner scan = new Scanner(System.in);
    private final LabelController controller = new LabelController();
    public void createLabelView(){
        System.out.println("Enter name: ");
        String name = scan.nextLine();
        Label label = controller.createLabel(name);
        System.out.println("Created label: " + label);
    }
    public void updateLabelView(){
        System.out.println("Enter change name: ");
        String name = scan.nextLine();
        System.out.println("Enter id for change: ");
        Integer id = scan.nextInt();
         controller.updateLabel(id,name);

    }

    public void deleteLabelView(){
       System.out.println("Which id needs delete: ");
        Integer id = scan.nextInt();
       controller.deleteLabel(id);
    }


}
