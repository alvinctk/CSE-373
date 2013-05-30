/** Description of IsomorphicSet: The Isomorphic set is a set that contains
 *  valid Isomorphism mapping of Vertices from VertexSet model(VM) to VertexSet
 *  data (VD) for 1 particular undirected subgraph isomorphism from graph model 
 *  (GM) to the image graph data (GD). 
 *  <br> <br>
 *  Values used by the IsomorphicSet is in conjunction with VertexSet 
 *  @see VertexSet
 *  
 * CSE 373 Homework 5: SubGraph Isomorphism 
 * TA: Gene Kim 
 * Professor: Linda Shapiro
 *
 * @author Alvin Chia
 * @version 3.0 SubGraph Isomorphism - Wednesday, May 29, 2013. 
 */
public class IsomorphicSet {

	private static final int numSets = 2;
	private int mapping [][];
	private int size; 
	public int curIndexPtr;

	/**
	 * Constructor: Specifies the size of a IsomorphicSet to be created.
	 * @param size (Integer) - the size of the IsomorphicSet
	 */
	public IsomorphicSet (int size){
		this(size, 0, new int [numSets][size]);
		for (int i = 0; i < numSets; i++){
			for (int j =0; j < size; j++){
				mapping[i][j] = -5;
			}
		}
	}
	/**
	 * Internal Constructor: Create a IsomorphicSet Object
	 * 
	 * @param size (Integer) - the size of the IsomorphicSet
	 * @param curIndexPtr (Integer) - the pointer to index at which to add mapping
	 * @param mapping (2D Integer array) - the mapping of vertex from VM to VD
	 *  
	 * <br><br>VM - Vertex Set of Model undirected graph
	 * <br>VD - Vertex Set of Data undirected graph
	 */
	private IsomorphicSet (int size, int curIndexPtr, int [][] mapping){
		this.size = size; 
		this.mapping = mapping; 
		this.curIndexPtr = curIndexPtr; 
	}
	
	/**
	 * Constructor: Creates a cloned copy of the original IsomorphicSet   
	 * @param toBeCopy (IsomorphicSet object) - The original IsomorphicSet to be copy
	 */
	public IsomorphicSet (IsomorphicSet toBeCopy){
		this(toBeCopy.size, toBeCopy.curIndexPtr, new int [numSets][toBeCopy.size]);
		int copyMe [][] = toBeCopy.mapping;
		for (int i = 0; i < numSets; i++){
			for (int j =0; j < this.size; j++){
				mapping[i][j] = copyMe[i][j];
			}
		}
	} 
	
	/**
	 * Description of addMapping: add Vertex from Vertex set VM and VD
	 * <br>Note: Vertices are added into the Isomorphic Set regardless of  
	 * whether the vertices in question has a valid Isomorphic mapping or not. 
	 * 
	 * @param VM (Integer) - the vertex value from VertexSet VM.
	 * 						 VM is based from undirected model graph GM
	 * @param VD (Integer) - the vertex value from VertexSet VD
	 *                       VD is based from undirected data graph GD 
	 * @return void - successfully add mapping of Vertex VM to Vertex VD
	 */
	public void addMapping(int VM, int VD){
		if ((mapping[0][curIndexPtr] == -5) &&
			(mapping[1][curIndexPtr] == -5)){
			
			mapping[0][curIndexPtr] = VM; 
			mapping[1][curIndexPtr] = VD; 
			
			if ((curIndexPtr + 1) <= size){
				curIndexPtr ++; 
			}
		}
	}
	
	/**
	 * Description of removeMapping: Remove invalid vertices in IsomorphicSet.
	 * 
	 * <br>Pre condition: addMapping need to be call first, and check whether 
	 * the previous added vertices mapping is not valid before call this method.
	 * 
	 * @param none 
	 * @return void - remove the vertex VM and VD from the IsomorphicSet
	 *               based on the vertices that was added from the last call
	 *               addMapping(). 
	 * <br><br>VM - Vertex Set of Model undirected graph
	 * <br>VD - Vertex Set of Data undirected graph
	 */
	public void removeMapping(){
		curIndexPtr--;
		mapping[0][curIndexPtr] = -5; 
		mapping[1][curIndexPtr] = -5;
	}
	
	/**
	 * Description of getMapValue: Retrieve the vertex's value from 
	 * either VertexSet VM (M for model) or VD (D for data). 
	 * 
	 * @param type (Integer) - 0 = VM; 1 = VD
	 * <br>VM - Vertex Set of Model undirected graph
	 * <br>VD - Vertex Set of Data undirected graph
	 * 
	 * @param index (Integer) - location of VM/VD is stored in IsomorphicSet
	 * 
	 * @return value (Integer) of the VM/VD to be used as index to access
	 *         the Adjacency Matrix 
	 */
	public int getMapValue(int type, int index){
		return mapping[type][index];
	}
	
	
	/**
	 * Description of isComplete: Determine whether the IsomorphicSet has a 
	 * complete set of mapping of vertices from Graph Model GM and Graph Data.<br>
	 * 
	 * Pre Condtion: Need to determine whether IsomorphicSet has the valid mapping.  
	 * 
	 * @param none 
	 * 
	 * @return true if the search for 1 instance of subgraph isomorphism is complete; 
	 * 		   Otherwise, false.  
	 */
	public boolean isComplete(){
		return ((curIndexPtr == size) && 
				(mapping[0][curIndexPtr-1] != -5) &&
				(mapping[1][curIndexPtr-1] != -5)) ? true : false; 
	}
	
	/** Description of printIsomorphic: prints the Isomorphism after 
	 * the successful search of one instance of subgraph isomorphisms 
	 * from the model graph (GM) to the data or image graph (GD). <br>
	 * 
	 * Pre Condtion: Need to determine whether IsomorphicSet has all valid mapping, and 
	 *               need to determine whether IsomorphisSet is a completed set of 
	 *               mapping of vertices from Graph Model GM and Graph Data.  
	 *  
	 * @param none
	 * @return void - prints the Isomorphism result stored in IsomorphicSet.
	 * 				  The printed result is only one instance of Isomorphism.
	 *                The format that is printed is listed in class website. 
	 */
	public void printIsomorphism () {

		System.out.print("ISOMORPHISM: {");

		for (int i = 0; i < curIndexPtr; i++) {

			System.out.print("(");
			System.out.print(mapping[0][i] + 1); // VM
			System.out.print(",");
			System.out.print(mapping[1][i] + 1); // VD
			System.out.print(")");

			if (i != curIndexPtr - 1) { // don't print last
				System.out.print(",");
			}

		}
		System.out.print("}\n");
	}
}
