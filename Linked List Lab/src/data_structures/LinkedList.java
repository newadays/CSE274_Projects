/**
 * 
 */
package data_structures;

/**
 * @author karroje
 *
 */

/**
 * The ListNode<value_type> is a helper class for your 
 * LinkedList<value_type> class.  As its not intended for use
 * outside the LinkeList class, we are keeping it simple -- the
 * two properties will be access directly, instead of going through
 * inspectors and mutators.
 * 
 * DO NOT MODIFY THIST CLASS.
 * 
 * @param <value_type>  The type of object to be stored in the list.
 */
class ListNode<value_type> {
	public value_type value;
	public ListNode<value_type> next, prev;
		
	public ListNode(value_type v) {
		value = v;
		next = null;
		prev = null;
	}
	
	public ListNode(value_type v, ListNode<value_type> n, ListNode<value_type> p) {
		value = v;
		next = n;
		prev = p;
	}
	
}


/*
 * We will implement this as a single linked list.
 */
public class LinkedList<value_type> extends Sequence<value_type> {
	
	/**
	 * head will be the first node of the list -- or null if the list is empty
	 */
	private ListNode<value_type> head;  
	                      
	private ListNode<value_type> tail;
	/**
	 * List constructor: must call the superclass constructor.
	 */
	public LinkedList() {
		super();
		// TO DO: Finish constructor. 
		head = null;
		tail = head;
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#get(int)
	 */
	@Override
	public value_type get(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		// TO DO: Finish method
		ListNode<value_type> s = head;
		for(int j=0;j<i;j++) {
			numOps++;
			s = s.next;
		}
		return s.value;   
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#set(int, java.lang.String)
	 */
	@Override
	public value_type set(int i, value_type value) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		// TO DO: Finish method.
		ListNode<value_type> chk = head;
		for(int j=0;j<i;j++) {
			numOps++;
			chk = chk.next;
		}
		value_type val = chk.value;
		chk.value = value;
		numOps+=2;
		return val;
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#add(int, java.lang.String)
	 */
	@Override
	public void add(int i, value_type value) {
		if (i < 0 || i > size())
			throw new IndexOutOfBoundsException();
		// TO DO: Finish method
		ListNode<value_type> nu = new ListNode<value_type>(value);
		if(i == 0) {
			if(size() == 0) {
				head = nu;
				tail = head;
			}
			else {
				numOps++;
				nu.next = head;
				nu.prev = head;
				head = nu;
			}
			
		}
		else if(i == size()) {
			tail.next = nu;
			nu.prev = tail;
			tail = nu;
			numOps++;
		}
		else{
			ListNode<value_type> bef = head, indx = head.next;
			for(int j = 1;j<i;j++) {
				numOps++;
				bef = indx;
				indx = bef.next;
			}
				
				nu.next = bef.next;
				nu.prev = bef;
				bef.next = nu;
				nu.next.prev = nu;
				numOps+=2;

			
		}
		n++;
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#remove(int)
	 */
	@Override
	public value_type remove(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		// TO DO: Finish method
		if(size() == 0) {
			throw new IllegalArgumentException();
		}
		ListNode<value_type> bef = null;
		ListNode<value_type> indx = head;
		if(i == 0) {
			if(size() == 1) {
				tail = null;
				head = null;
			}
			else {
			numOps+=2;
			head = head.next;
			//head.prev = null;
			indx.next = indx;
			}
			
		}
		else {
			bef = indx;
			indx = indx.next;
			for(int j=1;j<i;j++) {
				numOps+=2;
				bef = indx;
				indx = indx.next;
			}
			if(indx.next == null) {
				bef.next = null;
				//tail.prev = null;
				tail = bef;
				numOps++;
			}
			else {
				bef.next = indx.next;
				indx.next = indx;
				numOps+=2;
			}

		}
		n--;
		return indx.value;
		
		//return null;   // SHOULD BE CHANGED
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#clear()
	 */
	@Override
	public void clear() {
		// TO DO: Finish method.
		head = null;
		n = 0;
		numOps+=2;
	}

	@Override
	public boolean in(value_type query) {
		// TODO Auto-generated method stub
		if(head == null) {
			return false;
		}
		ListNode<value_type> s = head;
		while(s != null) {
			if(query.equals(s.value)) {
				numOps++;
				return true;
			}
			s = s.next;
			numOps++;
		}
		return false;
	}

//	@Override
//	public void push_back(value_type value) {
//		if(size() == 0) {
//			head = new ListNode<value_type>(value);
//			tail = head;
//			n++;
//		}
//		ListNode<value_type> nu = new ListNode<value_type>(value);
//		
//	}
	


}
