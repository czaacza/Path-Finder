package pl.jimp.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    int vertexNumber;
    private List<Double> weights;

    public Vertex(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        this.weights = new ArrayList<>(4);
    }

    public void setWeight(int index, double weight){
        if(index < 0 || index > 3){
            throw new IllegalArgumentException("ERROR: Index of added weight should be >= 0 and <= 3");
        }
        weights.set(index, weight);
    }
}
