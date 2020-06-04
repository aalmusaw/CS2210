/** 
 * This class represents a Matrix.
 * @author Ali Al-Musawi
 */

public class Matrix<T> {
    private int nrow; // the dimension of the row of this Matrix.
    private int ncol; // the dimension of the column of this Matrix.
    private T[][] matrix; // the matrix object consisting of nested arrays.

    /** 
     * Class Constructor
     * @param nrow the dimension of the row of this Matrix.
     * @param ncol // the dimension of the column of this Matrix.
     * @return a Matrix of size nrow*ncol
     */
    public Matrix(int nrow, int ncol) {
        this.nrow = nrow;
        this.ncol = ncol;
        matrix = (T[][]) (new Object[nrow][ncol]);
    }
    
    /** 
     * a method that verifies the 'square-ness' of this Matrix.
     * @return true if this Matrix is square, and false otherwise.
     */
    public boolean isSquare() {
        return (nrow == ncol);
    }

    /** 
     * a method that verifies symmetry of this Matrix.
     * @return true if this Matrix is symmetric, and false otherwise.
     */
    public boolean isSymmetric() {
        boolean symmetric = true;
        if (isSquare()) {
            for (int i = 0; i < nrow && symmetric; i++) {
                for (int j = 0; j <= i && symmetric; j++) {
                    symmetric = symmetric && (matrix[i][j] == matrix[nrow-1-i][ncol-1-j]);
                }
            }
            return symmetric;
        }
        return !symmetric;
    }

    /** 
     * a method that verifies if entry (i, j) is occupied.
     * @return true if the entry (i, j) is occupied.
     * @throws ArrayIndexOutOfBoundsException if either i+1 > nrow or j+1 > ncol.
     */
    public boolean isOccupied(int i, int j) {
        return (matrix[i][j] != null);
    }

    /** 
     * Accessor Method
     * @param axis 0 for "row", and 1 for "column".
     * @return the dimesnion of the specified axis.
     */
    public int getAxisDim(int axis) {
        switch (axis) {
            case 0: return nrow;
            case 1: return ncol;
            default: return 0;
        }
    }

    /** 
     * Accessor Method
     * @param i the ith row of this Matrix.
     * @param j the jth column on this Matrix.
     * @return a reference to the Object stored in entry (i, j).
     * @throws ArrayIndexOutOfBoundsException if either i+1 > nrow or j+1 > ncol
     */
    public T get(int i, int j) throws ArrayIndexOutOfBoundsException {
        return matrix[i][j];
    }

    /** 
     * Accessor Method
     * This method allows the user to retrieve the matrix entries...
     * lying on a specific axis (i.e. row, column, diagonal).
     * @param axis 0 for row, 1 for column, and othrwise for diagonal.
     * @param n the nth row, nth column, nth diagonal (square matrices only).
     * if axis is diagonal, n = 0 implies top left to bottom right,
     * and otherwise bottom left to top right. 
     * NOTE: diagnoal mode is compatible only with square matrices.
     * @return an array of T objects.
     * @throws ArrayIndexOutOfBoundsException if the row/column does not exist.
     */ 
    public Vector<T> getByAxis(int axis, int n) throws ArrayIndexOutOfBoundsException {
        switch(axis) {
            case 0:
                return (new Vector(matrix[n]));
            case 1:
                Vector<T> col_vector = new Vector(ncol);
                for (int i = 0; i < ncol; i++) col_vector.add(matrix[i][n]);
                return col_vector;
            default:
                Vector<T> diag_vector = new Vector(nrow);
                if (isSquare()) {
                    switch(n) {
                        case 0:
                            for (int k = 0; k < nrow; k++) diag_vector.add(matrix[k][k]);
                            break;
                        default:
                            for (int k = 0; k < nrow; k++) diag_vector.add(matrix[nrow-1-k][nrow-1-k]);
                    }
                }
                return diag_vector;
        }
    }

    /** 
     * Mutator Method
     * @param i the ith row of this Matrix.
     * @param j the jth column on this Matrix.
     * @param obj a pointer to an Object to store in entry (i, j).
     * @throws ArrayIndexOutOfBoundsException if either i+1 > nrow or j+1 > ncol
     */
    public void set(int i, int j, T obj) {
        matrix[i][j] = obj;
    }

    /** 
     * Mutator Method
     * This method fills out all the entries with NULL.
     */
    public void nullify() {
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol ; j++) matrix[i][j] = null;
        }
    }

    /** 
     * This method allows for neat printing of matrices.
     * @Override
     * @return a String representation of a Matrix.
     */
    public String toString() {
        String matrix = "";
        for (int i = 0; i < nrow; i++) {
            matrix = matrix + "\n|";
            for (int j = 0; j < ncol; j++) {
                matrix = matrix + this.matrix[i][j].toString() + "  ";
            }
            matrix = matrix.substring(0, matrix.length() - 2) + "|";
        }
        return matrix;
    }
}