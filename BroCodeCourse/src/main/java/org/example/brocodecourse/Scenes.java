package org.example.brocodecourse;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Scenes extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();

        Text text = new Text();
        text.setText("TEXT.......");
        text.setX(100);
        text.setY(100);
        text.setFont(Font.font("Consolas", FontWeight.BOLD, 30));
        text.setFill(Color.WHITE);

        Line line = new Line();
        line.setStartX(110);
        line.setStartY(110);
        line.setEndX(200);
        line.setEndY(200);
        line.setOpacity(0.5);
        line.setStroke(Color.WHITE);
        line.setRotate(45);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(300);
        rectangle.setY(300);
        rectangle.setHeight(100);
        rectangle.setWidth(250);
        rectangle.setFill(Color.RED);
        rectangle.setStroke(Color.WHITE);
        rectangle.setStrokeWidth(10);
        rectangle.setCursor(Cursor.HAND);

        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(
                400.0  , 400.0 ,
                450.0 , 450.0 ,
                350.0 , 450.0 );
        triangle.setFill(Color.BLUE);

        Circle circle = new Circle();
        circle.setCenterX(250);
        circle.setCenterY(250);
        circle.setRadius(100);
        circle.setFill(Color.GREEN);

        Image icon = new Image("C:\\Users\\moustaid\\Desktop\\javaFX course\\BroCodeCourse\\src\\images\\logo.png");
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setX(440);
        imageView.setY(440);

        root.getChildren().add(text );
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(triangle);
        root.getChildren().add(circle);
        root.getChildren().add(imageView);


        Scene scene = new Scene(root , 600 , 600 , Color.BLACK);
        stage.setScene(scene);

        // always run show at the end
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
