public class Main {

    public static void main(String[] args) {
        GraphImpl graph = new GraphImpl(10);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");
        graph.addVertex("F");

        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 3);
        graph.addEdge("C", "F", 4);

        graph.addEdge("A", "D", 2);
        graph.addEdge("D", "E", 6);
        graph.addEdge("E", "G", 1);
        graph.addEdge("G", "F", 1);

        graph.addEdge("A", "H", 3);
        graph.addEdge("H", "I", 2);
        graph.addEdge("I", "J", 1);
        graph.addEdge("J", "F", 1);

        graph.findShortestPath("A");

    }
}
