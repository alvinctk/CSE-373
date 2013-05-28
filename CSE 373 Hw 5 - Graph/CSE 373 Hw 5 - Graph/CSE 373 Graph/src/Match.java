
import java.io.*;


public class Match {

	private static int adjMatrixGM[][];
	private static int adjMatrixGD[][];
	
	public static void main (String [] args){
		
		if (args.length < 2){
			usage();
		}else {
			matchSubGraph(args[0], args[1]);
		}

	
		

		
	}
	
	private static  void matchSubGraph (String GM_filename, String GD_filename){
		GraphData GM = getGraphData(GM_filename);
		GraphData GD = getGraphData(GD_filename);
		
		adjMatrixGM = GM.adjMatrix;
		adjMatrixGD = GD.adjMatrix;
		
		VertexSet VM = new VertexSet (GM.numNodes);
		VertexSet VD = new VertexSet (GD.numNodes);
		
		IsomorphicSet hMap = new IsomorphicSet(GM.numNodes);
		
		printTestGraphData(GM);
		System.out.println();
		printTestGraphData(GD);
	}
	

	private static void searchIsomorphic(VertexSet VM, VertexSet VD, IsomorphicSet h){

	}
	

	 
	//####################!!!!!!!!!!!!!!!!!#################
	//####################!!!!!!!!!!!!!!!!!#################
	//####################!!!!!!!!!!!!!!!!!#################
	// you need not edit anything below this line
	 
	    private static void usage(){
	        System.out.println("Usage: java Match <command [query GM file] [query GD file]> ");
	        System.out.println("Commands:");
	        System.out.printf("\n\t[query GM file] - text file that contains graph object" );
	        System.out.printf("\n\t[query GD file] - text file that contains graph image" );
	        System.exit(1);
	    }

	
	


	
	private static void printTestGraphData(GraphData g){
		System.out.println("Graph Name: " + g.graphName);
		System.out.println("Number of nodes: " + g.numNodes);
		System.out.println("Number of eges: " + g.numEdges);
		for(int i =0; i < g.numNodes; i++){
			System.out.println();
			for(int j =0; j < g.numNodes; j++){
				System.out.print("\t" + g.adjMatrix[i][j]);
			}
		}
	}
	

	private static GraphData getGraphData (String filename){
		GraphData myGraph = null; 
		BufferedReader b = null; 
		try{
			b = new BufferedReader (new FileReader(filename));
			String graphName = b.readLine(); 
			int numNodes = Integer.parseInt(b.readLine());
			int numEdges = Integer.parseInt(b.readLine());
			myGraph = new GraphData(graphName, numNodes, numEdges);
			String read;
			String node [];
			int index = 0; 
			while((read = b.readLine())!= null && read != "" && read != " "){
				node = read.split(" ");
				myGraph.addEdge(Integer.parseInt(node[0]), Integer.parseInt(node[1]));
				index ++;
			}
			return myGraph;
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try {
                if (b!=null) b.close();
            } catch (IOException e){
                e.printStackTrace();
            }
			return myGraph;
		}
	}
}











