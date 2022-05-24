package pl.jimp.pathfinder;

import java.util.List;

public class GraphSplitter {
	private final Graph graph;
	List<Integer> pathOfTheSplit;

	public GraphSplitter(Graph graph, List<Integer> path) {
		this.graph = graph;
		this.pathOfTheSplit = path;
	}

	public void splitGraph(){
		/*if(startVertexField.isEmpty() || endVertexField.isEmpty()){
			return new InfoLabel("Please put start and end vertices values to split the graph.", InfoLabelSource.SPLIT, true);
		}

		int startVertex = Integer.parseInt(startVertexField);
		int endVertex = Integer.parseInt(endVertexField);
		*/
		//Search findPath = new Search(graph);
		/*findPath.setStartVertex(startVertex);
		findPath.setEndVertex(endVertex);*/

		//List<Integer> pathOfTheSplit = findPath.dijkstra();
		System.out.println("path = " + pathOfTheSplit);
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

	}

	public InfoLabel checkSplitArguments(double distance) {
		int start = pathOfTheSplit.get(0);
		int end = pathOfTheSplit.get(pathOfTheSplit.size() - 1);
		if((end >= graph.getNumOfColumns() && end <= graph.getNumOfColumns() * (graph.getNumOfRows() - 1) && end % graph.getNumOfColumns() != 0 && end % graph.getNumOfColumns() != graph.getNumOfColumns() - 1)
		|| (start >= graph.getNumOfColumns() && start <= graph.getNumOfColumns() * (graph.getNumOfRows() - 1) && start % graph.getNumOfColumns() != 0 && start % graph.getNumOfColumns() != graph.getNumOfColumns() - 1)) {
			return new InfoLabel("Those vertices are NOT at the edges of the graph, choose different vertices.", InfoLabelSource.SPLIT, true);
		}
		if(distance == Double.MAX_VALUE) {
			return new InfoLabel("Those vertices are NOT connected, choose different vertices.", InfoLabelSource.SPLIT, true);
		}
		return null;
	}
	public Graph getGraph() {
		return graph;
	}


}
