package pl.jimp.pathfinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1333, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/main/resources/pl/jimp/pathfinder/data/mygraph");

        URL url = HelloApplication.class.getResource("data/mygraph");
        System.out.println(url.getPath());

        GraphLoader graphLoader = new GraphLoader("src/main/resources/pl/jimp/pathfinder/data/mygraph");
        graphLoader.loadGraph();
        launch();
    }
}