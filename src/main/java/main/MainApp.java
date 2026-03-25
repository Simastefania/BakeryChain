package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainViewImpl;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        MainViewImpl view = new MainViewImpl();

        Scene scene = new Scene(view, 900, 700);
        stage.setScene(scene);
        stage.setTitle("Bakery Chain Management");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}