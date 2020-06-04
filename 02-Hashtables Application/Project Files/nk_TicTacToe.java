/* This class implements a set of methods used 
 * in the Play_nk_TTT class.
 * @author Ali Al-Musawi 
 * 
 */
public class nk_TicTacToe {
    private char[][] gameboard;
    private int inline;
    private int max_levels;
    private int board_size;
    
    /* Class Constructor
     * @param board_size the dimension of the game board (i.e. 3x3).
     * @param inline the number of symbols inline needed to win.
     * @param max_levels the maximum level of the game tree the program explores.
     */
    public nk_TicTacToe(int board_size, int inline, int max_levels) {
	this.inline = inline;
	this.max_levels = max_levels;
        this.board_size = board_size;
	gameboard = new char[board_size][board_size];
	for(int i = 0; i < board_size; i++) {
	    for(int j = 0; j < board_size; j++) {
		gameboard[i][j] = ' ';
	    }
	}
    }

    /* This method creates a dictionary to store sequential gameboards.
     * A conveneient size of 7000 has been chosen.
     * @return an empty Dictionary instance.
     */
    public Dictionary createDictionary() {
	return (new Dictionary(7000));
    }

    /* This method checks if the current gameboard's configuration exists within the supplied 
     * Dictionary, and returns a score associated with the configuration.
     * @param configurations the Dictionary instance in which to search for the configuration.
     * @return the score of the gameboard's configuration if it is in the dictionary,
     *    or -1 if the configuration does not exist in the dictionary.
     */
    public int repeatedConfig(Dictionary configurations) {
	String config = configurationMaker();
	int score = configurations.get(config);
        /* configurations.get() returns -1 if it cannot find the config provided. */
	return score;
    }

    /* This method silently inserts a Record into the provided Dictionary.
     * @param configurations the Dictionary in which the Record is inserted.
     * @param score the score attributed to the current gameboard's configuration.
     */
    public void insertConfig(Dictionary configurations, int score) {
	String config = configurationMaker();
	Record pair = new Record(config, score);
	configurations.insert(pair);
    }

    /* This method updates the gameboard by inserting the given symbol
     * into the gameboard matrix.
     * @param row the row number in which the symbol is placed.
     * @param col the column number in which the symbol is placed.
     * @param symbol the symbol to place in (row, col). Either 'X' or 'O'.
     */
    public void storePlay(int row, int col, char symbol) {
	gameboard[row][col] = symbol;
    }

    /* This method checks if a grid position is empty.
     * @param row the row number to check for availability.
     * @param col the column number to check for availability.
     * @return true if the position (row, col) is available, false otherwise.
     */
    public boolean squareIsEmpty(int row, int col) {
	return (gameboard[row][col] == ' ');
    }

    /* This method determines whether 'X' or 'O' is a winner.
     * @param symbol is either 'X' or 'O'.
     * @returns true if the player ('X' or 'O') is a winner, false otherwise.
     */
    public boolean wins(char symbol) {
    String winConfig = new String(new char[inline]).replace("\0", Character.toString(symbol));
	boolean winner = false;
	/* Check for a row winner */
	for (int i = 0; !winner && i < board_size; i++) {
	    winner = (new String(gameboard[i])).contains(winConfig);
	}
	if (winner) return true;

	/* Check for a column winner */
	for (int i = 0; !winner && i < board_size; i++) {
	    String config = "";
	    for (int j = 0; j < board_size; j++) {
		config += gameboard[j][i];
	    }
	    winner = config.contains(winConfig);
	    if (winner) return true;
	}

	/* Check for a diagonal winner */
	if (diagonalWinner(symbol)) return true;
	
        return winner;  // Always false
	    
    }

    /* This method determines if the current game outcome is a draw.
     * A draw is defined by two conditions: no spot is available AND no winner.
     * @return true if the outcome of the current game is a draw, false otherwise.
     */
    public boolean isDraw() {
        boolean spotAvailable = false;
        for (int i = 0; !spotAvailable && i < board_size; i++) {
            for (int j = 0; !spotAvailable && j < board_size; j++) {
                if(gameboard[i][j] == ' ') spotAvailable = true;
            }
        }
        return (!(spotAvailable || wins('X') || wins('O')));
    }

    /* This method decides on a score for the current game board.
     * By convention, human player is X, and computer is O.
     * score 3 implies the computer has won.
     * score 2 implies the game is a draw.
     * score 1 implies the game is undecided yet.
     * score 0 implies the human player has won.
     * @return a score that obeys the above scheme.
     */
    public int evalBoard() {
        int score = 1;
        if (wins('O')) score = 3;
        else if (wins('X')) score = 0;
        else if (isDraw()) score = 2;
        return score;
    }

    /* Helper Method
     * This method makes a String representation of the current gameboard.
     * @return a String that represents the configuration of the gameboard.
     */
    private String configurationMaker() {
        String config = "";
        for (int i = 0; i < board_size; i++) {
	    for (int j = 0; j < board_size; j++) {
	        config = config + gameboard[i][j];
	    }
        }
        return config;
    }

    /* Helper Method
     * This method checks for a diagonal winner. 
     * There are 4 directions to check:
     * Upper Left-To-Right and Lower Left-To-Right,
     * Upper Right-To-Left and Lower Right-To-Left.
     * @param symbol either 'X' or 'O'.
     * @return true if a diagoanl winner is found, false otherwise.
     */ 
    private boolean diagonalWinner(char symbol) {
        boolean winner = false;
	String winConfig = new String(new char[inline]).replace("\0", Character.toString(symbol));

        /* Checking Upper Left-To-Right diagonals */
        for(int i = 0; !winner && i < board_size; i++) {
            String config = "";
            for(int j = 0; j <= i; j++) {
                int col = j, row = i - j;
                config += gameboard[row][col];
            }
            winner = config.contains(winConfig);
            if (winner) return true;
         }

        /* Checking Lower Left-To-Right diagonals */
        for(int i = 1; !winner && i < board_size; i++) {
            String config = "";
            for(int j = i; j < board_size; j++) {
                int col = j, row = board_size - 1 + i - j;
                config += gameboard[row][col];
            }
            winner = config.contains(winConfig);
            if (winner) return true;
         }

        /* Checking Upper Right-To-Left diagonals */
        for(int i = 0; !winner && i < board_size; i++) {
            String config = "";
            for(int j = i; j < board_size; j++) {
                int col = j, row = j - i;
                config += gameboard[row][col];
            }
            winner = config.contains(winConfig);
            if (winner) return true;
         }

        /* Checking Lower Right-To-Left diagonals */
        for(int i = 1; !winner && i < board_size; i++) {
            String config = "";
            for(int j = 0; j < board_size - i; j++) {
                int col = j, row = j + i;
                config += gameboard[row][col];
            }
            winner = config.contains(winConfig);
            if (winner) return true;
         }

        return winner;  // Always false
    }

}

