package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.BakeryViewImpl;
import presenter.BakeryPresenter;
import model.repository.BakeryRepositoryImpl;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        BakeryViewImpl view = new BakeryViewImpl();

        Scene scene = new Scene(view, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Bakery App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}