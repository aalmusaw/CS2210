/* This class implements the Dictonary ADT,
 * and uses a hashtable with separate chaining.
 * @author Ali Al-Musawi
 */
import java.util.LinkedList;
public class Dictionary implements DictionaryADT {
    private int size; // This is the size of the hashtable.
    private int numRecords; // This is the number of records stored in the dictionary.
    private LinkedList<Record>[] hashtable; // This is the table in which the Records are stored.

    /* Class Constructor
     * @param size the desired size of the dictionary.
     */
    @SuppressWarnings("unchecked")
    public Dictionary(int size) {
        this.size = size;
        numRecords = 0;
	hashtable = new LinkedList[size];
    }

    /* Mutator Method
     * This method takes a Record instance's configuration, 
     * and stores it in the hashtable using the helper method hashCode().
     * The prime number 33 is used in this particular function.
     * If a cell already has a configuration, the new one will be chained using a linked list.
     * @param pair a Record instance containing configuration and score.
     * @return 0 if no collision occurs, 1 otherwise.
     * @throws DictionaryException if a pair already exists in the dictionary.
     */
    public int insert(Record pair) throws DictionaryException {
	int position = hashCode(pair.getConfig(), 33);
	if (hashtable[position] == null) {  // ensure the hashtable cell is empty
	    hashtable[position] = new LinkedList<Record>();
	    hashtable[position].add(pair);
            numRecords++;
	    return 0;
	}
	else { // a collision is happening
	    if (retrieveByKey(position, pair.getConfig()) != -1) throw new DictionaryException();
	    else hashtable[position].add(pair);
            numRecords++;
	    return 1;
	}
    }

    /* Mutator Method
     * This method searches for the Record's configuration using two helper methods.
     * The prime 33 is supplied as an argument to the hashCode() method.
     * Once found, the configuration is deleted silently.
     * @param config the configuration to locate on the hashtable and remove.
     * @throws DictionaryException if the configuration cannot be found.
     */
    public void remove(String config) throws DictionaryException {
        int position = hashCode(config, 33);
        if (hashtable[position] != null && retrieveByKey(position, config) != -1) {
            int positionInList = retrieveByKey(position, config);
            hashtable[position].remove(positionInList);
            numRecords--;
            /* this ensures we do not count an empty list as a collision cause. */
            if (hashtable[position].size() == 0) hashtable[position] = null;
	}
	else throw new DictionaryException();
    }

    /* Accessor Method
     * This method retrieves the score associated with the given configuration.
     * Two helper methods are invoked here.
     * @param config is the key used in searching for the score stored in the Records.
     * @return the score associated with the configuration, or -1 if not found.
     */
     public int get(String config) {
         int position = hashCode(config, 33);
         int positionInList = retrieveByKey(position, config);
         if (positionInList != -1) {
             return hashtable[position].get(positionInList).getScore();
         }
         else return -1;
     } 
	
    /* Accessor Method
     * @return the number of Record instances in the Dictionary.
     */
    public int numElements() {
        return numRecords;
    }

    /* Helper Method
     * This is the hash function. It maps the configuration string of a Record instance
     * to a position on the hashtable using a polynomial:
     * p(x) = (int)'c_0' + (int)'c_1'*x + (int)'c_2'*x^2 + ... + (int)'c_n-1'*x^(n-1)
     * Where each 'c_i' is the ith character of the configuration, and x is a prime number.
     * To prevent overflow, we take modulo s (where s is the size of the table) at every term. 
     * @param keyConfig the configuration of the Record instance
     * @param prime the value of the prime number used in the polynomial.
     * @return a position on the hashtable
     */
    private int hashCode(String keyConfig, int prime) {
	    int n = keyConfig.length() - 1;
	    int value = ((int) keyConfig.charAt(n));
	    for(int i = n-1; i >= 0; i--) {
	        value = (value*prime + ((int) keyConfig.charAt(i))) % size; 
		}
	    return (value % size);
    }


    /* Helper Method
     * This method finds a Record instance in a chain of Records in a linked list.
     * @param position the position of the linked list in the hashtable.
     * @param keyConfig the key used to search for the sought Record.
     * @returns the index of the sought Record in the linked list, or -1 if not found.
     */
     private int retrieveByKey(int position, String keyConfig) {
    	 if (hashtable[position] != null) {
             for(int i = 0; i < hashtable[position].size(); i++) {
                 if (keyConfig.contentEquals(hashtable[position].get(i).getConfig())) return i;
             }
    	 }
         return -1;
      }
}	 