package pl.jimp.pathfinder;

import java.io.File;

public class GraphLoader {
    String inputFileName;

    public GraphLoader(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void createGraph(){

    }

    public void loadGraph(){

        File file = new File("plik.txt");
        boolean exists = file.exists();

        System.out.println(file.getAbsolutePath());


    }

}
