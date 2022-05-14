package pl.jimp.pathfinder;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GraphDrawer {

    private AnchorPane graphPane;
    private Graph graph;

    private double circleRadius;
    private final static double PANE_WIDTH = 1000.0;
    private final static double PANE_HEIGHT = 750.0;

    private Circle[] drawnVertices;
    private Circle startVertexCircle;
    private Circle endVertexCircle;

    private boolean isStartChooseActive;
    private boolean isEndChooseActive;


    public GraphDrawer(AnchorPane graphPane, Graph graph) {
        this.graphPane = graphPane;
        this.graph = graph;
        circleRadius = PANE_HEIGHT / graph.getNumOfRows() / 2.0;
        drawnVertices = new Circle[graph.getNumOfRows() * graph.getNumOfColumns()];
        isStartChooseActive = false;
        isEndChooseActive = false;
    }

    public void drawGraph() {
        for (int row = 0; row < graph.getNumOfRows(); row++) {
            for (int column = 0; column < graph.getNumOfColumns(); column++) {
                Circle vertexCircle = new Circle();
                vertexCircle.setFill(Paint.valueOf("violet"));
                vertexCircle.setRadius(circleRadius);
                vertexCircle.setLayoutY(circleRadius + (row * circleRadius * 2));
                vertexCircle.setLayoutX(circleRadius + (column * circleRadius * 2));
                graphPane.getChildren().add(vertexCircle);

                drawnVertices[column + (row * graph.getNumOfColumns())] = vertexCircle;
            }
        }
        System.out.println(drawnVertices.length);
    }

    public void clearGraph() {
        graphPane.getChildren().clear();
    }

    public void selectVertices() {
        for (Circle vertexCircle : drawnVertices) {
            vertexCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (isStartChooseActive) {
                        if (startVertexCircle != null) {
                            startVertexCircle.setFill(Paint.valueOf("violet"));
                        }
                        vertexCircle.setFill(Paint.valueOf("black"));
                        startVertexCircle = vertexCircle;
                        isStartChooseActive = false;
                        vertexCircle.getScene().setCursor(Cursor.DEFAULT);
                    } else if (isEndChooseActive) {
                        if (endVertexCircle != null) {
                            endVertexCircle.setFill(Paint.valueOf("violet"));
                        }
                        vertexCircle.setFill(Paint.valueOf("blue"));
                        endVertexCircle = vertexCircle;
                        isEndChooseActive = false;
                        vertexCircle.getScene().setCursor(Cursor.DEFAULT);
                    }
                }
            });
            vertexCircle.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (isStartChooseActive || isEndChooseActive()) {
                        vertexCircle.getScene().setCursor(Cursor.HAND);
                    }
                }
            });
            vertexCircle.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                        vertexCircle.getScene().setCursor(Cursor.DEFAULT);
                }
            });

        }
    }

    public boolean isStartChooseActive() {
        return isStartChooseActive;
    }

    public boolean isEndChooseActive() {
        return isEndChooseActive;
    }

    public void setStartChooseActive(boolean startChooseActive) {
        isStartChooseActive = startChooseActive;
    }

    public void setEndChooseActive(boolean endChooseActive) {
        isEndChooseActive = endChooseActive;
    }
}
