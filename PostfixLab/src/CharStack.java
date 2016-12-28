import java.lang.IllegalStateException;

public class CharStack {
	private char[] A;
	private int size;
	
	/**
	 * Constructor
	 * @param max_size     Maximum size of the queue   
	 */
	public CharStack(int maxSize) {
		// Add code here
		A = new char[maxSize];
		size = 0;
	}
	
	/**
	 * Push a value onto the top of a queue.
	 * @param value   Value to be pushed
	 * @exception  IllegalStateException   Thrown if the stack is full
	 */
	public void push(char value) {
		// Add code here
		if(size == A.length) throw new IllegalStateException();
		size += 1;
		A[size-1] = value;
		
	}
	
	/**
	 * Pop a value from the stack: return the value, remove from stack.
	 * @return Top values of stacke
	 * @exception IllegalStateException   Thrown if you pop from an empty stack.
	 */
	public char pop() {
		// Add code here
		if(size == 0) throw new IllegalStateException();
		size -= 1;
		return A[size];
		
	}
	
	/**
	 * Return the top value on the stack without removing it.
	 * @return Topf value of stacke.
	 * @exception IllegalStateException()  Thrown if if applied to an empty stack.
	 */
	public char peek() {
		// Add code here
		if(size == 0) throw new IllegalStateException();
		return A[size-1];
	}
	
	/**
	 * Determine if the stack is empty.
	 * @return True if empty.
	 */
	public boolean isEmpty() {
		// Add code here}
		if(size == 0) return true;
		return false;
	}
	
	/**
	 * Determine if the stack is at capacity.
	 * @return True if at capacity.
	 */
	public boolean isFull() {
		// Add code here.
		if(size == A.length) return true;
		return false;
	}
	
	/**
	 * Number of elements in the stack.
	 * @return Number of elements in the stack.
	 */
	public int size() {
		// Add code here.
		return size;
	}
}
