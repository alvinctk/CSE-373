import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


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
	
    private static void usage(){
        System.out.println("Usage: java Match <command [query GM file] [query GD file]> ");
        System.out.println("Commands:");
        System.out.printf("\n\t[query GM file] - text file that contains graph object" );
        System.out.printf("\n\t[query GD file] - text file that contains graph image" );
        System.exit(1);
    }

	private static GraphData getGraphData (String filename){
		String read;
		String node [];
		GraphData myGraph = null; 
		BufferedReader b = null;
		
		try{
			b = new BufferedReader (new FileReader(filename));
			String graphName = b.readLine(); 
			int numNodes = Integer.parseInt(b.readLine());
			int numEdges = Integer.parseInt(b.readLine());
			myGraph = new GraphData(graphName, numNodes, numEdges);
	
			while((read = b.readLine())!= null && read != "" && read != " "){
				node = read.split(" ");
				myGraph.addEdge(Integer.parseInt(node[0]), Integer.parseInt(node[1]));
			}
			
			if (b != null) b.close();
			
		}catch (IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		return myGraph;
	}

}
class GraphData {
	String graphName; 
	int numNodes; 
	int numEdges; 
	int adjMatrix [][];
	int vertices[];

	public GraphData(String graphName, int numNodes, int numEdges){
		this.graphName = graphName; 
		this.numNodes = numNodes; 
		this.numEdges = numEdges; 
		adjMatrix = new int [numNodes][numNodes];
		// initalize all values of adj matrix 
		for(int i =0; i < numNodes; i++){
			for(int j =0; j < numNodes; j++){
				adjMatrix[i][j] = 0;
			}
		}
	}
	
	public void addEdge(int nodeA, int nodeB){
		adjMatrix[nodeA-1][nodeB-1] = 1;
		adjMatrix[nodeB-1][nodeA-1] = 1;
	}
}
