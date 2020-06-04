/* This class represents a binary tree node.
 * @author Ali Al-Musawi
 */
public class BinaryNode {
    private Pixel value; // the data item stored in.
    private BinaryNode parent; // the parent of this node.
    private BinaryNode left; // the left child of this node.
    private BinaryNode right; // the right child of this node.

    /* Class Constructor.
     * @param value the Pixel this node stores.
     * @param parent the parent of this node.
     * @param left the left child of this node.
     * @param right the right child of this node.
     * @return an instance of a BinaryNode.
     */
    public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    /* Class Constructor
     * This constructor initializes a leaf node.
     * @return a node with null fields.
     */
    public BinaryNode() {
        this.value = null;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    /* Accessor Method
     * @return the parent of this node.
     */
    public BinaryNode getParent() {
        return parent;
    }

    /* sets the parent of this node to the given node.
     * @param parent the node that is to be made this node's parent.
     */
    public void setParent(BinaryNode parent) {
        this.parent = parent;
    }

    /* sets the left child of this node to the given node.
     * @param p the node that is to be made this node's left child.
     */
    public void setLeft(BinaryNode p) {
        left = p;
    }

    /* sets the right child of this node to the given node.
     * @param p the node that is to be made this node's right child.
     */
    public void setRight(BinaryNode p) {
        right = p;
    }

    /* sets the pixel of this node to the given value.
     * @param value the Pixel that is to be made this node's data.
     */
    public void setData(Pixel value) {
        this.value = value;
    }

    /* checks whether or not this node is a leaf.
     * @return true if this node is a leaf, and false otherwise.
     */
    public boolean isLeaf() {
        return (right == null && left == null);
    } 

    /* Accessor Method
     * @return the left child of this node.
     */
    public BinaryNode getLeft() {
        return left;
    }

    /* Accessor Method
     * @return the right child of this node.
     */
    public BinaryNode getRight() {
        return right;
    }

    /* Accessor Method
     * @return the Pixel of this node.
     */
    public Pixel getData() {
        return value;
    }
}    