package unit_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import data_structures.MinHeap;
import data_structures.MinPriorityQueue;
import data_structures.Pair;

import org.junit.Test;

public class MinHeapTester {

	public void createHeap(MinPriorityQueue<Integer, Integer> heap) {
		heap.push(new Integer(5), new Integer(5));
		heap.push(new Integer(4), new Integer(4));
		heap.push(new Integer(6), new Integer(6));
		heap.push(new Integer(3), new Integer(3));
		heap.push(new Integer(7), new Integer(7));
		heap.push(new Integer(2), new Integer(2));
		heap.push(new Integer(8), new Integer(8));
		heap.push(new Integer(1), new Integer(1));
		heap.push(new Integer(9), new Integer(9));
		heap.push(new Integer(0), new Integer(0));
	}
	public Integer[] getPriority(MinHeap<Integer, Integer> heap) {
		return heap.getPriorityArray();
	}
	//[0,1,3,4,2,6,8,5,9,7]
	@Test
	public void test01() {
		MinPriorityQueue<String,Integer> H = new MinHeap<>();
		assertEquals(H.size(), 0);
	}
	
	@Test
	public void testPush() {
		MinHeap<Integer, Integer> H = new MinHeap<>();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0,1,3,4,2,6,8,5,9,7));
		createHeap(H);
		assertArrayEquals(getPriority(H),list.toArray());
	}
	
	@Test
	public void testPeek() {
		MinHeap<Integer, Integer> H = new MinHeap<>();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0,1,3,4,2,6,8,5,9,7));
		createHeap(H);
		assertEquals(H.peek(), new Integer(0));
		assertArrayEquals(getPriority(H), list.toArray());
	}
	
	@Test
	public void testClear() {
		MinHeap<Integer, Integer> H = new MinHeap<>();
		createHeap(H);
		H.clear();
		assertEquals(H.empty(), true);
	}
	
	@Test 
	public void testPop() {
		MinHeap<Integer, Integer> H = new MinHeap<>();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4,7,6,8,5,9));
		createHeap(H);
		H.pop();
		assertArrayEquals(getPriority(H), list.toArray());
		
	}
}
