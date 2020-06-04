/** 
 * This class represents an edge of a graph.
 * @author Ali Al-Musawi
 */

public class Edge {
    private int type; // one of {-1, 0, 1} representing {"reward", "public", "private"}
    private Node end1; // the first endpoint of this Edge.
    private Node end2; // the second endpoint of this Edge.
    
    /** 
     * Edge constructor
     * @param u the first endpoint of this Edge.
     * @param v the second endpoint of this Edge.
     * @param type the type of this Edge, i.e. -1 for "reward", 0 for "public", 1 for "private".
     * @return an Edge instance.
     */
    public Edge(Node u, Node v, int type) {
        end1 = u;
        end2 = v;
        this.type = type;       
    }

    /**
     * Accessor Method
     * @return the first endpoint of this Edge.
     */
    public Node firstEndpoint() {
        return end1;
    }

    /** 
     * Accessor Method
     * @return the second endpoint of this Edge.
     */
    public Node secondEndpoint() {
        return end2;
    }

    /** 
     * Accessor Method
     * @return the type of this Edge.
     */
    public int getType() {
        return type;
    }
}
     