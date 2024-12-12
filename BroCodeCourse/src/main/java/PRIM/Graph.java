package PRIM;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private int[][] positions;
    private final List<Edge> edges;

    public Graph() {
        edges = new ArrayList<>();
    }

    public void addEdge(int start, int end, int weight) {
        edges.add(new Edge(start, end, weight));
    }

    public void generatePositions(int numNodes) {
        positions = new int[numNodes][2];
        double angleStep = 2 * Math.PI / numNodes;
        int radius = 250;
        int centerX = 300, centerY = 300;

        for (int i = 0; i < numNodes; i++) {
            positions[i][0] = (int) (centerX + radius * Math.cos(i * angleStep));
            positions[i][1] = (int) (centerY + radius * Math.sin(i * angleStep));
        }
    }

    public int[][] getPositions() {
        return positions;
    }

    public void drawGraph(GraphicsContext gc) {
        gc.clearRect(0, 0, 600, 600);

        // Draw edges
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(2);
        for (Edge edge : edges) {
            int x1 = positions[edge.getStart()][0];
            int y1 = positions[edge.getStart()][1];
            int x2 = positions[edge.getEnd()][0];
            int y2 = positions[edge.getEnd()][1];
            gc.strokeLine(x1, y1, x2, y2);
            // Add edge weight
            gc.setFill(Color.BLACK);
            gc.fillText(String.valueOf(edge.getWeight()), (x1 + x2) / 2, (y1 + y2) / 2);
        }

        // Draw nodes
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0], y = positions[i][1];
            if (i == 0) { // Only the first node is light blue
                gc.setFill(Color.LIGHTBLUE);
                gc.fillOval(x - 15, y - 15, 30, 30);
                gc.setFill(Color.BLACK);
                gc.fillText(String.valueOf(i), x - 5, y + 5);
            } else { // Other nodes are black
                gc.setFill(Color.BLACK);
                gc.fillOval(x - 15, y - 15, 30, 30);
            }
        }
    }

    public void visualizePrimAlgorithm(GraphicsContext gc) {
        int numNodes = positions.length;
        int[][] graphMatrix = generateGraphMatrix(numNodes);
        boolean[] mstSet = new boolean[numNodes];
        int[] key = new int[numNodes];
        int[] parent = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;

        List<int[]> mstEdges = new ArrayList<>();
        for (int count = 0; count < numNodes - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < numNodes; v++) {
                if (graphMatrix[u][v] != 0 && !mstSet[v] && graphMatrix[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graphMatrix[u][v];
                }
            }
            if (parent[u] != -1) {
                mstEdges.add(new int[]{parent[u], u});
            }
        }

        animateMST(gc, mstEdges);
    }

    private int[][] generateGraphMatrix(int numNodes) {
        int[][] graphMatrix = new int[numNodes][numNodes];
        for (Edge edge : edges) {
            graphMatrix[edge.getStart()][edge.getEnd()] = edge.getWeight();
            graphMatrix[edge.getEnd()][edge.getStart()] = edge.getWeight();
        }
        return graphMatrix;
    }

    private int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < key.length; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private void animateMST(GraphicsContext gc, List<int[]> mstEdges) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), e -> {
            if (!mstEdges.isEmpty()) {
                int[] edge = mstEdges.remove(0);
                int start = edge[0], end = edge[1];
                int x1 = positions[start][0], y1 = positions[start][1];
                int x2 = positions[end][0], y2 = positions[end][1];

                gc.setStroke(Color.GREEN);
                gc.setLineWidth(4);
                gc.strokeLine(x1, y1, x2, y2);
            } else {
                timeline.stop();
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void simulatePrimAlgorithm(GraphicsContext gc) {
        visualizePrimAlgorithm(gc);
    }
}
