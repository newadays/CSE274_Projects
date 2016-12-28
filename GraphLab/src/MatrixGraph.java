import java.util.ArrayList;


/**
 * @author karroje
 *
 */
public class MatrixGraph extends Graph {
	
	private Double[] graph;
	private int width;
	
	public MatrixGraph(int n) {
		// TODO
		super(n);
		graph = new Double[n*n];
		width = n;
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
		return graph[i*width + j];
	}

	/* (non-Javadoc)
	 * @see Graph#setWeight(int, int, java.lang.Double)
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
		graph[width*i+j] = weight;
		graph[width*j+i] = weight;
	}

	/* (non-Javadoc)
	 * @see Graph#adjacentNodes(int)
	 */
	
	/**
	 * Return a list of all node adjacent to i.
	 * @param i
	 * @ return
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	@Override
	ArrayList<Pair<Integer, Double>> adjacentNodes(int i) {
		// TODO
		ArrayList<Pair<Integer, Double>> nodes = new ArrayList<Pair<Integer, Double>>();
		Double test = graph[width*i];
		for(int j =0; j<width; j++) {
			if(graph[i*width+j] != null) {
				nodes.add(new Pair<Integer, Double>(j, graph[i*width+j]));
			}
		}
		return nodes;
		
	}

	/* (non-Javadoc)
	 * @see Graph#degree(int)
	 */
	
	/**
	 * Return the degree of node i (the number of edge going "out" of i).
	 * @return
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	@Override
	int degree(int i) {
		// TODO
		int cnt = 0;
		Double test = graph[width*i];
		for(int j = 0; j<width; j++) {
			if(graph[i*width+j] != null) {
				cnt++;
			}
		}
		return cnt;
	}

}
