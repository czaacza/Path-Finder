package pl.jimp.pathfinder;

import java.util.Random;

public class GraphGenerator {
    private int numOfRows;
    private int numOfColumns;
    private double minWeight;
    private double maxWeight;
    private double chance;

    public GraphGenerator(int numOfRows, int numOfColumns, double minWeight, double maxWeight, double chance) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.chance = chance;
    }

    public Graph generateGraph() {
        Graph graph = new Graph(numOfRows, numOfColumns);

        for (int i = 0; i < numOfRows * numOfColumns - 1; i++) {

            double weight = 0;
            if (chance < randomNumberInRange(0, 100)) {
                weight = randomNumberInRange(minWeight, maxWeight);
            }

            if (i >= numOfColumns * (numOfRows - 1)) {
                graph.getVertices().get(i).setWeight(2, weight);
                graph.getVertices().get(i + 1).setWeight(3, weight);

            } else if (i % numOfColumns == numOfColumns - 1) {
                graph.getVertices().get(i).setWeight(1, weight);
                graph.getVertices().get(i + numOfColumns).setWeight(0, weight);

            } else {
                graph.getVertices().get(i).setWeight(2, weight);
                graph.getVertices().get(i + 1).setWeight(3, weight);

                if (chance < randomNumberInRange(0, 100)) {
                    weight = randomNumberInRange(minWeight, maxWeight);
                }

                graph.getVertices().get(i).setWeight(1, weight);
                graph.getVertices().get(i + numOfColumns).setWeight(0, weight);
            }

        }

        return graph;
    }

    private double randomNumberInRange(double min, double max) {
        Random random = new Random();
        return random.nextDouble(max - min) + min;
    }

}
