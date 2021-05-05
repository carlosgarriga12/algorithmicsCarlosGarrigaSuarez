package algstudent.s7.util;

import java.util.ArrayList;

/**
 * Main class to solve problems using the Branch and Bound technique
 * We need to extend it for any specific problem
 */
public abstract class BranchAndBound {
	protected Heap ds; //Nodes to be explored (not used nodes)
	protected Node bestNode; //To save the final node of the best solution
	protected Node rootNode; //Initial node
	protected int pruneLimit; //To prune nodes above this value
	private int processedNodes;
	private int trimmedNodes;
	private int generatedNodes;
	      
	public int getProcessedNodes() {
		return processedNodes;
	}

	public int getTrimmedNodes() {
		return trimmedNodes;
	}

	public int getGeneratedNodes() {
		return generatedNodes;
	}

	/**
	 * Constructor for BrancAndBount objects
	 */
	public BranchAndBound() {
		ds = new Heap(); //We create an instance of the Heap class to save the nodes
	}
	      
	/**
	 * Manages all the process, from the beginning to the end
	 * @param rootNode Starting state of the problem
	 */
	public void branchAndBound(Node rootNode) { 
		ds.insert(rootNode); //First node to be explored
	
		pruneLimit = rootNode.initialValuePruneLimit();

		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();	
			
			ArrayList<Node> children = node.expand(); 
			generatedNodes += children.size();
			for (Node child : children)
				if (child.isSolution()) {
					int cost = child.getHeuristicValue();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
					}
				}
				else
					if (child.getHeuristicValue() < pruneLimit) {
						ds.insert(child);
						processedNodes++;
					} else {
						trimmedNodes++;
					}
		} //while
	}
	/**
	 * Gets the root node
	 * @return The root node
	 */
    public Node getRootNode() {
    	return rootNode;
    }
    
	/**
	 * Gets the best node
	 * @return The best node
	 */
    public Node getBestNode() {
    	return bestNode;
    }

    /**
     * Prints the solution from the root node to the best node
     */
    public void printSolutionTrace() {
    	if (bestNode == null) {
			System.out.println("Original:");
			System.out.println(rootNode.toString());
			System.out.println("THERE IS NO SOLUTION");
    	} 
    	else {
    		//Extract the path of the used nodes from bestNode to the rootNode
            ArrayList<Node> result = ds.extractUsedNodesFrom(bestNode);
            for (int i = 0; i < result.size();  i++) {
    			if (i == 0) 
    				System.out.println("Original:");
    			else 
    				System.out.println("Step " + i + ":");
    			System.out.println(result.get(result.size()-i-1).toString());
    	    }
            System.out.println("\nSolution with " + bestNode.getDepth() + " step(s).");
    	}
    	
    	System.out.println("\nGenerated Nodes: " + getGeneratedNodes());
    	System.out.println("\nProcessed Nodes: " + getProcessedNodes());
    	System.out.println("\nTrimmed Nodes: " + getTrimmedNodes());
    }
}
