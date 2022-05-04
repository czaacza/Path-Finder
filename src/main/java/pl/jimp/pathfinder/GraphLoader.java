package pl.jimp.pathfinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphLoader {
    String inputFileName;
    private int numOfRows;
    private int numOfColumns;
    Graph graph;


    public GraphLoader(String inputFileName) {
        this.inputFileName = inputFileName;
    }


    public InfoLabel loadGraph() {

        File inputFile = new File(inputFileName);

        Scanner scanner = null;
        Scanner lineNumberScanner = null;

        try {
            scanner = new Scanner(inputFile);
            lineNumberScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            return new InfoLabel("Cannot load the graph from '" + inputFileName + "'. File not found.", InfoLabelSource.LOAD, true);
        }

        lineNumberScanner.nextLine();

        if (scanner.hasNextInt()) {
            numOfRows = scanner.nextInt();
        } else {
            return new InfoLabel("Wrong file format. Couldn't load number of rows.", InfoLabelSource.LOAD, true);
        }
        if (scanner.hasNextInt()) {
            numOfColumns = scanner.nextInt();
        } else {
            return new InfoLabel("Wrong file format. Couldn't load number of columns.", InfoLabelSource.LOAD, true);
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
                    return new InfoLabel("Wrong file format. There is no ':' separating vertex and weight numbers in '" + inputFileName +"' line no: " + (vertexNum+2),
                            InfoLabelSource.LOAD, true);
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
