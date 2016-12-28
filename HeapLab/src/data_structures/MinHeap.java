package data_structures;
import java.util.ArrayList;
import java.util.Collections;

public class MinHeap<E, P extends Comparable<P>> implements MinPriorityQueue<E, P> {
	
	// E is element, P is the priority
	private Object[] table;
	private int n,m,numOps;
	
	public MinHeap() {
		this(10);
	}
	public MinHeap(int m) {
		n = 0;
		numOps=0;
		table = new Object[10];
	}
	
	// For testing purposes ONLY
	public Integer[] getPriorityArray() {
		Integer[] tmp = new Integer[n];
		for(int i=0; i<n; i++) {
			Pair<E,P> pr = (Pair<E,P>)table[i];
			tmp[i] = (Integer)pr.second;
		}
		return tmp;
	}
	public void resize() {
		if(n == table.length-1) {
			Object[] tmp = new Object[2*n];
			for(int i=0; i<table.length;i++) {
				tmp[i] = table[i];
				numOps++;
			}
			table = tmp;
		}
	}
	/**
	 * Push an element e with priority p into the priority queue
	 * @param e  The element being put in the PriorityQueue.
	 * @param p  The priority of the e
	 */
	@Override
	public void push(E e, P v) {
		// TODO Auto-generated method stub
		resize();
		table[n] = new Pair<E,P>(e,v);
		
		if(n != 0) {
			boolean done = false;
			int parent = (n-1)/2;
			int indx = n;
			while(!done) {
				if(indx == 0) {
					done = true;
				}
				else if(((Pair<E,P>)(table[parent])).second.compareTo(((Pair<E,P>)(table[indx])).second) > 0) {
					Pair<E,P> tmp = (Pair<E,P>)table[parent];
					table[parent] = table[indx];
					table[indx] = tmp;
					numOps+=2;
				} else {
					done = true;
				}
				indx = parent;
				parent = (indx-1)/2;
			}
		}
		n++;
	}

	/**
	 * Remove the smallest element of the priority queue from the queue 
	 * and return the element.  Ties broken arbitrarily.
	 * @return E
	 * @exception ArrayIndexOutOfBoundsException  Thrown when called on an empty heap.
	 */
	@Override
	public E pop() {
		// TODO Auto-generated method stub
		if(n == 1) {
			n--;
			E e = ((Pair<E,P>)table[0]).first;
			table[0] = null;
			return e;
		} else {
			Pair<E,P> tmp = (Pair<E,P>)table[0];
			n--;
			table[0] = table[n];
			table[n] = null;
			boolean done = false;
			int indx = 0;
			while(!done) {
				if(table[2*indx + 1] != null && table[2*indx+2] != null) {
					if(((Pair<E,P>)table[2*indx+1]).second.compareTo(((Pair<E,P>)table[2*indx+2]).second) > 0){
						if(((Pair<E,P>)table[indx]).second.compareTo(((Pair<E,P>)table[2*indx+2]).second) > 0) {
							Object j = table[indx];
							table[indx] = table[2*indx+2];
							table[2*indx+2] = j;
							indx = 2*indx+2;
							numOps+=2;
						} else {
							done = true;
						}
						
					} else if(((Pair<E,P>)table[2*indx+1]).second.compareTo(((Pair<E,P>)table[2*indx+2]).second) < 0) {
						if(((Pair<E,P>)table[indx]).second.compareTo(((Pair<E,P>)table[2*indx+1]).second) > 0) {
							Object j = table[indx];
							table[indx] = table[2*indx+1];
							table[2*indx+1] = j;
							indx = 2*indx+1;
							numOps+=2;
						} else {
							done = true;
						}
					} else {
						done = true;
					}
					
				} else if(table[2*indx+1] != null) {
					if(((Pair<E,P>)table[indx]).second.compareTo(((Pair<E,P>)table[2*indx+1]).second) > 0) {
						Object j = table[indx];
						table[indx] = table[2*indx+1];
						table[2*indx+1] = j;
						indx = 2*indx+1;
						numOps+=2;
					} else {
						done = true;
					}
					
				} else if(table[2*indx+2] != null) {
					if(((Pair<E,P>)table[indx]).second.compareTo(((Pair<E,P>)table[2*indx+2]).second) > 0) {
						Object j = table[indx];
						table[indx] = table[2*indx+2];
						table[2*indx+2] = j;
						indx = 2*indx+2;
						numOps+=2;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			}
			
			
			return tmp.first;
		}
		
	}

	/**
	 * Return the smallest element of the priority queue (but to not
	 * remove it from the queue.
	 * @return E
	 */
	@Override
	public E peek() {
		// TODO Auto-generated method stub
		numOps++;
		return ((Pair<E,P>)table[0]).first;
		
	}

	/**
	 * Return the size of the queue.
	 * @return int
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}
	
	/**
	 * Test whether heap is empty
	 */
	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return (n == 0);
	}

	/**
	 * Clear structure
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		table = new Object[10];
		n=0;
	}

	/**
	 * Reset numOp count.
	 */
	@Override
	public void resetOps() {
		// TODO Auto-generated method stub
		numOps = 0;
	}

	/**
	 * Get number of basic operations
	 * @return Number of operations
	 */
	@Override
	public int numOps() {
		// TODO Auto-generated method stub
		return numOps;
	}
	// TODO: Implement 
	
}
