package pl.jimp.pathfinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 1000);
        stage.setTitle("Graph Path Finder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {

        GraphLoader graphLoader = new GraphLoader("src/main/resources/pl/jimp/pathfinder/data/mygraph");
        graphLoader.loadGraph();
        Graph graph = graphLoader.getGraph();
        GraphSaver graphSaver = new GraphSaver(graph, "src/main/resources/pl/jimp/pathfinder/data/example");
        graphSaver.saveGraph();
        launch();
    }
}