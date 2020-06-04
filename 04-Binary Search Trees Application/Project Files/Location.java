/* This class represents the position (x, y) of a pixel.
 * @author Ali Al-Musawi
 */
public class Location {
    private int x; // this is the horizontal position component.
    private int y; // this is the vertical position component.

    /* Class Constructor.
     * @param x the horizontal position component.
     * @param y the vertical position component.
     * @return an instance of class Location.
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* Accessor Method.
     * @return the horizontal position component.
     */
    public int xCoord() {
        return x;
    }

    /* Accessor Method.
     * @return the vertical position component.
     */
    public int yCoord() {
        return y;
    }

    /* Compares two Location instances using column order.
     * @param p the other Location instance to compare against this instance.
     * @return 1 if this is greater, 0 if both are equal, else -1.
     */
    public int compareTo(Location p) {
        if (x != p.xCoord()) {
            if (x > p.xCoord()) return 1;
            else return -1;
        }
        else {
            if (y > p.yCoord()) return 1;
            else if (y == p.yCoord()) return 0;
            else return -1;
        }
    }
}
        