package PRIM;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PrimsAlgorithmUI extends Application {

    private static final int CANVAS_SIZE = 600;
    private static final int NODE_RADIUS = 15;
    private Canvas canvas;
    private GraphicsContext gc;
    private List<Edge> edges;
    private Graph graph;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prim's Algorithm Visualizer");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #2b2b2b;"); // Set background color to dark

        // Input section
        TextField numNodesField = new TextField();
        numNodesField.setPromptText("Enter number of nodes");
        numNodesField.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: #a9b7c6;");

        Button addEdgeButton = new Button("Add Edge");
        Button startButton = new Button("Start Visualization");
        Button drawButton = new Button("Draw Graph");
        Button simulateButton = new Button("Simulate Prim's Algorithm");

        addEdgeButton.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: #a9b7c6;");
        startButton.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: #a9b7c6;");
        drawButton.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: #a9b7c6;");
        simulateButton.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: #a9b7c6;");

        TextArea edgeListArea = new TextArea();
        edgeListArea.setEditable(false);
        edgeListArea.setPromptText("Edges added will appear here.");
        edgeListArea.setStyle("-fx-control-inner-background: #3c3f41; -fx-text-fill: #a9b7c6;");

        edges = new ArrayList<>();
        graph = new Graph();

        // Form for adding edges
        HBox edgeForm = new HBox(10);
        TextField startNodeField = new TextField();
        TextField endNodeField = new TextField();
        TextField weightField = new TextField();
        startNodeField.setPromptText("Start Node");
        endNodeField.setPromptText("End Node");
        weightField.setPromptText("Weight");
        startNodeField.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: #a9b7c6;");
        endNodeField.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: #a9b7c6;");
        weightField.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: #a9b7c6;");
        edgeForm.getChildren().addAll(startNodeField, endNodeField, weightField, addEdgeButton);

        canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        gc = canvas.getGraphicsContext2D();

        // Add Edge functionality
        addEdgeButton.setOnAction(e -> {
            try {
                int start = Integer.parseInt(startNodeField.getText());
                int end = Integer.parseInt(endNodeField.getText());
                int weight = Integer.parseInt(weightField.getText());

                graph.addEdge(start, end, weight);
                edges.add(new Edge(start, end, weight));
                edgeListArea.appendText("Edge: " + start + " -> " + end + " (Weight: " + weight + ")\n");
                startNodeField.clear();
                endNodeField.clear();
                weightField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter valid integers for nodes and weight.");
            }
        });

        // Draw Graph functionality
        drawButton.setOnAction(e -> {
            try {
                int numNodes = Integer.parseInt(numNodesField.getText());
                if (numNodes <= 0) {
                    throw new NumberFormatException("Number of nodes must be greater than 0.");
                }
                graph.generatePositions(numNodes);
                graph.drawGraph(gc);
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid positive number of nodes.");
            }
        });

        // Start Visualization functionality
        startButton.setOnAction(e -> {
            if (graph.getPositions() == null || graph.getPositions().length == 0) {
                showAlert("Graph Not Drawn", "Please draw the graph before starting the visualization.");
                return;
            }
            graph.visualizePrimAlgorithm(gc);
        });

        // Simulate Prim's Algorithm functionality
        simulateButton.setOnAction(e -> {
            if (graph.getPositions() == null || graph.getPositions().length == 0) {
                showAlert("Graph Not Drawn", "Please draw the graph before simulating the Prim's Algorithm.");
                return;
            }
            graph.simulatePrimAlgorithm(gc);
        });

        root.getChildren().addAll(
                new Label("Enter Number of Nodes:") {{
                    setTextFill(Color.web("#a9b7c6"));
                }},
                numNodesField,
                edgeForm,
                edgeListArea,
                drawButton,
                canvas,
                startButton,
                simulateButton
        );

        Scene scene = new Scene(root, CANVAS_SIZE + 50, CANVAS_SIZE + 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}