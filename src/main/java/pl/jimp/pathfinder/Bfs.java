package pl.jimp.pathfinder;

import java.util.Iterator;
import java.util.LinkedList;

public class Bfs {
    private Graph graph;

    public Bfs(Graph graph){
        this.graph = graph;
    }

    public boolean checkIfConnected() {
        int currentVertex = 0;
        int numOfVertices = graph.getNumOfColumns() * graph.getNumOfRows();
        boolean[] visited = new boolean[numOfVertices];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[currentVertex] = true;
        queue.add(currentVertex);

        while (queue.size() != 0) {
            currentVertex = queue.poll();
            for(int i = 0; i < 4; i++) {
                int k = -1;
                if (i == 0 && graph.getVertices().get(currentVertex).getWeights().get(i) != 0)
                    k = currentVertex - graph.getNumOfColumns();
                if (i == 1 && graph.getVertices().get(currentVertex).getWeights().get(i) != 0)
                    k = currentVertex + graph.getNumOfColumns();
                if (i == 2 && graph.getVertices().get(currentVertex).getWeights().get(i) != 0)
                    k = currentVertex + 1;
                if (i == 3 && graph.getVertices().get(currentVertex).getWeights().get(i) != 0)
                    k = currentVertex - 1;
                if (k != -1 && !visited[k])
                {
                    visited[k] = true;
                    queue.add(k);
                }
            }
        }
        for (int i = 0; i < numOfVertices; i++)
            if (!visited[i])
            {
                return false;
            }
        return true;
    }
}
