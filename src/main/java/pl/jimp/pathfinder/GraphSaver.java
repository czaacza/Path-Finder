package pl.jimp.pathfinder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GraphSaver {
    Graph graph;
    String outputFileName;

    public GraphSaver(Graph graph, String outputFileName) {
        this.graph = graph;
        this.outputFileName = outputFileName;
    }

    public InfoLabel saveGraph() {
        if(graph == null){
            return new InfoLabel("Graph not generated. I cannot save it.", InfoLabelSource.SAVE, true);
        }
        String outputFilePath = "src/main/resources/pl/jimp/pathfinder/data/" + outputFileName;
        File outputFile = new File(outputFilePath);

        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Couldn't create file " + outputFileName);
            }
        }

        if (!outputFile.canWrite()) {
            System.out.println("No permissions to write to file " + outputFileName);
        }

        try {
            FileWriter fileWriter = new FileWriter(outputFilePath, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            System.out.println("saveGraph() succesfully saved");
            printWriter.println(graph.getNumOfRows() + " " + graph.getNumOfColumns());
            for (int vertexIndex = 0; vertexIndex < graph.getNumOfRows() * graph.getNumOfColumns(); vertexIndex++) {
                printWriter.print("\t");
                for (int weightIndex = 0; weightIndex < 4; weightIndex++) {
                    if (graph.getVertices().get(vertexIndex).getWeights().get(weightIndex) != 0) {
                        switch (weightIndex) {
                            case 0:
                                printWriter.print((vertexIndex - graph.getNumOfColumns()) + " :" + graph.getVertices().get(vertexIndex).getWeights().get(0));
                                break;
                            case 1:
                                printWriter.print((vertexIndex + graph.getNumOfColumns()) + " :" + graph.getVertices().get(vertexIndex).getWeights().get(1));
                                break;
                            case 2:
                                printWriter.print((vertexIndex + 1) + " :" + graph.getVertices().get(vertexIndex).getWeights().get(2));
                                break;
                            case 3:
                                printWriter.print((vertexIndex - 1) + " :" + graph.getVertices().get(vertexIndex).getWeights().get(3));
                                break;
                        }
                        printWriter.print(" ");
                    }
                }
                printWriter.print("\n");
            }


            printWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Couldn't write to file " + outputFileName);
        }


        return null;
    }
}
