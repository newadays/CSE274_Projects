/**
 * 
 */
package data_structures;
import java.lang.IndexOutOfBoundsException;

/**
 * @author karroje Tyler Davis
 *
 */
public class BST<K extends Comparable<K>, V> extends Dictionary<K, V> {
	protected TreeNode<K,V> root;
	private int h;
	boolean height2 = false;
	
	
	/**
	 * 
	 */
	public BST() {
		super();
		root = null;
		h = 0;
		n = 0;
	}
	
	/**
	 * Compare two keys and increment numOps.
	 * @param k1   First key
	 * @param k2   Second key
	 * @return     -1: k1 smaller; 0: elements equal; 1: k22 smaller
	 */
	private int compareKeys(K k1, K k2) {
		numOps++;
		return k1.compareTo(k2);
	}

	/**
	 * Get the tree's root node.
	 * @return
	 */
	public TreeNode<K,V> getRoot() {
		return root;
	}

	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#insert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) {
		// TODO
		TreeNode<K,V> tmp = new TreeNode<K,V>(key, value);
		if(n == 0) {
			root = tmp;
			n++;
			h++;
		} else {
			int cnt = 0;
			tmp = root;
			while(tmp != null) {
				if(key.equals(tmp.key)) {
					tmp = null;
					throw new IndexOutOfBoundsException();
				} else if(key.compareTo(tmp.key) < 0) {
					if(tmp.left == null) {
						tmp.left = new TreeNode<K,V>(key, value);
						n++;
						numOps++;
						if(cnt == h) {
							h++;
							height2 = false;
						} else if(cnt == h-1) {
							height2 = true;
						}
						tmp = null;
					} else {
						tmp = tmp.left;
						cnt++;
						numOps++;
					}
				} else {
					if(tmp.right == null) {
						tmp.right = new TreeNode<K,V>(key, value);
						n++;
						numOps++;
						if(cnt == h) {
							h++;
							height2 = false;
						} else if(cnt == h-1) {
							height2 = true;
						}
						tmp = null;
					} else {
						tmp = tmp.right;
						cnt++;
						numOps++;
					}
				}
					
				
			}
		}
	}

	
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(K key) {
		// TODO
		TreeNode<K,V> tmp = root;
		TreeNode<K,V> bef = root;
		boolean found = false;
		boolean right = false;
		int cnt = 0;
		if(n == 1) {
			root.left = null;
			root.right = null;
			root = null;
			n = 0;
			h = 0;
			numOps+=3;
		}
		else if(tmp != null) {
			while(tmp != null && !found) {
				if(key.equals(tmp.key)) {
					found = true;
				} else if(key.compareTo(tmp.key) < 0) {
					//System.out.println(tmp);
					bef = tmp;
					tmp = tmp.left;
					//System.out.println("less " + bef);
					right = false;
					numOps++;
				} else {
					//System.out.println(tmp);
					bef = tmp;
					tmp = tmp.right;
					//System.out.println("greater " + bef);
					right = true;
					numOps++;
				}
				cnt++;
			}
			//System.out.println("after " + bef);
			if(tmp.right == null && tmp.left == null) {
				
				if(right) {
					bef.right = null;
				} else {
					bef.left = null;
				}
				tmp = tmp.right;
				numOps+=2;
				n--;
				
			} else if(tmp.right != null && tmp.left == null) {
//				if(bef == null) {
//					System.out.println("bef");
//				} else if(tmp == null) {
//					System.out.println("tmp");
//				}
				if( tmp == root)	 {
					root = tmp.right;
				}
				else if(right) {
					bef.right = tmp.right;
				} else {
					bef.left = tmp.right;
				}
				numOps++;
				n--;
				

			} else if(tmp.left != null && tmp.right == null) {
//				if(bef == null) {
//					System.out.println("bef");
//				} else if(tmp == null) {
//					System.out.println("tmp");
//				}
				if(tmp == root) {
					root = tmp.left;
				}
				else if(right) {
					bef.right = tmp.left;
				} else {
					bef.left = tmp.left;
				}
				numOps++;
				n--;
				
			} else {
				
				TreeNode<K,V> tmp2 = tmp.left;
				TreeNode<K,V> bef2 = tmp;
				right = false;
				while(tmp2.right != null) {
					bef2 = tmp2;
					tmp2 = tmp2.right;
					cnt++;
					right = true;
					
					numOps++;
				}
				
				K d = tmp.key;
				V k = tmp.value;
				tmp.key = tmp2.key;
				tmp.value = tmp2.value;
				tmp2.value = k;
				tmp2.key = d;
				numOps+=4;
				
				if(right) {
					bef2.right = tmp2.left;
					numOps++;
				} else {
					bef2.left = tmp2.left;
					numOps++;
				}
				n--;
			}
			
//			if(cnt == h) {
//				h--;
//			}
			if(!height2) {
				h--;
			}
		} else {
			
		}
	}

