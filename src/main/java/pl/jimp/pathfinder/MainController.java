package pl.jimp.pathfinder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

public class MainController {

	Graph graph;
	GraphDrawer graphDrawer;

	@FXML
	private AnchorPane mainPane;

	@FXML
	private AnchorPane graphPane;
	@FXML
	private AnchorPane edgePane;

	@FXML
	private TextField numOfRowsField;

	@FXML
	private TextField numOfColumnsField;

	@FXML
	private TextField minWeightField;

	@FXML
	private TextField maxWeightField;

	@FXML
	private TextField chanceField;

	@FXML
	private Button generateButton;

	@FXML
	private TextField inputLoadPathField;

	@FXML
	private Button loadButton;

	@FXML
	private TextField outputSavePathField;

	@FXML
	private Button saveButton;

	@FXML
	private TextField startVertexField;

	@FXML
	private TextField endVertexField;

	@FXML
	private Button splitButton;

	@FXML
	private Button bfsButton;

	@FXML
	private Label lblBfs;
	@FXML
	private Label lblPathLength;

	@FXML
	private Pane zoomPane;





	public void submitGenerate(ActionEvent event) {
		int numOfRows;
		int numOfColumns;
		double minWeight;
		double maxWeight;
		double chance;
		try {
			numOfRows = Integer.parseInt(numOfRowsField.getText());
			numOfColumns = Integer.parseInt(numOfColumnsField.getText());
			minWeight = Double.parseDouble(minWeightField.getText());
			maxWeight = Double.parseDouble(maxWeightField.getText());
			chance = Double.parseDouble(chanceField.getText());
		} catch (NumberFormatException e) {
			InfoLabel argumentsInfo = new InfoLabel("wrong format of argument(s)", InfoLabelSource.GENERATE, true);
			mainPane.getChildren().add(argumentsInfo);
			argumentsInfo.showInfoLabel();
			return;
		}
		GraphGenerator genGraph = new GraphGenerator(numOfRows, numOfColumns, minWeight, maxWeight, chance);

		InfoLabel generateInfo = genGraph.checkGenerateArguments();
		mainPane.getChildren().add(generateInfo);
		if (generateInfo.isError()) {
			generateInfo.showInfoLabel();
			return;
		}
		graph = genGraph.generateGraph();
		manageGraph();
	}

	public void submitBfs(ActionEvent event) {
		Bfs bfs = new Bfs(graph);
		if (graph == null) {
			lblBfs.setText("Graph is not loaded");
			return;
		}
		if (bfs.checkIfConnected()) {
			lblBfs.setText("Graph is connected");
		} else {
			lblBfs.setText("Graph is not connected");
		}
	}

	public void submitLoad(ActionEvent event) throws Exception {
		GraphLoader graphLoader = new GraphLoader(inputLoadPathField.getText());
		InfoLabel loadInfo = graphLoader.loadGraph();
		mainPane.getChildren().add(loadInfo);
		loadInfo.showInfoLabel();
		if (!loadInfo.isError()) {
			graph = graphLoader.getGraph();
			manageGraph();
		}
		lblBfs.setText("");
	}

	public void submitSave() {
		GraphSaver graphSaver = new GraphSaver(graph, outputSavePathField.getText());

		InfoLabel saveInfo = graphSaver.saveGraph();
		mainPane.getChildren().add(saveInfo);

		saveInfo.showInfoLabel();
	}

	public void submitSplit() {
		graph.setSplitMode(!graph.getSplitMode());
		/*
		InfoLabel splitInfo = graphSplitter.splitGraph();
		if(splitInfo != null && splitInfo.isError()) {
			splitInfo.showInfoLabel();
			return;
		}
		graph = graphSplitter.getGraph();
		manageGraph();
		graph.setSplitMode(false);*/
	}

	public void manageGraph() {
		ZoomableScrollPane zsp = new ZoomableScrollPane(zoomPane);
		mainPane.getChildren().add(zsp);
		graphDrawer = new GraphDrawer(graphPane, edgePane, graph, lblPathLength);
		graphDrawer.drawGraph();
		graph = graphDrawer.getGraph();
	}

}
