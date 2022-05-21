package pl.jimp.pathfinder;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GraphDrawer {

    private AnchorPane graphPane;
    private AnchorPane edgePane;
    private Label lblPathLength;
    private Graph graph;
    private Search search;
    private double circleRadius;
    private final static double PANE_WIDTH = 1000.0;
    private final static double PANE_HEIGHT = 750.0;
    private Circle[] drawnVertices;
    private Line[][] drawnEdges;
    private List<Integer> path;
    private List<Paint> edgesColors;

    private final static Color VERTEX_DEFAULT_COLOR = Color.VIOLET;
    private final static Color VERTEX_VISITED_COLOR = Color.BLACK;
    public GraphDrawer(AnchorPane graphPane, AnchorPane edgePane, Graph graph, Label lblPathLength) {
        this.graphPane = graphPane;
        this.edgePane = edgePane;
        this.graph = graph;
        this.lblPathLength = lblPathLength;
        circleRadius = PANE_WIDTH * graph.getNumOfRows() > PANE_HEIGHT * graph.getNumOfColumns() ? PANE_HEIGHT / graph.getNumOfRows() / 4.0 : PANE_WIDTH / graph.getNumOfColumns() / 4.0;
        drawnVertices = new Circle[graph.getNumOfRows() * graph.getNumOfColumns()];
        drawnEdges = new Line[graph.getNumOfColumns() * graph.getNumOfRows()][4];
        edgesColors = new ArrayList<>();
    }

    public void drawGraph() {
        clearGraph();
        for (int row = 0; row < graph.getNumOfRows(); row++) {
            for (int column = 0; column < graph.getNumOfColumns(); column++) {
                Circle vertexCircle = new Circle();
                vertexCircle.setFill(VERTEX_DEFAULT_COLOR);
                vertexCircle.setRadius(circleRadius);
                vertexCircle.setLayoutY(circleRadius + (row * circleRadius * 4));
                vertexCircle.setLayoutX(circleRadius + (column * circleRadius * 4));

                findPathOnMouseClick(vertexCircle, row, column);

                drawnVertices[column + (row * graph.getNumOfColumns())] = vertexCircle;
                graphPane.getChildren().add(drawnVertices[column + (row * graph.getNumOfColumns())]);
            }
        }
        drawEdges(drawnVertices);
    }

    private void findPathOnMouseClick(Circle vertexCircle, int row, int column) {
        vertexCircle.setOnMouseClicked(event -> {
            vertexCircle.setFill(VERTEX_VISITED_COLOR);
            if (search == null) {
                search = new Search(graph);
                search.setStartVertex(row * graph.getNumOfColumns() + column);
            } else {
                if (path != null) {
                    if(!path.isEmpty()){
                        clearPath();
                    }
                }
                search.setEndVertex(row * graph.getNumOfColumns() + column);
                path = search.dijkstra();

                if (search.getDistance() == Double.MAX_VALUE) {
                    lblPathLength.setText("no connection between given vertices");
                    search = null;
                    return;
                }
                lblPathLength.setText(String.valueOf(search.getDistance()));
                drawPath();
                search = null;
            }
        });
        vertexCircle.setOnMouseEntered(event -> {
            vertexCircle.getScene().setCursor(Cursor.HAND);
        });
        vertexCircle.setOnMouseExited(event ->{
            vertexCircle.getScene().setCursor(Cursor.DEFAULT);
        });
    }

    private void drawEdges(Circle[] drawnVertices) {

        double maxWeight = 0.0;
        double minWeight = Double.MAX_VALUE;
        for (int i = 0; i < graph.getNumOfColumns() * graph.getNumOfRows() - 1; i++) {
            if (maxWeight < Collections.max(graph.getVertices().get(i).getWeights()))
                maxWeight = Collections.max(graph.getVertices().get(i).getWeights());
            if (minWeight > Collections.min(graph.getVertices().get(i).getWeights()))
                minWeight = Collections.min(graph.getVertices().get(i).getWeights());
        }

        for (int i = 0; i < graph.getNumOfColumns() * graph.getNumOfRows(); i++) {
            for (int j = 0; j < 4; j++) {
                if ((graph.getVertices().get(i).getWeights().get(j)) > 0) {
                    Line edgeLine = new Line();

                    double amountOfRedColor = 1;
                    double amountOfGreenColor = 1;
                    if (graph.getVertices().get(i).getWeights().get(j) / (maxWeight - minWeight) + minWeight < 0.5)
                        amountOfRedColor = 2 * (graph.getVertices().get(i).getWeights().get(j) / (maxWeight - minWeight) + minWeight);
                    if (graph.getVertices().get(i).getWeights().get(j) / (maxWeight - minWeight) + minWeight > 0.5)
                        amountOfGreenColor = 2 * (1 - graph.getVertices().get(i).getWeights().get(j) / (maxWeight - minWeight) + minWeight);

                    Color currentColor = Color.color(amountOfRedColor, amountOfGreenColor, 0.0);
                    edgeLine.setStroke(currentColor);
                    edgeLine.setStrokeWidth(drawnVertices[i].getRadius() / 2);
                    edgeLine.setStartX(drawnVertices[i].getLayoutX());
                    edgeLine.setStartY(drawnVertices[i].getLayoutY());
                    edgeLine.setEndX(drawnVertices[graph.getIndexOfTheVertex(i, j)].getLayoutX());
                    edgeLine.setEndY(drawnVertices[graph.getIndexOfTheVertex(i, j)].getLayoutY());
                    drawnEdges[i][j] = edgeLine;
                    edgePane.getChildren().add(drawnEdges[i][j]);
                }
            }
        }
    }

    private void drawPath() {
        if (path.get(0) == path.get(1)) {  // if startVertex == endVertex
            drawnVertices[path.get(0)].setFill(VERTEX_VISITED_COLOR);
            return;
        }
        for (int i = 0; i < path.size() - 1; i++) {
            drawnVertices[path.get(i)].setFill(VERTEX_VISITED_COLOR);
            int j = -1;
            int k = -1;
            if (path.get(i + 1) - path.get(i) == -(graph.getNumOfColumns())) {
                j = 0;
                k = 1;
            } else if (path.get(i + 1) - path.get(i) == graph.getNumOfColumns()) {
                j = 1;
                k = 0;
            } else if (path.get(i + 1) - path.get(i) == 1) {
                j = 2;
                k = 3;
            } else if (path.get(i + 1) - path.get(i) == -1) {
                j = 3;
                k = 2;
            }
            edgesColors.add(drawnEdges[path.get(i)][j].getStroke());
            drawnEdges[path.get(i)][j].setStroke(VERTEX_VISITED_COLOR);
            drawnEdges[path.get(i + 1)][k].setStroke(VERTEX_VISITED_COLOR);
        }
        drawnVertices[path.get(path.size() - 1)].setFill(VERTEX_VISITED_COLOR);
    }

    private void clearGraph() {
        graphPane.getChildren().clear();
        edgePane.getChildren().clear();
    }

    private void clearPath() {
        if (path.size() > 2 && !Objects.equals(path.get(0), path.get(1))) {
            System.out.println("dlugosc = " + path.size());
            for (int i = 0; i < path.size() - 1; i++) {
                drawnEdges[path.get(i)][graph.getDirectionFromIndex(path.get(i), path.get(i + 1))].setStroke(edgesColors.get(i));
                drawnEdges[path.get(i + 1)][graph.getDirectionFromIndex(path.get(i + 1), path.get(i))].setStroke(edgesColors.get(i));
                drawnVertices[path.get(i)].setFill(VERTEX_DEFAULT_COLOR);
            }
        }
        drawnVertices[path.get(0)].setFill(VERTEX_DEFAULT_COLOR);
        if(graph.getDirectionFromIndex(path.get(0), path.get(1)) != -1 && !Objects.isNull(drawnEdges[path.get(0)][graph.getDirectionFromIndex(path.get(0), path.get(1))])) {
            drawnEdges[path.get(0)][graph.getDirectionFromIndex(path.get(0), path.get(1))].setStroke(edgesColors.get(0));
            drawnEdges[path.get(1)][graph.getDirectionFromIndex(path.get(1), path.get(0))].setStroke(edgesColors.get(0));
        }
        drawnVertices[path.get(path.size() - 1)].setFill(VERTEX_DEFAULT_COLOR);
        edgesColors.clear();
    }


}
