/* This class implements an ordered dictionary using
 * a binary search tree. Only internal nodes store Pixels.
 * @author Ali Al-Musawi
 */
public class BinarySearchTree implements BinarySearchTreeADT {
    private BinaryNode r; // the root of the tree
    private int internalNodes;

    /* Class Constructor.
     * @return an instance of a BinarySearchTree.
     */
    public BinarySearchTree() {
        r = new BinaryNode();
        internalNodes = 0;
    }

    /* Accessor Method
     * @param r the root of the subtree to start the search from.
     * @param key the Location of the Pixel used to find the Pixel.
     * @return the Pixel with the given key if it exists, and null otherwise.
     */
    public Pixel get(BinaryNode r, Location key) {
        return find(r, key).getData();
    }

    /* inserts the given data in the tree if no duplicate key exists.
     * @param r the root of the subtree in which insertion starts.
     * @param data the Pixel to insert in the tree.
     * @throws DuplicatedKeyException if the same key already exists.
     */
    public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
        BinaryNode p = find(r, data.getLocation());
        if (!p.isLeaf()) throw new DuplicatedKeyException();
        else {
            p.setData(data);
            p.setLeft(new BinaryNode());
            p.getLeft().setParent(p);
            p.setRight(new BinaryNode());
            p.getRight().setParent(p);
            if (internalNodes == 0) this.r = p;
            internalNodes++;
        }   
    }

    /* removes the node with the given key if it exists.
     * @param r the root of the subtree in which removal occurs.
     * @param key the key of the node to remove.
     * @throws InexistentKeyException if the key cannot be found.
     */
    public void remove(BinaryNode r, Location key) {
        BinaryNode p = find(r, key);
        if (p.isLeaf()) {
            throw new InexistentKeyException();
        }
        else {
            if (p.getLeft().isLeaf() ^ p.getRight().isLeaf()) {
                BinaryNode pp = p.getParent();
                BinaryNode c = (!(p.getLeft().isLeaf())) ? p.getLeft() : p.getRight();
                if (p == getRoot()) {
                    this.r = c;
                    c.setParent(null);
                }
                else {
                    if (pp.getLeft() == p) pp.setLeft(c);
                    else pp.setRight(c);
                    c.setParent(pp);
                }
            }
            else if (p.getLeft().isLeaf() && p.getRight().isLeaf()) {
                if (p == this.r) this.r = new BinaryNode();
                else {
                    BinaryNode pp = p.getParent();
                    if (pp.getLeft() == p) pp.setLeft(new BinaryNode());
                    else pp.setRight(new BinaryNode());
                }
            }
            else {
                BinaryNode s = find(p.getRight(), smallest(p.getRight()).getLocation());
                p.setData(s.getData());
                remove(p.getRight(), s.getData().getLocation());
            }
            internalNodes --;
        }
    }

    /* finds the Pixel with the smallest Location larger than the given one.
     * @param r the root of the subtree where the search starts.
     * @param key the unique Location to compare against to find the successor node.
     * @returns the successor's Pixel or null if there is no successor.
     */
    public Pixel successor(BinaryNode r, Location key) {
        if (r.isLeaf()) return r.getData();
        else {
            BinaryNode p = find(r, key);
            if (!p.isLeaf() && !p.getRight().isLeaf()) return smallest(p.getRight());
            else {
                BinaryNode pp = p.getParent();
                while (p != r && p == pp.getRight()) {
                    p = pp;
                    pp = p.getParent();
                }
                if (p == r) return null;
                else return pp.getData();
            }
        }
    }

    /* finds the Pixel with the largest Location smaller than the given one.
     * @param r the root of the subtree where the search starts.
     * @param key the unique Location to compare against to find the successor node.
     * @returns the predecessor's Pixel or null if there is no predecessor.
     */
    public Pixel predecessor(BinaryNode r, Location key) {
        if (r.isLeaf()) return r.getData();
        else {
            BinaryNode p = find(r, key);
            if (!p.isLeaf() && !p.getLeft().isLeaf()) return largest(p.getLeft());
            else {
                BinaryNode pp = p.getParent();
                while (p != r && p == pp.getLeft()) {
                    p = pp;
                    pp = p.getParent();
                }
                if (p == r) return null;
                else return pp.getData();
            }
        }
    }
    
    /* retrieves the key of the smallest node in the subtree.
     * @param r the root of the subtree in which the search begins.
     * @return the Pixel stored in the smallest node.
     * @throws EmptyTreeException if the tree does not contain any data.
     */
    public Pixel smallest(BinaryNode r) throws EmptyTreeException {
        if (r.isLeaf()) throw new EmptyTreeException();
        else {
            BinaryNode p = r;
            while(!p.isLeaf()) p = p.getLeft();
            return p.getParent().getData();
            }
    }

    /* retrieves the key of the largest node in the subtree.
     * @param r the root of the subtree in which the search begins.
     * @return the Pixel stored in the largest node.
     * @throws EmptyTreeException if the tree does not contain any data.
     */
    public Pixel largest(BinaryNode r) throws EmptyTreeException {
        if (r.isLeaf()) throw new EmptyTreeException();
        else {
            BinaryNode p = r;
            while(!p.isLeaf()) p = p.getRight();
            return p.getParent().getData();
            }
    }

    /* Accessor Method
     * @returns the root of this tree.
     */
    public BinaryNode getRoot() {
        return r;
    }
                   
    /* Helper Method
     * recursively finds the desired node with the given key.
     * @param r the root of the subtree in which the search starts.
     * @param key the unique Location to compare against to find the node.
     * @return the desired node, or the leaf node where it should be stored.
     */
    private BinaryNode find(BinaryNode r, Location key) {
        if (r.isLeaf()) return r;
        else {
            if (r.getData().getLocation().compareTo(key) == 0) return r;
            else if (r.getData().getLocation().compareTo(key) == -1) return find(r.getRight(), key);
            else return find(r.getLeft(), key);
        }
    }

}
