package com.json.gson.view.View;


import java.util.Scanner;

public class MainView {
    private final LabelView labelView = new LabelView();
    private final PostView postView = new PostView();
    private final WriterView writerView = new WriterView();

    private final Scanner scan = new Scanner(System.in);
   public void mainMenuLabel(){
String input = scan.nextLine();
switch (input) {
    case "Создать":
        labelView.createLabelView();
        break;
    case "Обновить":
        labelView.updateLabelView();
        break;
    case "Удалить":
        labelView.deleteLabelView();
        break;

}
   }
    public void mainMenuPost(){
        String input = scan.nextLine();
        switch (input) {
            case "Создать":
                postView.createPostView();
                break;
            case "Обновить":
                postView.updatePostView();
                break;
            case "Удалить":
                postView.deletePostView();
                break;

        }
    }
    public void mainMenuWriter(){
        String input = scan.nextLine();
        switch (input) {
            case "Создать":
                writerView.createWriterView();
                break;
            case "Обновить":
                writerView.updateWriterView();
                break;
            case "Удалить":
                writerView.deleteWriterView();
                break;


        }
    }
}
