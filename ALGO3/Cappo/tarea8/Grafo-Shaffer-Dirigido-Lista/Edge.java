class Edge {
  private int vert, wt;

  public Edge(int v, int w) // Constructor
    { vert = v;  wt = w; }

  public int vertex() { return vert; }
  public int weight() { return wt; }
}