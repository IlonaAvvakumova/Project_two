package com.json.gson;
import com.json.gson.repository.jdbc.JdbcPostRepositoryImpl;
import com.json.gson.view.MainView;

public class AppRunner {
    public static void main(String[] args) {

      MainView menu = new MainView();
      menu.menu();
     /*   JdbcPostRepositoryImpl ttt = new JdbcPostRepositoryImpl();
        System.out.println( ttt.getAll());*/

    }
}
