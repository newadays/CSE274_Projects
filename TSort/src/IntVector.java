
import java.lang.IndexOutOfBoundsException;
/**
 * StringArray: An implementation of a dynamic-sized string array.
 * 
 * @author TODO: Add your name here
 *
 */

public class IntVector extends Sequence {
	/**
	 * A: Contains the actual array.
	 */
	private Integer[] A;
	/**
	 * n: Size of array
	 */
	//int n;

	
	/**
	 * Basic constructor: Creates an "empty" list.
	 */
	public IntVector() {
		A = new Integer[1];
		n = 0;
	}

	/**
	 * Return the number of strings contained in the list.
	 * @return int
	 */
//	public int size() {
//		return n;
//	}
	
	/**
	 * Get the string i.
	 * @param i Number of accessed element (from 0).
	 * @return string
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	public Integer get(int i) {
		numOps++;
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		return A[i];
	}

	/**
	 * Set the string at element i to value.
	 * @param i Number of accessed element (from 0).
	 * @param value String value being put in the array
	 * @return string
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	public Integer set(int i, Integer value) {
		numOps+=2;
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		Integer y = A[i];
		A[i] = value;
		return y;
	}
	
	/**
	 * Insert a new value at index i (bumping elements i - size() up one).
	 * @param i Number of inserted element
	 * @param value String valueb eing inserted
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	public void add(int i, Integer value) {
		if (i < 0 || i > size())
			throw new IndexOutOfBoundsException();		

		resize();  // This will make sure A is large enough to hold the new element
		// TODO: Now you need to shift the elements, add the new element,
		// and change n.  (And ***please*** remove the TODO comments in your submission.)
		Integer h;
		n++;
		for(int j = i; j<A.length && j<n;j++) {
			numOps++;
			h = A[j];
			A[j] = value;
			value = h;
		}
	}
	
	/**
	 * Add an element to the back of the string.
	 * @param value String being added
	 */
//	public void push_back(String value) {
//		add(n, value);
//	}
	
	/**
	 * Add an element to the front of the string.
	 * @param value String being added.
	 */
//	public void push_front(String value) {
//		add(0, value);
//	}
	
	/**
	 * Remove element i from the array (renumbering everything past it)
	 * @param i  element being removed
	 * @return   value that was removed
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	public Integer remove(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();		
		Integer value = A[i];
		
		// TODO: Now you need to shift all the elements over and change n.
		// (Note: We are not resizing A.  Will just keep it the same size so
		// we have the space the next time we need it.)
		Integer val;
		n--;
		for(int j = i;j<A.length-1;j++) {
			numOps++;
			val = A[j+1];
			A[j] = val;
		}
		
		return value;
	}
	
	/**
	 * Remove last element
	 * @return value of last element
	 */
//	public String pop_back() {
//		return remove(size()-1);
//	}
	
	/**
	 * Remove first element
	 * @return value of first element
	 */
//	public String pop_front() {
//		return remove(0);
//	}
	
	/**
	 * If the array is full (that is: A.length == n), resize it so it can hold one more element.
	 */
	private void resize() {
		// TODO: If n < A.length, do nothing.
		// Otherwise: A needs to be resized.  This means changing the
		// A reference to a new array.  Notice that n will not change;
		// we are changing the size of A, but not the number of elements
		// being held in the container.
		if(A.length == 0) {
			Integer[] S = new Integer[A.length+1];
			A = S;
		}
		if(!(n<A.length-1)) {
			Integer[] S = new Integer[A.length*2];
			for(int i =0; i<A.length; i++) {
				numOps++;
				S[i] = A[i];
			}
			A = S;
		}
	}
	
	/**
	 * Search for a query string in the array.
	 * @param query String being seqrched for
	 * @return boolean
	 */
//	public boolean in(String query) {
//		// TODO: Write this method.  It should return true if any
//		// element in the sequence is equal to query.  (Remember:
//		// you need to use String.equals to compare, ot ==.  Make sure
//		// you know why.)
//		for(String s : A) {
//			if(s.equals(query)) return true;
//		}
//		return false;
//	}
	
	/**
	 * Remove all elements.
	 */
	public void clear() {
		A = new Integer[1];
		n = 0;
	}
	
	/**
	 * Return True if the container is empty.
	 */
//	public boolean isEmpty() {
//		return n==0;
//	}
}
