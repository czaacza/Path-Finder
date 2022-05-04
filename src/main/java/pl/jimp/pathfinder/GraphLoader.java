package pl.jimp.pathfinder;

import java.io.File;
import java.util.Scanner;

public class GraphLoader {
    String inputFileName;
    private int numOfRows;
    private int numOfColumns;
    Graph graph;


    public GraphLoader(String inputFileName) {
        this.inputFileName = inputFileName;
    }


    public InfoLabel loadGraph() throws Exception {

        File inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
            return new InfoLabel("Cannot load the graph from '" + inputFileName + "'. File not found.", InfoLabelSource.LOAD, true);
       }

            Scanner scanner = new Scanner(inputFile);
            Scanner lineNumberScanner = new Scanner(inputFile);


        lineNumberScanner.nextLine();

        if (scanner.hasNextInt()) {
            numOfRows = scanner.nextInt();
        } else {
            throw new Exception("ERROR: Wrong file format. Couldn't load number of rows.");
        }
        if (scanner.hasNextInt()) {
            numOfColumns = scanner.nextInt();
        } else {
            throw new Exception("ERROR: Wrong file format. Couldn't load number of columns.");
        }

        graph = new Graph(numOfRows, numOfColumns);

        int vertexNum = 0;
        System.out.println(numOfRows + ", " + numOfColumns);

        while (lineNumberScanner.hasNextLine()) {

            String currentLine = lineNumberScanner.nextLine();
            scanner = new Scanner(currentLine);

            while (scanner.hasNextInt()) {
                int checkedVertex = scanner.nextInt();
                double weightValue;
                String weightString;

                weightString = scanner.next();

                if (weightString.charAt(0) != ':') {
                    System.out.println("ERROR: Wrong file format. There is no ':' separating vertex and weight numbers in file " + inputFileName + ", line number: " + vertexNum + 2);
                    System.exit(1);
                }
                weightValue = Double.parseDouble(weightString.substring(1, weightString.length()));

                if (checkedVertex == (vertexNum - numOfColumns)) {
                    graph.getVertices().get(vertexNum).setWeight(0, weightValue);
                }
                if (checkedVertex == (vertexNum + numOfColumns)) {
                    graph.getVertices().get(vertexNum).setWeight(1, weightValue);
                }
                if (checkedVertex == (vertexNum + 1)) {
                    graph.getVertices().get(vertexNum).setWeight(2, weightValue);
                }
                if (checkedVertex == (vertexNum - 1)) {
                    graph.getVertices().get(vertexNum).setWeight(3, weightValue);
                }


            }
            vertexNum++;

        }

        scanner.close();
        lineNumberScanner.close();

        return new InfoLabel("Graph succesfully loaded.", InfoLabelSource.LOAD, false);
    }

}
