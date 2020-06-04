/**
 *  This class represents an Array Iterator
 * @author Ali Al-Musawi
 */
import java.util.Iterator;
public class ArrayIterator<T> implements Iterator {
   private int size;  // the number of elements in the collection
   private int current;  // the current position in the iteration 
   private T[] array; 

   /**
    * Class Constructor
    * @param array the array to iterate.
    */
   public ArrayIterator (T[] array) {
      this.array = array;
      size = array.length;
      current = 0;
   }

   /**
    * A method that verifies there is more elements in the array.
    * @return true if this iterator has one more element, and false otherwise.
    */
   public boolean hasNext() {
      return current < size;
   }

   /**
    * Accessor Method
    * @return the next available element in the iterator.
    */
   public T next() {
      if (!hasNext()) return null;
      current++;
      return array[current-1]; 
   }

   /**
    * Not Supported. Just here to satisfy the compiler.
    */
   public void remove() {
   }
}

