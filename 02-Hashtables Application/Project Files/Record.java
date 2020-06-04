/* This class represents an entry in the dictionary class
 * @author Ali Al-Musawi
 */

public class Record {
    private String config; 
    private int score;
    
    /* Class Constructor
     * @param config a String that represents the configuration of the game board.
     * @param score an integer that specifies the outcome of the game.
     * Refer to assignment specifications for more info on each score.
     */
    public Record(String config, int score) {
	this.config = config;
	this.score = score;
    }

    /* Accessor Method
     * @return the configuration of the gameboard scored in this record.
     */
    public String getConfig() {
	return config;
    }

    /* Accessor Method
     * @return the score of the gameboard stored in this record.
     */
    public int getScore() {
	return score;
    }

}

    
