package pl.jimp.pathfinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphLoader {
    String inputFileName;
    private int numOfRows;
    private int numOfColumns;


    public GraphLoader(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void createGraph(){

    }

    public void loadGraph() throws FileNotFoundException {

        File inputFile = new File(inputFileName);
        if(!inputFile.exists()){
            throw new FileNotFoundException("ERROR: Cannot load the graph from " + inputFileName + ". File not found.");
        }

        Scanner scanner = new Scanner(inputFile);
        numOfRows = scanner.nextInt();
        numOfColumns = scanner.nextInt();

        System.out.println(numOfRows + ", " + numOfColumns);


    }

}
