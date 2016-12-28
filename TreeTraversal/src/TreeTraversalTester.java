
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import data_structures.BST;


public class TreeTraversalTester {
	
	//public ArrayList<Integer> breadthF = new ArrayList<Integer>();
	
	
//	public int[] inOrder= {0,2,4,6,8,10,12,14,16,18};
//	public int[] postOrder = {0,2,6,8,4,12,14,18,16,10};
//	public int[] preOrder = {10,4,2,0,8,6,16,14,12,18};
//	public int[] breadthF = {10,4,16,2,8,14,18,0,6,12};
	
	private void create_helper(BST<Integer,Integer> D, int a, int b) {
		if (a == b-1) {
			D.insert(new Integer(2*a), new Integer(2*a+1));
		}
		else if (a < b-1) {
			int m = (a+b)/2;
			D.insert(new Integer(2*m), new Integer(2*m+1));
			create_helper(D, a, m);
			create_helper(D, m+1, b);
		}
	}
	
	private BST<Integer,Integer> create_BST(int n) {
		BST<Integer,Integer> D = new BST<Integer,Integer>();
		create_helper(D, 0, n);
		return D;
	}
	
	@Test
	public void testBFS() {
		BST<Integer,Integer> D = create_BST(10);
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(10,4,16,2,8,14,18,0,6,12));
		assertArrayEquals(list.toArray(), TreeTraversal.BFS(D.getRoot()).toArray());
	}
	
	@Test
	public void testPreOrder() {
		BST<Integer,Integer> D = create_BST(10);
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(10,4,2,0,8,6,16,14,12,18));
		assertArrayEquals(list.toArray(), TreeTraversal.preOrder(D.getRoot()).toArray());
	}
	
	@Test
	public void testPostOrder() {
		BST<Integer,Integer> D = create_BST(10);
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0,2,6,8,4,12,14,18,16,10));
		assertArrayEquals(list.toArray(), TreeTraversal.postOrder(D.getRoot()).toArray());
	}
	
	@Test
	public void testInOrder() {
		BST<Integer,Integer> D = create_BST(10);
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0,2,4,6,8,10,12,14,16,18));
		assertArrayEquals(list.toArray(), TreeTraversal.inOrder(D.getRoot()).toArray());
	}
}
