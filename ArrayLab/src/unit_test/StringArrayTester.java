package unit_test;
import static org.junit.Assert.*;
import org.junit.Test;

import data_structures.StringArray;

public class StringArrayTester {
	
	/**
	 * Helper function -- not a test.
	 * @param n   Size of the string to be created
	 * @return    A string for testing.
	 */
	private StringArray create_array(int n) {
		StringArray S = new StringArray();  // Specified which StringArray structure is being used.
		for (int i=0; i < n; i++) {
			S.push_back("" + i);
		}
		
		return S;
	}
	
	/**
	 * Does the constructor create an (empty, non-null) StringArray.
	 */
	@Test
	public void constructor() {
		StringArray S = new StringArray();
		assertNotNull("S should not be null", S);
		assertTrue("S.size() should be 0", S.size()==0);	
	}
	
	/**
	 * Does StringArray.add(0) work on an empty list?
	 */
	@Test
	public void addToEmpty() {
		StringArray S = new StringArray();
		S.add(0, "0");
		assertTrue("S.size() should be 1", S.size()==1);
		assertEquals("S.get() bad output", S.get(0), "" + 0);
	}
	
	/**
	 * Does StringArray.push_back work?
	 */
	@Test
	public void push_back_test() {
		StringArray S = create_array(4);
		assertTrue("S.size() should be 10", S.size()==4);
		for (int i=0; i < S.size(); i++) {
			assertEquals("S.get(" + i + "): bad value", S.get(i), "" + i);
		}
	}
	
	/**
	 * Does StringArray.push_front work when adding to the beginning of a list?
	 */
	@Test
	public void push_front_test() {
		StringArray S = create_array(4);
		S.push_front("4");
		assertTrue("S.size() should be 5", S.size() == 5);
		assertEquals("S.get(0): bad value", S.get(0), "4");
		for (int i=1; i < S.size(); i++) {
			assertEquals("S.get(" + i + "): bad value", S.get(i), "" + (i-1));
		}
	}
	
	/**
	 * Does Sequene.add(i) work when adding to the middle of a list?
	 */
	@Test 
	public void add() {
		StringArray S = create_array(4);
		S.add(2, "4");
		assertTrue("S.size() should be 5", S.size() ==5);
		for (int i=0; i < 2; i++)
			assertEquals("S.get(" + i + "): bad value", S.get(i), "" + i);
		assertEquals("S.get(2): bad value", S.get(2), "" + (4));
		for (int i=3; i < 5; i++)
			assertEquals("S.get(" + i + "): bad value", S.get(i), "" + (i-1));
		
	}
	
	/**
	 * Does StringArray.set(i,v) work on each element?
	 */
	@Test
	public void setElement() {
		StringArray S = create_array(4);
		for (int i=0; i < S.size(); i++) {
			String t = "" + (i+10);
			String s = S.set(i, t);
			assertEquals("S.set(" + i + ",t): Return value incorrect", "" + i, s);
		}
		for (int i=0; i < S.size(); i++) {
			assertEquals("S.get(" + i + "): Not set correctly", S.get(i), "" + (i+10));
		}
	}
		
	/**
	 * Does pop_front?
	 */
	@Test
	public void removeFirstElement() {
		StringArray S = create_array(4);
		String v = S.pop_front();
		assertTrue("S.size() should be 3", S.size()==3);
		for (int i=0; i < S.size(); i++) {
			assertEquals("S.get incorrect after remove", S.get(i), "" + (i+1));
		}
		assertEquals("Return value wrong", v, "0");
	}

	/**
	 * Does pop_back work?
	 */
	@Test
	public void removeLastElement() {
		StringArray S = create_array(4);
		String v = S.pop_back();
		assertTrue("S.size() should be 3", S.size()==3);
		for (int i=0; i < S.size(); i++) {
			assertEquals("S.get incorrect after remove", S.get(i), "" + i);
		}
		assertEquals("Return value wrong", v, "3");
	}
	
	/**
	 * Does remove(i) work when 0 < i < S.size()-1?
	 */
	@Test
	public void removeMiddleElement() {
		StringArray S = create_array(4);
		String v = S.remove(2);
		assertTrue("S.size() should be 3", S.size()==3);
		for (int i=0; i < S.size(); i++) {
			assertEquals("S.get incorrect after remove", S.get(i), "" + (i < 2 ? i : (i+1)));
		}
		assertEquals("Return value wrong", v, "2");
	}
	
	/**
	 * Does in() work when the list contains the element?
	 */
	@Test
	public void in_positive() {
		StringArray S = create_array(4);
		assertTrue("in should return true", S.in("3"));
	}
	
	/**
	 * Does in() work when the list does not contain the element?
	 */
	@Test
	public void in_negative() {
		StringArray S = create_array(4);
		assertFalse("in should return false", S.in("10"));
	}
	
}
