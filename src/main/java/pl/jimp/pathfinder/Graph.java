package pl.jimp.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int numOfRows;
    private int numOfColumns;
    private List<Vertex> vertices;
    private boolean splitMode = false;

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
        return switch (j) {
            case 0 -> i - numOfColumns;
            case 1 -> i + numOfColumns;
            case 2 -> i + 1;
            case 3 -> i - 1;
            default -> -1;
        };
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
    public void setSplitMode(boolean mode) {
        splitMode = mode;
    }
    public boolean getSplitMode() {
        return splitMode;
    }

    @Override
    public String toString() {
        return "Graph: " +
                "numOfRows=" + numOfRows +
                ", numOfColumns=" + numOfColumns +
                ", vertices=\n" + vertices;
    }
}
