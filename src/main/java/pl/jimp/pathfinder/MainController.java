package pl.jimp.pathfinder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainController {

    Graph graph;

    @FXML
    private AnchorPane mainPane;

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

    public void submitGenerate(ActionEvent event) {

    }

    public void submitLoad(ActionEvent event) throws Exception {
        System.out.println("graphLoader(" + inputLoadPathField.getText() + ")");

       GraphLoader graphLoader = new GraphLoader(inputLoadPathField.getText());
       InfoLabel loadInfo = graphLoader.loadGraph();
        mainPane.getChildren().add(loadInfo);
            loadInfo.showInfoLabel();
            if(!loadInfo.isError()){
                graph = graphLoader.graph;
                System.out.println(graph);
            }

    }

    public void submitSave(){
        System.out.println("submitSave()");
        InfoLabel saveInfo = null;
        if(graph == null){
            saveInfo = new InfoLabel("Graph not generated. I cannot save it.", InfoLabelSource.SAVE, true);
        } else {
            saveInfo = new InfoLabel("Graph successfully saved.", InfoLabelSource.SAVE, false);
        }

        mainPane.getChildren().add(saveInfo);
        saveInfo.showInfoLabel();
    }


}
