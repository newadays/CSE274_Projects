/**
 * 
 */
package data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/**
 * @author karroje
 *
 */
public class IntHash<value_type> extends Dictionary<Integer, value_type> {
	int a;
	int b;
	int m;
	Pair<Integer,value_type> del;
	
	ArrayList<Pair<Integer,value_type>> table;
	
	/**
	 * Hashing function
	 * @param key
	 * @return hash value
	 */
	private int hash(Integer key) {
		return (((int)key*a) + b) % m;
	}
	
	/**
	 * Default constructor
	 */
	public IntHash() {
		this(7, 1, 25014);
	}
	
	
	/**
	 * Constructor -- hash values specified.
	 */
	public IntHash(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<Pair<Integer,value_type>>(Collections.nCopies(m, null));
		del = new Pair<Integer,value_type>(-1,null);
	}

	/**
	 * Insert a value/key pair into the dictionary.  Do not allow duplicate
	 * or null values.
	 * @param key        key to be inserted
	 * @param value      value to be inserted
	 * @exception   Throw IndexOutOfBoundsException if key already present.
	 * @exception   Throw IllegalArgumentException if value is null.
	 * @exception   Throw IllegalArgumentException if key < 0.  (Makes life easier.
	 */
	@Override
	public void insert(Integer key, value_type value) {
		//System.out.println(table.toString());
		if (value == null)
			throw new IllegalArgumentException("Null values not allowed");
		else if (n == m)
			throw new ArrayIndexOutOfBoundsException("Duplicate values not allowed");
		else if (key < 0)
			throw new IllegalArgumentException("Negative keys not allowed");
		
		int i = hash(key);
		if(table.get(i) != null || table.get(i) == del) {
			numOps++;
			
			while(table.get(i) != null || table.get(i) == del) {
				numOps++;
				//i++;
				if(table.get(i).first.equals(key)) {
					numOps++;
					throw new IndexOutOfBoundsException("Duplicate keys not allowed");
				}
				numOps++;
				i++;
				//System.out.println(this.size());
				if(i>=m){
					i=0;
				}
				if(i == hash(key)) {
					throw new IllegalArgumentException("Table is full");
				}
				
			}
			//table.remove(i);
			table.set(i, new Pair<Integer,value_type>(key,value));
			numOps++;
			n++;
		}
		else {
			//table.remove(i);
			table.set(i, new Pair<Integer,value_type>(key,value));
			numOps++;
			n++;
		}
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 * (((int)key*a) + b) % m
	 */
	@Override
	public void remove(Integer key) {
		
		int i = hash(key);
		
		while(table.get(i) != null) {
			numOps++;
			if(table.get(i).first.equals(key)) {
				numOps+=2;
				table.set(i,del);
				n--;
				break;
			}
			numOps++;
			i++;
			if(i>=m) {
				i=0;
			}
			if(i == hash(key))
				break;
		}
		
		
		
		
//		while(table.get(i) != null) {
//			numOps++;
//			if(table.get(i).first.equals(key)) {
//				numOps++;
//				table.set(i,null);
//				numOps++;
//				n--;
//				while(table.get(i+1) != null) {
//					numOps++;
//					Pair<Integer,value_type> n = table.get(i);
//					if(i==0) {
//						table.set(m-1, n);
//						numOps++;
//					} else {
//						table.set(i-1, n);
//						numOps++;
//						i++;
//						if(i>=m) {
//							i=0;
//						}
//					}
//				}
//				if(i >= m) {
//					i = 0;
//				}
//			}
//			else{
//				numOps++;
//			i++;
//			if(i>=m){
//				i=0;
//			}
//			}
//		}
//		i-=2;
//		if(i<0){
//			i+=m;
//		}
//		
//		table.set(i, null);
//		numOps++;
		
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		table = new ArrayList<Pair<Integer,value_type>>(Collections.nCopies(m, null));
		n = 0;
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public value_type find(Integer key) {
		
		int i = hash(key);
		while(table.get(i) != null  || table.get(i) != del) {
			numOps++;
			if(table.get(i) == null) {
				return null;
			}
			else if(table.get(i).first.equals(key)) {
				numOps+=2;
				return table.get(i).second;
			}
			i++;
			if(i >= m) {
				i=0;
			}
			if(i == hash(key)) {
				break;
			}
		}
		return null;
		
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#check_structure()
	 * This is not useful for this class -- we will just always pass it.
	 */
	@Override
	public boolean check_structure() {
		return true;
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		for (int i=0; i < m; i++) {
			Pair<Integer,value_type> p = table.get(i);
			if (p != null && p.first >= 0)
				System.out.println("k, h(k), v = " + p.first + " " + i + " " + p.second);
		}
	}

}
