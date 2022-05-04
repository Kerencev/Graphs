public interface Graph {

    void addVertex(String ladel);

    boolean addEdge(String startLabel, String secondLabel, int weight);

    int getSize();

    void display();

    void dfs(String startLabel);

    void bfs(String startLabel);
}
