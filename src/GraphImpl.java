import java.util.*;

public class GraphImpl implements Graph{

    private final List<Vertex> vertexList;
    private int[][] adjMatrix;

    private int sum;
    private List<String> way;
    private List<Integer> allSums;
    private HashMap<Integer, List<String>> allWays;

    public GraphImpl(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new int[maxVertexCount][maxVertexCount];

        this.sum = 0;
        this.way = new ArrayList<>();
        this.allSums = new ArrayList<>();
        this.allWays = new HashMap<>();
    }

    @Override
    public void addVertex(String ladel) {
        vertexList.add(new Vertex(ladel));
    }


    @Override
    public boolean addEdge(String startLabel, String secondLabel, int weight) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(secondLabel);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        adjMatrix[startIndex][endIndex] = weight;
        return true;
    }

    private int indexOf(String label) {

        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getLabel().equals(label)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getSize(); i++) {
            sb.append(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMatrix[i][j] > 0) {
                    sb.append(" -> ").append(vertexList.get(j)).append(" Weight: ").append(adjMatrix[i][j]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void dfs(String startLabel) {
        int startindex = indexOf(startLabel);

        if (startindex == -1) {
            throw new IllegalArgumentException("???????????????? ?????????????? " + startLabel);
        }

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startindex);

        visitVertex(stack, vertex, true);

        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex == null) {
                stack.pop();
            } else {
                visitVertex(stack, vertex, true);
            }
        }
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex, boolean isPrint) {
        if (isPrint) {
            System.out.println(vertex.getLabel() + " ");
        }
        stack.push(vertex);
        vertex.setVisited(true);
    }

    public void findShortestPath(String startLabel) {

        int startindex = indexOf(startLabel);

        if (startindex == -1) {
            throw new IllegalArgumentException("???????????????? ?????????????? " + startLabel);
        }

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startindex);

        visitVertex(stack, vertex, false);
        way.add(vertex.getLabel());

        int keyHashMap = 0;
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex == null) {
                if (sum != 0) {
                    for (int i = 0; i < getSize(); i++) {
                        sum += adjMatrix[vertexList.indexOf(stack.peek())][i];
                    }

                    allWays.put(keyHashMap, new ArrayList<>(way));
                    allSums.add(sum);

                    sum = 0;
                    way.clear();
                    keyHashMap++;
                }
                stack.pop();
            } else {
                sum += adjMatrix[vertexList.indexOf(stack.peek())][vertexList.indexOf(vertex)];
                way.add(vertex.getLabel());
                visitVertex(stack, vertex, false);
            }
        }

        int indexOfMinPath = findIndexOfShortPath(allSums);
        int minPath = allSums.get(indexOfMinPath);

        ArrayList<String> list = new ArrayList<>(allWays.get(indexOfMinPath));
        if (indexOfMinPath != 0) {
            list.add(0, vertexList.get(0).getLabel());
            list.add(vertexList.get(vertexList.size() - 1).getLabel());
        }

        System.out.println("?????????????????????? ????????: " + list + " ??????????: "+ minPath);
    }

    private int findIndexOfShortPath(List<Integer> list) {
        int min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (allSums.get(i) < min) {
                min = allSums.get(i);
            }
        }
        return list.indexOf(min);
    }

    public void printAdjMatrix () {
        System.out.print("  ");
        for (int i = 0; i < getSize(); i++) {
            System.out.print(vertexList.get(i).getLabel() + " ");
        }
        System.out.println();

        for (int i = 0; i < getSize(); i++) {
            System.out.print(vertexList.get(i).getLabel() + " ");
            for (int j = 0; j < getSize(); j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);

        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] > 0 && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        System.out.println(vertex.getLabel() + " ");
        queue.add(vertex);
        vertex.setVisited(true);
    }

    @Override
    public void bfs(String startLabel) {
        int startindex = indexOf(startLabel);

        if (startindex == -1) {
            throw new IllegalArgumentException("???????????????? ?????????????? " + startLabel);
        }

        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startindex);

        visitVertex(queue, vertex);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex == null) {
                queue.remove();
            } else {
                visitVertex(queue, vertex);
            }
        }
    }
}
