/* This class represents a Pixel, 
 * composed of a Location instance and a color.
 * Each Pixel is data item in a BS Tree node.
 * @author Ali Al-Musawi
 */
public class Pixel {
    private Location p; // this is the location of this Pixel.
    private int color; // this integer maps to a color.

    /* Class Constructor.
     * @param p the location of this Pixel.
     * @param color an integer that maps to this Pixel's color.
     * @return an instance of class Pixel.
     */
    public Pixel(Location p, int color) {
        this.p = p;
        this.color = color;
    }

    /* Accessor Method.
     * @return the location of this Pixel.
     */
    public Location getLocation() {
        return p;
    }

    /* Accessor Method.
     * @return the integer representing the color of this Pixel.
     */
    public int getColor() {
        return color;
    }
}    