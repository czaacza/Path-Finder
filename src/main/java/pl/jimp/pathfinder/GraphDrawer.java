package pl.jimp.pathfinder;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GraphDrawer {

    private AnchorPane graphPane;
    private Graph graph;

    private double circleRadius;
    private final static double PANE_WIDTH = 1000.0;
    private final static double PANE_HEIGHT = 750.0;

    private Circle [] drawnVertices;


    public GraphDrawer(AnchorPane graphPane, Graph graph) {
        this.graphPane = graphPane;
        this.graph = graph;
        circleRadius = PANE_HEIGHT / graph.getNumOfRows() / 2.0;
        drawnVertices = new Circle[graph.getNumOfRows()* graph.getNumOfColumns()];
    }

    public void drawGraph(){
        for(int row = 0; row < graph.getNumOfRows(); row++){
            for(int column = 0; column < graph.getNumOfColumns(); column++){
                Circle vertexCircle = new Circle();
                vertexCircle.setFill(Paint.valueOf("violet"));
                vertexCircle.setRadius(circleRadius);
                vertexCircle.setLayoutY(circleRadius + (row * circleRadius*2));
                vertexCircle.setLayoutX(circleRadius + (column *circleRadius*2));
                drawnVertices[column + (row * graph.getNumOfColumns())] = vertexCircle;
                graphPane.getChildren().add(drawnVertices[column + (row * graph.getNumOfColumns())]);
            }
        }
    }

    public void clearGraph(){
        graphPane.getChildren().clear();
    }

}
