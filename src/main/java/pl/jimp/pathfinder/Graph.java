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

    private void createVertices() {
        for (int i = 0; i < numOfRows * numOfColumns; i++) {
            vertices.add(new Vertex(i));
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

    public int getIndexOfTheVertex(int i, int j) {
        switch (j) {
            case 0:
                return i - numOfColumns;
            case 1:
                return i + numOfColumns;
            case 2:
                return i + 1;
            case 3:
                return i - 1;
            default:
                return -1;
        }
    }

    public int getDirectionFromIndex(int vertexA, int vertexB) {
        if (vertexB == vertexA - numOfColumns) {
            return 0;
        } else if (vertexB == vertexA + numOfColumns) {
            return 1;
        } else if (vertexB == vertexA + 1) {
            return 2;
        } else if (vertexB == vertexA - 1) {
            return 3;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Graph: " +
                "numOfRows=" + numOfRows +
                ", numOfColumns=" + numOfColumns +
                ", vertices=\n" + vertices;
    }
}
