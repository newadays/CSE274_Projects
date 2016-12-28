import java.util.ArrayList;
import java.util.Collections;

/**
 * @author karroje
 *
 */
public class ListGraph extends Graph {
	
	private ArrayList<ArrayList<Pair<Integer, Double>>> graph;
	
	public ListGraph(int n) {
		// TODO
		super(n);
		graph = new ArrayList<ArrayList<Pair<Integer, Double>>>(Collections.nCopies(
				n, null)); 
	}


	/* (non-Javadoc)
	 * @see Graph#weight(int, int)
	 */
	
	/**
	 * Return weight of edge between node i to node j, or null if it doesn't exist..
	 * @param i
	 * @param j
	 * @return double
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	@Override
	Double getWeight(int i, int j) {
		// TODO
		ArrayList<Pair<Integer, Double>> tmp = graph.get(i);
		if(tmp == null) {
			return null;
		}
		for(Pair<Integer, Double> p: tmp) {
			if(p != null && p.first.equals((Integer) j)) {
				return p.second;
			}
		}
		return null;
		
	}



	/* (non-Javadoc)
	 * @see Graph#addEdge(int, int)
	 */
	
	/** 
	 * Set weight of edge between from node i to node j.
	 * @param i
	 * @param j
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	@Override
	void setWeight(int i, int j, Double weight) {
		// TODO
		if(graph.get(i) == null) {
			graph.set(i, new ArrayList<Pair<Integer, Double>>(0));
		}
		ArrayList<Pair<Integer, Double>> tmp = graph.get(i);
//		for(Pair<Integer, Double> p : tmp) {
//			if(p.first.equals((Integer)j)){
//				throw new IndexOutOfBoundsException("That object already exists");
//			}
//		}
		tmp.add(new Pair<Integer, Double>(j,weight));
//		if(graph.get(j) == null) {
//			graph.set(j, new ArrayList<Pair<Integer, Double>>());
//		}
//		tmp = graph.get(j);
//		for(Pair<Integer, Double> p : tmp) {
//			if(p.first.equals((Integer) i))	{
//				throw new IndexOutOfBoundsException("That object already exists");
//			}
//		}
//		tmp.add(new Pair<Integer, Double>(i,weight));
	}
		
	/* (non-javadoc)
	 * @see Graph#AdjacentNodes
	 */
	
	/**
	 * Return a list of all node adjacent to i.
	 * @param i
	 * @ return
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	ArrayList<Pair<Integer,Double>> adjacentNodes(int i) {
		// TODO
		ArrayList<Pair<Integer, Double>> nodes = graph.get(i);
		//ArrayList<Pair<Integer, Double>> nodes = new ArrayList<Pair<Integer, Double>>();
		
		
		
		return nodes;
	}
	 
	/* (non-javadoc)
	 * @see Graph#degree
	 */
	
	/**
	 * Return the degree of node i (the number of edge going "out" of i).
	 * @return
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	int degree(int i) {
		// TODO
		ArrayList<Pair<Integer, Double>> nodes = graph.get(i);
		if(nodes == null) {
			return 0;
		}

		return nodes.size();
	}
	public Graph clone() {
		ListGraph nu = new ListGraph(graph.size());
		for(int i = 0; i<graph.size();i++) {
			if(graph.get(i) != null) {
				for(int j =0; j<graph.get(i).size();j++) {
					nu.setWeight(i, graph.get(i).get(j).first, graph.get(i).get(j).second);
				}
			}
		}
		return nu;
		
//		for(int i = 0; i<graph.size(); i++) {
//			if(graph.get(i) != null) {
//				nu.set(i, new ArrayList<Pair<Integer, Double>>());
//			}
//			if(nu.get(i) != null) {
//				for(int j = 0; j < graph.get(i).size(); j++	) {
//					nu.get(i).add(graph.get(i).get(j));
//				}
//			}
//		}
		
		
		
	}
}
