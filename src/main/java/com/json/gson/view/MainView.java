package com.json.gson.view;


import java.util.Scanner;

public class MainView {
    private final LabelView labelView = new LabelView();
    private final PostView postView = new PostView();
    private final WriterView writerView = new WriterView();
    private final Scanner scan = new Scanner(System.in);

    public void menu(){
        System.out.println("Напиши название таблицы: Label, Post или Writer");
        String s = scan.nextLine();
        System.out.println("Выберете операцию: Создать, Обновить, Удалить, Показать все");
        if (s.equals("Label")){
            mainMenuLabel();
        }else if (s.equals("Post")){
            mainMenuPost();
        }else if(s.equals("Writer")){
            mainMenuWriter();
        }else{
            System.out.println("Вы ввели не корректное значение");
            menu();
        }

    }
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
    case"Показать все":
        labelView.getAll();
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
            case"Показать все":
                postView.getAll();
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
            case"Показать все":
                writerView.getAll();

        }
    }
}
