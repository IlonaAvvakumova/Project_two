package com.json.gson;


import com.json.gson.repository.jdbc.JdbcLabelRepositoryImpl;
import com.json.gson.service.LabelService;
import com.json.gson.view.MainView;

public class AppRunner {
    public static void main(String[] args) {

      //  MainView menu = new MainView();
      //  menu.menu();

        JdbcLabelRepositoryImpl jdb = new JdbcLabelRepositoryImpl();
        System.out.println(jdb.getAll());
/* "Создать новый массив размера 100 и заполнить его случайными числами из диапазона от 0 до 10000.
Затем, используя цикл for each вывести в консоль:
наибольший элемент массива"*/
/*int [] massiv = new int[100];
        for (int i = 0; i < 100; i++) {
            massiv [i]= (int) (Math.random()*10001);
        }
        int max =0;
        for (int a:massiv
             ) {
            if(a>max)
                max  = a;
        }
        System.out.println(max);*/
    }
}
