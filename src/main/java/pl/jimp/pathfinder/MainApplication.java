package pl.jimp.pathfinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

	private static final int WINDOW_WIDTH = 1100;
	private static final int WINDOW_HEIGHT = 810;

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
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