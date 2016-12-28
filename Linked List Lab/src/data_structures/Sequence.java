package data_structures;

public abstract class Sequence<element_type> {
	
	/**
	 * n: Size of array
	 */
	protected int n;
	
	/**
	 * numOps: Number of "basic operations" since last resource reset
	 */
	int numOps;
	
	protected Sequence() {
		resetOps();
		n=0;
	}

	/**
	 * Return the number of element_types contained in the list.
	 * @return int
	 */
	public int size() {
		return n;
	}
	
	/**
	 * Get the element_type i.
	 * @param i Number of accessed element (from 0).
	 * @return element_type
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	abstract public element_type get(int i);

	/**
	 * Set the element_type at element i to value and
	 * return the old value.
	 * @param i Number of accessed element (from 0).
	 * @param value element_type value being put in the array
	 * @return The item that was at index i that was overwritten.
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	abstract public element_type set(int i, element_type value);
	
	/**
	 * Insert a new value at index i (bumping elements i - size() up one).
	 * @param i Number of inserted element
	 * @param value element_type valueb eing inserted
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	abstract public void add(int i, element_type value);
	
	/**
	 * Add an element to the back of the element_type.
	 * @param value element_type being added
	 */
	public void push_back(element_type value) {
		add(n, value);
	}
	
	/**
	 * Add an element to the front of the element_type.
	 * @param value element_type being added.
	 */
	public void push_front(element_type value) {
		add(0, value);
	}
	
	/**
	 * Remove element i from the array (renumbering everything past it)
	 * @param i  element being removed
	 * @return   value that was removed
	 * @throws IndexOutOfBoundsException if index out of bounds
	 */
	abstract public element_type remove(int i);
	
	/**
	 * Remove last element
	 * @return value of last element
	 */
	public element_type pop_back() {
		return remove(size()-1);
	}
	
	/**
	 * Remove first element
	 * @return value of first element
	 */
	public element_type pop_front() {
		return remove(0);
	}
	
	
	/**
	 * Search for a query element_type in the array.
	 * @param query element_type being seqrched for
	 * @return boolean
	 */
	public boolean in(element_type query) {
		for (int i=0; i < n; i++)
			if (query.equals(get(i)))
				return true;
		
		return false;
	}
	
	/**
	 * Remove all elements.
	 */
	abstract public void clear();
	
	/**
	 * Return True if the container is empty.
	 */
	public boolean isEmpty() {
		return n==0;
	}
	
	/**
	 * Amount of memory used since last resource reset.
	 * @return Number of operations
	 */
	public int numOps() {
		return numOps;
	}
	
	
	public void resetOps() {
		numOps = 0;
	}
}
