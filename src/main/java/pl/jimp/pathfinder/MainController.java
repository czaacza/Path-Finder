package pl.jimp.pathfinder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
	private Button splitButton;
	@FXML
	private Label lblSplit;

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
			InfoLabel argumentsInfo = new InfoLabel("Wrong format of argument(s)", InfoLabelSource.GENERATE, true);
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
		if (graph != null) {
			Bfs bfs = new Bfs(graph);
			if (bfs.checkIfConnected()) {
				lblBfs.setText("Graph is connected");
			} else {
				lblBfs.setText("Graph is not connected");
			}
			bfsButton.setDisable(true);
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
		if (graph == null) {
			InfoLabel noSplitGraphInfo = new InfoLabel("Graph is not loaded. There is nothing to split.", InfoLabelSource.SPLIT, true);
			mainPane.getChildren().add(noSplitGraphInfo);
			noSplitGraphInfo.showInfoLabel();
			return;
		}
		graph.setSplitMode(!graph.getSplitMode());
		if(graph.getSplitMode()) {
			lblSplit.setText("ON");
			lblSplit.setTextFill(Color.GREEN);
			return;
		}
		lblSplit.setText("OFF");
		lblSplit.setTextFill(Color.RED);
	}

	public void manageGraph() {
		bfsButton.setDisable(false);
		lblBfs.setText("");
		ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(zoomPane);
		mainPane.getChildren().add(zoomableScrollPane);
		graphDrawer = new GraphDrawer(graphPane, edgePane, graph, lblPathLength, mainPane, lblSplit);
		graphDrawer.drawGraph();
		graph = graphDrawer.getGraph();
	}

}
