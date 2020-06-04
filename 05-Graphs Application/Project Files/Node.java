/** 
 * This class represents a node of a graph.
 * @author Ali Al-Musawi
 */

public class Node {
    private int name; // the name (label) of the Node instance.
    private boolean mark; // a status flag used to categorize a node.
    
    /** 
     * Node constructor
     * @param name the integer label of the node.
     * @return a Node instance.
     */
    public Node(int name) {
        this.name = name;
    }

    /** 
     * Mutator Method
     * @param mark a logical flag that marks a Node.
     */
    public void setMark(boolean mark) {
        this.mark = mark;
    }

    /** 
     * Accessor Method
     * @return the mark/status of this Node.
     */
    public boolean getMark() {
        return mark;
    }

    /** 
     * Accessor Method
     * @return the name/label of this Node.
     */
    public int getName() {
        return name;
    }
}
     