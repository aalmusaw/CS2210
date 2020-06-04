/** 
 * This class represents a Graph
 * @author Ali Al-Musawi
 */
import java.util.Iterator;

public class Graph {
    private Vector<Node> nodes; // the nodes (vertices) of this Graph.
    private Matrix<Edge> adjMatrix; // the adjacency matrix of this Graph.
    private int size; // the maximum number of nodes stored in this Graph.

    /** 
     * Class Constructor
     * @param n the number of nodes to store in this Graph.
     * @return a Graph instance.
     */
    public Graph(int n) {
        size = n;
        nodes = new Vector(n);
        for (int i = 0; i < n; i++) nodes.add(new Node(i));
        adjMatrix = new Matrix(n, n);
    }

    /** 
     * Accessor Method
     * @param name the name of the Node to access.
     * @return a reference to the Node with name 'name'.
     * @throws GraphException if no such Node exists.
     */
    public Node getNode(int name) throws GraphException {
        try {
            return nodes.get(name);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new GraphException();
        }
    }

    /** 
     * Mutator Method
     * @param u the first endpoint of the Edge to add.
     * @param v the second endpoint of the Edge to add.
     * @param edgeType one of {-1, 0, 1} for "reward", "public", and "private".
     * throws GraphException if no such Node exists or if there is an edge already between u and v.
     */
    public void insertEdge(Node u, Node v, int edgeType) {
        try {
            int i = u.getName();
            int j = v.getName();
            if (!adjMatrix.isOccupied(i, j)) {
                adjMatrix.set(i, j, new Edge(u, v, edgeType));
                adjMatrix.set(j, i, new Edge(v, u, edgeType));
            }
            else throw new GraphException();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new GraphException();
        }
    }

    /** 
     * This method returns an iterable containing all edges incident on node u.
     * @param u a Node on which some edges are incident on.
     * @return an iterable.
     * @throws GraphException if no such Node exists
     */
    public Iterator incidentEdges(Node u) throws GraphException {
        try {
            return adjMatrix.getByAxis(0, u.getName()).iterator();
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new GraphException();
        }
    }

    /** 
     * This method returns the edge connecting nodes u, v.
     * @param u the Node u
     * @param v the Node v
     * @return the edge u----v
     * @throws GraphException if no such Node exists.
     */
    public Edge getEdge(Node u, Node v) throws GraphException {
        try {
            Edge edge = adjMatrix.get(u.getName(), v.getName());
            if (edge != null) return edge;
            else throw new GraphException();
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new GraphException();
        }
    }

    /** 
     * This method verifies if two nodes u,v are adjacent.
     * @param u Node u
     * @param v Node v
     * @return true if the nodes are adjacent and false otherwise.
     * @throws GraphException if no such Node exists.
     */
    public boolean areAdjacent(Node u, Node v) throws GraphException {
        try {
            return adjMatrix.isOccupied(u.getName(), v.getName());
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new GraphException();
        }
    }
}

    
 
    
