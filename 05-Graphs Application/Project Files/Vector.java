/** 
 * This class represents a Vector
 * @author Ali Al-Musawi
 */
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

public class Vector<T> implements Iterable<T> {
    private int size;
    private T[] vector;

    /** 
     * Class Constructor
     * @param size the size of this Vector
     * @return an instance of a Vector
     */
    public Vector(int size) {
        this.size = size;
        vector = (T[]) (new Object[size]);
    }

    /** 
     * Class Constructor
     * @param vector an array to turn to vector;
     * @return an instance of a Vector
     */
    public Vector(T[] vector) {
        size = vector.length;
        this.vector = vector;
    }


    /** 
     * Accessor Method
     * @return the size of this Vector
     */
    public int getSize() {
        return size;
    }

    /** 
     * Accessor Method
     * @param i the index of the entry desired.
     * @return the entry i.
     * @throws ArrayIndexOutOfBoundsException if i+1 > size or i < 0
     */
    public T get(int i) throws ArrayIndexOutOfBoundsException {
        return vector[i];
    }

    /** 
     * Mutator Method
     * @param i the index of the entry desired to be set.
     * @param obj the object to insert as entry i.
     * @throws ArrayIndexOutOfBoundsException if i+1 > size or i < 0
     */
    public void set(int i, T obj) throws ArrayIndexOutOfBoundsException {
        vector[i] = obj;
    }

    /** 
     * Mutator Method - does nothing if this Vector is full.
     * @param obj the object to insert in the next available slot.
     */
    public void add(T obj) {
        for (int i = 0; i < size; i++) {
            if (vector[i]==null) {
                vector[i] = obj;
                break;
            }
        }
    }

    /** 
     * This method creates a new Vector without null values.
     * @return a Vector holding the same entries as this without null.
     */
    public Vector<T> clean() {
        int count = 0;
        for (int i = 0; i < size; i++) if (vector[i]!=null) count++;     
        if (count != 0 && count != size) {
            Vector vector_new = new Vector(count);
            for (int i = 0; i < size; i++) if (vector[i]!=null) vector_new.add(vector[i]);
            return vector_new;
        }
        else if (count == size) return this;
        else return null;
    }

    /**
     *  This method converts this Vector to an Array
     * @return an array of type T
     */
    public T[] toArray() {
        return vector;
    }

    /** 
     * This method returns an Iterable
     * @return an Iterable for this Vector.
     */
    public Iterator<T> iterator() {
        Vector vector_new = this.clean();
        if (vector_new != null) {
            return new ArrayIterator(vector_new.toArray());
        }
        else return null; 
    }
}


     