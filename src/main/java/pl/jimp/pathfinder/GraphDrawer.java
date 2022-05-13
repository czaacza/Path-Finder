package pl.jimp.pathfinder;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Collections;
import java.util.List;

public class GraphDrawer {

    private AnchorPane graphPane;
    private AnchorPane edgePane;
    private Graph graph;
    private Search search;

    private double circleRadius;
    private final static double PANE_WIDTH = 1000.0;
    private final static double PANE_HEIGHT = 750.0;

    private Circle [] drawnVertices;
    private Line[][] drawnEdges;


    public GraphDrawer(AnchorPane graphPane,AnchorPane edgePane, Graph graph) {
        this.graphPane = graphPane;
        this.edgePane = edgePane;
        this.graph = graph;
        circleRadius = PANE_WIDTH * graph.getNumOfRows() > PANE_HEIGHT * graph.getNumOfColumns() ? PANE_HEIGHT / graph.getNumOfRows() / 4.0 : PANE_WIDTH / graph.getNumOfColumns() / 4.0;
        drawnVertices = new Circle[graph.getNumOfRows()* graph.getNumOfColumns()];
        drawnEdges = new Line[graph.getNumOfColumns()* graph.getNumOfRows()][4];
    }

    public void drawGraph(){
        for(int row = 0; row < graph.getNumOfRows(); row++){
            for(int column = 0; column < graph.getNumOfColumns(); column++){
                Circle vertexCircle = new Circle();
                vertexCircle.setFill(Paint.valueOf("violet"));
                vertexCircle.setRadius(circleRadius);
                vertexCircle.setLayoutY(circleRadius + (row * circleRadius*4));
                vertexCircle.setLayoutX(circleRadius + (column *circleRadius*4));


                int finalColumn = column;
                int finalRow = row;

                vertexCircle.setOnMouseClicked(event -> {
                    if(search == null){
                        search = new Search(graph);
                        search.startVertex = finalRow * graph.getNumOfColumns() + finalColumn;
                    } else {
                        search.endVertex = finalRow * graph.getNumOfColumns() + finalColumn;
                        System.out.println("start = " + search.startVertex + " end = " + search.endVertex);
                        drawPath(search.dijkstra());
                        search = null;
                    }
                });

                drawnVertices[column + (row * graph.getNumOfColumns())] = vertexCircle;
                graphPane.getChildren().add(drawnVertices[column + (row * graph.getNumOfColumns())]);
            }
        }
        drawEdges(drawnVertices);
    }



    public void drawEdges(Circle [] drawnVertices) {

        double maxWeight = 0.0;
        double minWeight = Double.MAX_VALUE;
        for(int i=0; i < graph.getNumOfColumns() * graph.getNumOfRows() - 1; i++) {
            if(maxWeight < Collections.max(graph.getVertices().get(i).getWeights()))
                maxWeight = Collections.max(graph.getVertices().get(i).getWeights());
            if(minWeight > Collections.min(graph.getVertices().get(i).getWeights()))
                minWeight = Collections.min(graph.getVertices().get(i).getWeights());
        }

        for(int i=0; i < graph.getNumOfColumns() * graph.getNumOfRows(); i++) {
            for (int j=0; j<4; j++) {
                if((graph.getVertices().get(i).getWeights().get(j)) > 0) {
                    Line edgeLine = new Line();

                    double amountOfRedColor = graph.getVertices().get(i).getWeights().get(j) / (maxWeight - minWeight) + minWeight;
                    edgeLine.setStroke(Color.color(amountOfRedColor, 0.9, 0.0));
                    edgeLine.setStrokeWidth(drawnVertices[i].getRadius()/2);
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
    private void drawPath(List<Integer> path) {
        for(int i = 0; i < path.size() - 1; i++){
            drawnVertices[path.get(i)].setFill(Color.BLACK);
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
            drawnEdges[path.get(i)][j].setStroke(Color.BLACK);
            drawnEdges[path.get(i+1)][k].setStroke(Color.BLACK);
        }
        drawnVertices[path.get(path.size() - 1)].setFill(Color.BLACK);
    }

    public void clearGraph(){
        graphPane.getChildren().clear();
        edgePane.getChildren().clear();
    }

}
