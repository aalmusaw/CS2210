/* This class represents a grapical figure.
 * @author Ali Al-Musawi
 */
public class GraphicalFigure implements GraphicalFigureADT {
    private int id;  // an identifier for this figure
    private int width;  // the width of the enclosing rectangle of this figure
    private int height;  // the height of the enclosing rectangle of this figure
    private String type;  // the type of this figure, one of: ["fixed", "user", "computer", "target"].
    private Location pos;  // the offset of this figure
    private BinarySearchTree tree;  // the tree storing the pixels of this figure.

    /* Class Constructor
     * @param id the identifier for this figure.
     * @param width the width of the enclosing rectangle of this figure
     * @param height the height of the enclosing rectangle of this figure
     * @param type the type of this figure, one of: ["fixed", "user", "computer", "target"].
     * @param pos the offset of this figure
     * @return an instance of class GraphicalFigure
     */
    public GraphicalFigure(int id, int width, int height, String type, Location pos) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.type = type;
        this.pos = pos;
        this.tree = new BinarySearchTree();
    }

    /* sets the type of this figure.
     * @param type the type of this figure, one of: ["fixed", "user", "computer", "target"].
     */
    public void setType(String type) {
        this.type = type;
    }

    /* Accessor Method
     * @return the width of the enclosing rectangle of this figure.
     */
    public int getWidth() {
        return this.width;
    }

    /* Accessor Method
     * @return the height of the enclosing rectangle of this figure.
     */
    public int getHeight() {
        return this.height;
    }

    /* Accessor Method
     * @return the type of this figure.
     */
    public String getType() {
        return this.type;
    }

    /* Accessor Method
     * @return the identifier of this figure.
     */
    public int getId() {
        return this.id;
    }

    /* Accessor Method
     * @return the offset of this figure.
     */
    public Location getOffset() {
        return this.pos;
    }

    /* sets the offset of this figure.
     * @param value the new offset.
     */
    public void setOffset(Location value) {
        this.pos = value;
    }

    /* adds a pixel to the pixel tree of this figure.
     * @param pix the new pixel to insert.
     * @throws DuplicatedKeyException if the pixel already exists in the pixel tree.
     */
    public void addPixel(Pixel pix) throws DuplicatedKeyException {
        this.tree.put(this.tree.getRoot(), pix);
    }

    /* checks if this figure intersects with the given figure.
     * this algorithm verifies the intersection of the enclosing rectangles.
     * @param gobj the other graphical figure to check intersection with.
     * @return true if the figures intersect, and false otherwise.
     */
    public boolean intersects(GraphicalFigure gobj) {
        int leftA = this.pos.xCoord(), leftB = gobj.getOffset().xCoord();
        int rightA = leftA + this.width, rightB = leftB + gobj.getWidth();
        int topA = this.pos.yCoord(), topB = gobj.getOffset().yCoord();
        int bottomA = topA + this.height, bottomB = topB + gobj.getHeight();
        return (rightA >= leftB && leftA <= rightB && topA <= bottomB && bottomA >= topB);
    }
}
