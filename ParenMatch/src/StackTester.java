import static org.junit.Assert.*;

import org.junit.Test;

public class StackTester {

	/**
	 * If this test fails, your new stack is not registering as empty.
	 */
	@Test
	public void testEmpty() {
		IntStack Q = new IntStack(10);
		assertTrue(Q.isEmpty());
	}
	
	
	/**
	 * If this test fails, something is wrong with either push() or size().
	 */
	@Test
	public void testPushAndSize() {
		IntStack Q = new IntStack(10);
		for (int i=0; i < 5; i++)
			Q.push(i);
		assertTrue(Q.size() == 5);
	}
	
	/**
	 * If this test fails, something is wrong with either push() or peek().
	 */
	@Test
	public void testPushAndPeek1() {
		IntStack Q = new IntStack(10);
		for (int i=0; i < 5; i++)
			Q.push(i);
		assertTrue(Q.peek() == 4);

	}
	
	
	@Test
	public void testPushAndPeek2() {
		IntStack Q = new IntStack(10);
		for (int i=5; i < 10; i++)
			Q.push(i);
		Q.peek();
		assertTrue(Q.peek() == 9);
		assertTrue(Q.size() == 5);
	}
	
	@Test
	public void testPushAndPeek3() {
		IntStack Q = new IntStack(10);
		for (int i=100; i < 110; i+=2)
			Q.push(i);

		Q.peek();
		Q.push(42);;
		assertTrue(Q.peek() == 42);
	}
	
	@Test
	public void testPop1() {
		IntStack Q = new IntStack(10);
		for (int i=0; i < 10; i++)
			Q.push(i);
		Q.pop();
		assertTrue(Q.size() == 9);
	}
	
	@Test
	public void testPop2() {
		IntStack Q = new IntStack(10);
		for (int i=0; i < 12; i+=2) {
			Q.push(i);
		}
		for (int i=10; i >= 0; i-=2) {
			assertTrue(Q.pop() == i);
		}
	}
	
	@Test
	public void testPop3() {
		IntStack Q = new IntStack(10);
		for (int i=0; i < 10; i++)
			Q.push(i);
		for (int i=0; i < 10; i++)
			Q.pop();
		assertTrue(Q.isEmpty());
	}
	
	@Test
	public void testPushAndPop() {
		IntStack Q = new IntStack(20);
		for (int i=0; i < 10; i++) {
			Q.push(i);
			Q.push(i+100);
			Q.pop();
		}
		for (int i=0; i < 10; i++) {
			assertTrue(Q.pop() == 10-i-1);
		}
		assertTrue(Q.isEmpty());
	}
	
	@Test
	public void testFull1() {
		IntStack Q = new IntStack(100);
		for (int i=0; i < 100; i++)
			Q.push(i);
		assertTrue(Q.isFull());
	}
	
	@Test
	public void testFull2() {
		IntStack Q = new IntStack(100);
		for (int i=0; i < 99; i++) 
			Q.push(i);
		assertFalse(Q.isFull());
	}
	
	/**
	 * If this test fails, you are not throwing the correct exception
	 * when the user tries to peek into  an empty stack.
	 */
	@Test(expected = IllegalStateException.class)
	public void exception1() {
		IntStack Q = new IntStack(10);
		Q.peek();
	}

	/**
	 * If this test fails, you are not throwing the correct exception
	 * when the user tries to pop from an empty stack.
	 */
	@Test(expected = IllegalStateException.class)
	public void exception2() {
		IntStack Q = new IntStack(10);
		Q.pop();
	}
	
	/**
	 * If this test fails, you are not throwing the correct exception
	 * when the user tries to push into a full stack.
	 */
	@Test(expected = IllegalStateException.class)
	public void exception3() {
		IntStack Q = new IntStack(3);
		for (int i=0; i < 3; i++)
			Q.push(i);
		Q.push(1);
	}
}
