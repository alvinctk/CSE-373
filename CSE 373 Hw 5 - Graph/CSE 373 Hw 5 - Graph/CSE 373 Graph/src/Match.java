/**
 * Background: Related to the problem of recognizing objects in images.  
 * Both the image and the object are represented as graphs, where a 
 * node corresponds to a region of pixels and the edges correspond 
 * to adjacencies between regions.
 * 
 * Description of Match: Find all subgraph isomorphisms from the 
 * model graph GM to the image graph GD.
 * 
 * CSE 373 Homework 5: SubGraph Isomorphism 
 * TA: Gene Kim 
 * Professor: Linda Shapiro
 * 
 * @author Alvin Chia
 * @version 3.0 SubGraph Isomorphism - Wednesday, May 29, 2013.
 * 
 */
import java.io.*;


public class Match {

	private static int adjMatrixGM[][];
	private static int adjMatrixGD[][];
	
	/**
	 * Pre conditon: args.length must be exactly equal 2. 
	 * 				 args[0] must be filename of Graph Model GM 
	 * 				 args[1] must be filename of Graph Data GD
	 * 
	 * @param args (String array) - program argument to run the program.
	 * 		  Contains the filename to query for subgraph isomorphism. 
	 * 
	 * @return If args.length == 2, then prints all instances of subgraph isomorphism if any;
	 * 		   Otherwise, no output.
	 * 		   If args.length != 2, then prints information to user to how to use the program.				
	 */
	public static void main (String [] args){
		
		if (args.length != 2){
			usage();
		}else {
			matchSubGraph(args[0], args[1]);
		}
	}
	
	/**
	 * Description of usage: Helper method for main. Helps to inform the user
	 * if the main program fails to run. 
	 * 
	 * @param none
	 * @version Based on previous programming assignment. 
	 *          Credit to UW CSE 373. Edited to be usable in this homework. 
	 * @return prints appropriate information to user if argument is less than 2
	 */
	private static void usage(){
		System.out.println("Usage: java Match <command [query GM file] [query GD file]> ");
		System.out.println("Commands:");
		System.out.printf("\n\t[query GM file] - text file that contains graph object" );
		System.out.printf("\n\t[query GD file] - text file that contains graph image" );
		System.exit(1);
	}
	
	/**
	 * Description of matchSubGraph: Search for all possible instances of 
	 * subgraph isomorphism, that is, find all copies of GM in GD, where 
	 * all mapping has zero error. <br>
	 * 
	 * @param GM_filename (String) - directory of the filename for the Graph Model GM
	 * @param GD_filename (String) - directory of the filename for the Graph Data GD
	 *
	 * @return prints all instances of subgraph isomorphism if any;
	 * 		   Otherwise, no output. 
	 */
	
	private static  void matchSubGraph (String GM_filename, String GD_filename){
		GraphData GM = getGraphData(GM_filename);
		GraphData GD = getGraphData(GD_filename);
		
		adjMatrixGM = GM.adjMatrix;
		adjMatrixGD = GD.adjMatrix;
		
		VertexSet VM = new VertexSet (GM.numNodes);
		VertexSet VD = new VertexSet (GD.numNodes);

		searchIsomorphic(VM, VD);	
	}
	
	/**
	 * Description of getGraphData: Helper method for matchSubGraph. 
	 * Process the graph.txt and store all necessary information into 
	 * the GraphData object. 
	 * 
	 * @param filename (String) - filename of graph text file 
	 * @return a new GraphData Object with all information in text file
	 */
	private static GraphData getGraphData (String filename){
		
		String read;
		String node [];
		GraphData myGraph = null; 
		BufferedReader b = null;
		
		try{
			b = new BufferedReader (new FileReader(filename));
			b.readLine(); // Name of graph is not used. 
			
			int numNodes = Integer.parseInt(b.readLine());
			int numEdges = Integer.parseInt(b.readLine());
			
			myGraph = new GraphData(numNodes, numEdges);

			// Adjacency Matrix information 
			while((read = b.readLine())!= null && 
				   (read != "") && (read != " ")){
				
				node = read.split(" ");
				myGraph.addEdge(Integer.parseInt(node[0]), 
						        Integer.parseInt(node[1]));
			}
			
			if (b != null) b.close();
		}catch (IOException e){
			e.printStackTrace();
		}

		return myGraph;
	}
	
	/**
	 * Description of searchIsomorphic: Helper method for matchSubGraph
	 * Search for all possible instances of subgraph isomorphism, that is, 
	 * find all copies of GM in GD, where all mapping has zero error. <br>
	 * 
	 * Note: This searchIsomorphic call the recursive searchIsomorphic <br>
	 * 
	 * Pre condtion: Adjacency Matrix must be setup up.
	 * 
	 * @param VM (VertexSet) - Vertex set of undirected Graph Model(GM)
	 * @param VD (VertexSet) - Vertex set of undirected Graph Data (GD)
	 * 
	 * @return prints all instances of subgraph isomorphism if any;
	 * 		   Otherwise, no output.  
	 */
	private static void searchIsomorphic(VertexSet VM, VertexSet VD){
	
		for (int i = 0; i < VD.size; i++){
				
			IsomorphicSet hCopy = new IsomorphicSet(VM.size);
			hCopy.addMapping(VM.getVertice(0), VD.getVertice(i));
				
			VertexSet VMCopy = new VertexSet(VM, 0);
			VertexSet VDCopy = new VertexSet(VD, i);
			
			searchIsomorphic(VMCopy, VDCopy, hCopy);	
		}
	}  
	
