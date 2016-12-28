/**
 * 
 */

/**
 * @author karroje
 *
 */
public class MatrixRM extends Matrix {
	int [] A;
	/**
	 * Constructor.  Should initialize all values to 0.
	 * @param num_rows
	 * @param num_cols
	 */
	public MatrixRM(int num_rows, int num_cols) {
		super(num_rows, num_cols);
		A = new int[num_rows*num_cols];
	}

	/* (non-Javadoc)
	 * @see Matrix#get(int, int)
	 */
	// i = rows j = cols
	@Override
	public int get(int i, int j) {
		// TODO: Write code.  (Please remove this comment.)
		if(i > numRows()-1 || j > numCols()-1) throw new ArrayIndexOutOfBoundsException();
		else return A[j + i*(numCols())];
	}

	/* (non-Javadoc)
	 * @see Matrix#set(int, int, int)
	 */
	@Override
	public void set(int i, int j, int val) {
		// TODO: Write code.  (Please remove this comment.)
		if(i > numRows()-1 || j > numCols()-1) throw new ArrayIndexOutOfBoundsException();
		else A[j + i*(numCols())] = val;
	}
	
	/* (non-Javadoc)
	 * @see Matrix#find(int)
	 */
	// Returns [ row , col] of value if it is in the array
	public int[] find(int val) {
		// NOTE: DO this WITHOUT calling this.get() -- access A directly.
		// TODO: Write code.  (Please remove this comment.)
		
		
		for(int i=0; i<A.length;i++) {
			if(A[i] == val) {
				int[] ret = {i/numCols() , i%numCols()};
				return ret;
			}
		}
		return null;
	}

}
