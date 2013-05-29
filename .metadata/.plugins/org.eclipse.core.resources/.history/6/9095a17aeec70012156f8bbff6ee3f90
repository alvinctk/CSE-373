public class IsomorphicSet {
	int mapping [][]; 
	int curIndexPtr;
	int size; 
	static int numSets = 2;
	
	public IsomorphicSet (int size){
		this(size, 0, new int [numSets][size]);
		for (int i = 0; i < numSets; i++){
			for (int j =0; j < size; j++){
				mapping[i][j] = -5;
			}
		}
	}
	
	public IsomorphicSet (int size, int curIndexPtr, int [][] mapping){
		this.size = size; 
		this.mapping = mapping; 
		curIndexPtr = this.curIndexPtr; 
	}
	
	public IsomorphicSet (IsomorphicSet toBeCopy){
		this(toBeCopy.size, toBeCopy.curIndexPtr, new int [numSets][toBeCopy.size]);
		int copyMe [][] = toBeCopy.mapping;
		for (int i = 0; i < numSets; i++){
			for (int j =0; j < this.size; j++){
				mapping[i][j] = copyMe[i][j];
			}
		}
	} 

	//ISOMORPHISM: {(1,2),(2,3),(3,4)}
	public void printIsomorphism () {
		System.out.print("\nISOMORPHISM: {");
		for (int i = 0; i <= curIndexPtr ; i++){
			System.out.print("(");
			System.out.print(mapping[0][i]);
			System.out.print(","); 
			System.out.print(mapping[1][i]);
			System.out.print(")");
			if (i != curIndexPtr){
				System.out.print(","); 
			}
		}
		System.out.print("}");
	}
	
	public void addMapping(int VM, int VE, boolean increment){
		mapping[0][curIndexPtr] = VM; 
		mapping[1][curIndexPtr] = VE; 
		if (increment){
			curIndexPtr ++; 
		}
	}
}
