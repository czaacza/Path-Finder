package pl.jimp.pathfinder;

import java.util.List;

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

        int startVertex = Integer.parseInt(startVertexField);
        int endVertex = Integer.parseInt(endVertexField);

        Search findPath = new Search(graph);
        findPath.setStartVertex(startVertex);
        findPath.setEndVertex(endVertex);

        List<Integer> pathOfTheSplit = findPath.dijkstra();

        for (int currentVertex : pathOfTheSplit) {
            if(graph.getVertices().get(currentVertex).getWeights().get(1) != 0){
                graph.getVertices().get(currentVertex).setWeight(1, 0);
                graph.getVertices().get(graph.getIndexOfTheVertex(currentVertex, 1)).setWeight(0, 0);
            }

            if(graph.getVertices().get(currentVertex).getWeights().get(2) != 0) {
                graph.getVertices().get(currentVertex).setWeight(2, 0);
                graph.getVertices().get(graph.getIndexOfTheVertex(currentVertex, 2)).setWeight(3, 0);
            }
        }

        return null;
    }

    Graph getGraph() {
        return graph;
    }


}
