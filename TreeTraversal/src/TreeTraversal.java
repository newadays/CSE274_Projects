import java.util.ArrayList;
import data_structures.*;


public class TreeTraversal {
	
	public static ArrayList<Integer> order = new ArrayList<Integer>();
	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary search tree,
	 * return an ArrayList<Integer> holding the keys in order from a post-order traversal.
	 * RESTRICTION: Use recursion for this implementation.
	 * @param root
	 * @return Array list
	 */
	public static ArrayList<Integer> postOrder(TreeNode<Integer,Integer> root) {
		// TODO
		if(root == null) {
			return null;
		}else if(root.left == null && root.right == null) {
			order.add(root.key);
		} else {
			postOrder(root.left);
			postOrder(root.right);
			order.add(root.key);
		}
		return order;
	}
	

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a BFS traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Queue.  (Or
	 * use your Linked List class as a queue.)
	 * @param root
	 * @return Array List
	 */
	public static ArrayList<Integer> BFS(TreeNode<Integer, Integer> root) {
		// TODO
		order.clear();
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		if(root != null) {
			queue.push_back(root);
		} else {
			return order;
		}
		while(!queue.isEmpty()) {
			TreeNode tmp = queue.pop_front();
			if(tmp.left != null) {
				queue.push_back(tmp.left);
			}
			if(tmp.right != null) {
				queue.push_back(tmp.right);
			}
			order.add((Integer)tmp.key);
		}
		return order;
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a pre-order traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Stack.  (Or
	 * use your Linked List class as a stack.)
	 * @param root
	 * @return Array List
	 */
	public static ArrayList<Integer> preOrder(TreeNode<Integer, Integer> root) {
		// TODO
		order.clear();
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		if(root != null){
			stack.push_back(root);
		} else {
			return order;
		}
		while(!stack.isEmpty()) {
			TreeNode tmp = stack.pop_back();
			if(tmp.right != null) {
				stack.push_back(tmp.right);
			} 
			if(tmp.left != null) {
				stack.push_back(tmp.left);
			}
			order.add((Integer)tmp.key);
		}
		return order;
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from an in-order traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Stack.  (Or
	 * use your Linked List class as a stack.)
	 * @param root
	 * @return Array List
	 */
	public static ArrayList<Integer> inOrder(TreeNode<Integer, Integer> root) {
		// TODO
		order.clear();
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		if(root != null){
			stack.push_back(root);
		} else {
			return order;
		}
		while(!stack.isEmpty()) {
			TreeNode tmp = stack.pop_back();
			if(tmp.right == tmp && tmp.left == tmp) {
				order.add((Integer)tmp.key);
			} else if(tmp.right != null && tmp.left != null) {
				TreeNode cur = new TreeNode(tmp.key, tmp.value);
				cur.left = cur;
				cur.right = cur;
				stack.push_back(tmp.right);
				stack.push_back(cur);
				stack.push_back(tmp.left);
			} else if(tmp.right != null) {
				TreeNode cur = new TreeNode(tmp.key, tmp.value);
				cur.left = cur;
				cur.right = cur;
				stack.push_back(tmp.right);
				stack.push_back(cur);
				
			} else if(tmp.left != null) {
				TreeNode cur = new TreeNode(tmp.key, tmp.value);
				cur.left = cur;
				cur.right = cur;
				stack.push_back(cur);
				stack.push_back(tmp.left);
			} else {
				order.add((Integer)tmp.key);
			}
		}
		
		return order;
	}

}
