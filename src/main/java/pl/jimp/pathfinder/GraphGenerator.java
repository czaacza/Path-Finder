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

    public InfoLabel checkGenerateArguments() {

        if (numOfRows <= 0) {
            return new InfoLabel("Number of rows should be greater than 0", InfoLabelSource.GENERATE, true);
        }
        if (numOfColumns <= 0) {
            return new InfoLabel("Number of columns should be greater than 0", InfoLabelSource.GENERATE, true);
        }
        if (minWeight <= 0) {
            return new InfoLabel("Minimal weight should be greater than 0", InfoLabelSource.GENERATE, true);
        }
        if (maxWeight > 1000) {
            return new InfoLabel("Maximal weight should be less than 1000", InfoLabelSource.GENERATE, true);
        }
        if (minWeight > maxWeight) {
            return new InfoLabel("Minimal weight should NOT be greater than maximal weight", InfoLabelSource.GENERATE, true);
        }
        if (chance < 0 || chance > 100) {
            return new InfoLabel("% for no connection should NOT be less than 0 or greater than 100", InfoLabelSource.GENERATE, true);
        }
        return new InfoLabel("", InfoLabelSource.GENERATE, false);
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
        if(min == max)
            return min;
        Random random = new Random();
        return random.nextDouble(max - min) + min;
    }

}
