package pl.jimp.pathfinder;

import java.util.*;

public class Search {
	public int startVertex;
	public int endVertex;
	private Graph graph;

	public Search(Graph graph){
		this.graph = graph;
	}

	public List<Integer> dijkstra() {
		int numOfVertices = graph.getNumOfColumns() * graph.getNumOfRows();
		boolean[] visited = new boolean[numOfVertices];
		int[] pred = new  int[numOfVertices];
		double[] distance = new double[numOfVertices];
		double minDistance;
		int count = 0;
		int nextNode = 0;
		int k = -1;

		for (int i = 0; i < numOfVertices; i++)
		{
			visited[i] = false;
			pred[i] = startVertex;
			distance[i] = Double.MAX_VALUE;
		}

		for (int i = 0; i < 3; i++)
		{
			if (graph.getVertices().get(startVertex).getWeights().get(i) != 0)
				k = graph.getIndexOfTheVertex(startVertex, i);
			if (k != -1)
			{
				distance[k] = graph.getVertices().get(startVertex).getWeights().get(i);
			}
		}
		visited[startVertex] = true;
		distance[startVertex] = 0;
		count++;

		while (count < numOfVertices - 1) {
			minDistance = Double.MAX_VALUE;
			for (int i = 0; i < numOfVertices; i++)
			{
				if (distance[i] < minDistance && !visited[i])
				{
					minDistance = distance[i];
					nextNode = i;
				}
			}
			visited[nextNode] = true;
			for (int i = 0; i < 4; i++)
			{
				if (graph.getVertices().get(nextNode).getWeights().get(i) != 0)
					k = graph.getIndexOfTheVertex(nextNode, i);
				if (k != -1)
				{
					if (!visited[k] && minDistance + graph.getVertices().get(nextNode).getWeights().get(i) < distance[k] && graph.getVertices().get(nextNode).getWeights().get(i) != 0)
					{
						distance[k] = minDistance + graph.getVertices().get(nextNode).getWeights().get(i);
						pred[k] = nextNode;
					}
				}
			}
			count++;
		}

		System.out.println("distance = " + distance[endVertex]);
		int numOfElements = 0;
		List<Integer> path = new ArrayList<>();
		path.add(endVertex);
		do
		{
			path.add(pred[path.get(numOfElements)]);
			numOfElements++;
		} while (path.get(numOfElements) != startVertex);
		Collections.reverse(path);
		for (int i = 0; i < numOfElements; i++) {
			System.out.print(path.get(i) + "->");
		}
		System.out.print(path.get(numOfElements));
		return path;
	}
}