//else if((tmp.right == null && tmp.left != null) || 
//(tmp.right != null && tmp.left == null)) {
//if(tmp.right != null) {
//tmp.key = tmp.right.key;
//tmp.value = tmp.right.value;
//tmp.right = null;
//} else {
//tmp.key = tmp.left.key;
//tmp.value = tmp.left.value;
//tmp.left = null;
//}
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public V find(K key) {
		// TODO
		if(n == 0) {
			return null;
		} else {
			TreeNode<K,V> tmp = root;
			while(tmp != null) {
				if(key.equals(tmp.key)) {
					return tmp.value;
				} else if (key.compareTo(tmp.key) < 0) {
					tmp = tmp.left;
					numOps++;
				} else {
					tmp = tmp.right;
					numOps++;
				}
			}
		}
		return null;
	}
	
	
	
	
	/**
	 * Return the smallest value in the tree.  (Return null if empty)
	 * @return key
	 */
	public K min() {
		// TODO
		if(n == 0){
			return null;
		}
		else {
			TreeNode<K,V> tmp = root;
			K val = tmp.key;
			while(tmp != null) {
				val = tmp.key;
				if(tmp.left == null) {
					return val;
				} else {
					tmp = tmp.left;
					numOps++;
				}
			}
		}
		return null;
	}
	
	/**
	 * Return the smallest value in the tree.  (Return null if empty)
	 * @return key
	 */
	public K max() {
		// TODO
		if(n == 0) {
			return null;
		} else {
			TreeNode<K,V> tmp = root;
			K val = tmp.key;
			numOps++;
			while(tmp != null) {
				val = tmp.key;
				numOps++;
				if(tmp.right == null) {
					return val;
				} else {
					tmp = tmp.right;
					numOps++;
				}
			}
		}
		return null;
	}
	
	
	
	/**
	 * Return the height of the tree.  
	 * Definition:
	 *    The *depth* of a node is number of edges from the root to that node.
	 *    The *height* of a tree is equal to the depth of the node with the
	 *        greatest depth of all the nodes.
	 * @return int
	 */
	public int height() {
		// TODO
		return h;
	}
	
	
	boolean isBSTHelper(TreeNode<K,V> root, K min_value, K max_value) {
		if (root == null)
			return true;

		if ((min_value != null && root.key.compareTo(min_value) <= 0) || (max_value != null && root.key.compareTo(max_value) >= 0))
			return false;
		
		return isBSTHelper(root.left, min_value, root.key) && isBSTHelper(root.right, root.key, max_value);
	}
	
	/**
	 * Check that the tree is a BST.
	 * @param root    Root of tree being checked.
	 * @return
	 */
	boolean isBST(TreeNode<K,V> root) {
		return isBSTHelper(root, null, null);
	}
	
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#check_structure()
	 */
	@Override
	public boolean check_structure() {
		return isBST(root);
	}

	
	void print_structure_helper(TreeNode<K,V> root, int indent) {			
		for (int i=0; i < indent; i++)
			System.out.print("\t");
		if (root == null) {
			System.out.println("LEAF");
			return;
		}
		System.out.println(root.key + ": " + root.value);
		print_structure_helper(root.left, indent+1);
		print_structure_helper(root.right, indent+1);
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		print_structure_helper(root, 0);
	}

	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		n = 0;
		h = 0;
		root = null;
	}

}
