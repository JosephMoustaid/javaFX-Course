package org.example.brocodecourse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXStages extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root , Color.BLACK);
        stage.setTitle("Demo program");

        stage.setHeight(500);
        stage.setWidth(500);
        stage.setResizable(false);
        //stage.setX(0);
        //stage.setY(0);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Press e to exit the full screen mode");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("e"));

        Image icon = new Image("C:\\Users\\moustaid\\Desktop\\javaFX course\\BroCodeCourse\\src\\images\\logo.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        // always run show at the end
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
