package pl.jimp.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int numOfRows;
    private int numOfColumns;
    private List<Vertex> vertices;

    public Graph(int numOfRows, int numOfColumns) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.vertices = new ArrayList<>(numOfRows * numOfColumns);
        createVertices();
    }

    private void createVertices(){
        for(int i = 0; i < numOfRows * numOfColumns; i++){
            vertices.set(i, new Vertex(i));
        }
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }
}
