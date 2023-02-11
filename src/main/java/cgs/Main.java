package cgs;

import com.sun.javafx.scene.control.skin.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import cgs.controller.Controller;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/authorization.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Controller.setStage(stage);
        Controller.setShowStage(fxmlLoader);
    }

    private Utils getClass() {
    }

    public static void main(String[] args) {
        launch();
    }
}
