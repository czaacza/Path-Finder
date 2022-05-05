package pl.jimp.pathfinder;

public class GraphSplitter {
    private Graph graph;
    private String startVertexField;
    private String endVertexField;

    public GraphSplitter(Graph graph, String startVertexField, String endVertexField) {
        this.graph = graph;
        this.startVertexField = startVertexField;
        this.endVertexField = endVertexField;
    }

    public InfoLabel splitGraph(){
        if(startVertexField.isEmpty() || endVertexField.isEmpty()){
            return new InfoLabel("Please put start and end vertices values to split the graph.", InfoLabelSource.SPLIT, true);
        }

        return null;
    }


}