	/**
	 * Description of searchIsomorphic: Recursive backtracking tree search
	 * to find all copies of GM in GD. In other words, find all mapping with 
	 * zero error. <br>
	 * 
	 * Pre condtion: Adjacency Matrix must be setup up.
	 *               VertexSet VM, VD and IsomorphicSet h must be copies of 
	 *               the original or similar, or otherwise no guarantee that 
	 *               the original object might be modified. 
	 * 
	 * @param VM (VertexSet) - Vertex set of undirected Graph Model(GM)
	 * @param VD (VertexSet) - Vertex set of undirected Graph Data (GD)
	 * @param h (Isomorphic Set) - Isomorphic set to hold all the isomorphism mapping 
	 * 							   between vertices from VM and VD 
	 * 
	 * @return prints all instances of subgraph isomorphism if any;
	 * 		   Otherwise, no output.  
	 */
	private static void searchIsomorphic(VertexSet VM, VertexSet VD, IsomorphicSet h){
		
		for (int j = 0; j < VD.size && VM.size != 0; j++) {

			// Create a copy of Isomorphic Set	
			IsomorphicSet hCopy = new IsomorphicSet(h);
			hCopy.addMapping(VM.getVertice(0), VD.getVertice(j));

			if (isMappingOk(hCopy)) {

				// create a copy Vertex Set without (vm, vd)
				// that has been added to Isomoprhic Set
				VertexSet VMCopy = new VertexSet(VM, 0);
				VertexSet VDCopy = new VertexSet(VD, j);

				if (hCopy.isComplete()) { // base case
					hCopy.printIsomorphism();

				} else { // recursive case
					searchIsomorphic(VMCopy, VDCopy, hCopy);
				}
			}
		}
	}
	
	/**
	 * Description of isMappingOk: Helper method for recursive searchIsomorphic method 
	 * to determine if the isomorphic mapping is valid. <br>
	 * 
	 * Pre condition: Vertices from VM and VD need to be added into IsomorphicSet h 
	 * 
	 * @param h (IsomorphicSet) - Isomorphic set to hold all the isomorphism mapping 
	 * 							   between vertices from VM and VD 
	 * @return true if there exists such a undirected subgraph isomorphic mapping between
	 *         subgraph GD and subgraph GM; Otherwise, false. 
	 */
	private static boolean isMappingOk(IsomorphicSet h){
		int curPosition = h.curIndexPtr-1;
		int xCurVM = h.getMapValue(0, curPosition);
		int xCurVD = h.getMapValue(1, curPosition);

		for (int j = 0; j < (h.curIndexPtr); j++) {
			if (curPosition!= j) {
				int xTempVM = h.getMapValue(0, j);
				int xTempVD = h.getMapValue(1, j);
				if (adjMatrixGM[xCurVM][xTempVM] != 0 &&
					adjMatrixGM[xCurVM][xTempVM] != adjMatrixGD[xCurVD][xTempVD]) {

					h.removeMapping();
					return false;
				}
			}
		}
		
		return true;
	}

}

/**
 * Outer class within Match.class <br>
 * 
 * Description of GraphData: A data structure to contain 
 * all the necessary information of an undirected graph. 
 * 
 * @author Alvin Chia
 * @version 3.0 SubGraph Isomorphism - Wednesday, May 29, 2013. 
 *
 */
class GraphData {
	public int numNodes; 
	public int numEdges; 
	public int adjMatrix [][];
	public int vertices[];
	
	/**
	 * Constructor: Creates an GraphData Object
	 * @param numNodes (Integer) - the number nodes in an undirected graph
	 * @param numEdges (Integer) - the number edges in an undirected graph 
	 */
	public GraphData(int numNodes, int numEdges){
		this.numNodes = numNodes; 
		this.numEdges = numEdges; 
		
		adjMatrix = new int [numNodes][numNodes];
		
		for(int i =0; i < numNodes; i++){
			for(int j =0; j < numNodes; j++){
				adjMatrix[i][j] = 0;
			}
		}
	}
	
	/**
	 * Description of addEdge: Update the adjacency matrix 
	 * @param nodeA (Integer) - node that is connected by this edge
	 * @param nodeB (Integer) - node that is connected by this edge
	 * @return updates the GraphData's adjacency matrix with this edge
	 */
	public void addEdge(int nodeA, int nodeB){
		adjMatrix[nodeA-1][nodeB-1] = 1;
		adjMatrix[nodeB-1][nodeA-1] = 1;
	}
}











