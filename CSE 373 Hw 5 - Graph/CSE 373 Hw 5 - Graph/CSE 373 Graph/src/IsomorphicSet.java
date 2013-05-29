public class IsomorphicSet {
	private int mapping [][]; 
	public int curIndexPtr;
	private int size; 
	private static int numSets = 2;
	
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
		this.curIndexPtr = curIndexPtr; 
	
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
			for (int i = 0; i < curIndexPtr ; i++){
				System.out.print("(");
				System.out.print(mapping[0][i]+1);
				System.out.print(","); 
				System.out.print(mapping[1][i]+1);
				System.out.print(")");
				if (i != curIndexPtr-1){
					System.out.print(","); 
				}
			}
			System.out.print("}");


	}
	
	//ISOMORPHISM: {(1,2),(2,3),(3,4)}
	public void printTest () {

			System.out.print("\nTest: {");
			for (int i = 0; i < curIndexPtr ; i++){
				System.out.print("(");
				System.out.print(mapping[0][i] +1);
				System.out.print(","); 
				System.out.print(mapping[1][i] +1);
				System.out.print(")");
				if (i != curIndexPtr){
					System.out.print(","); 
				}
			}
			System.out.print("}\n");


	}
	
	
	public void addMapping(int VM, int VD){
		if (mapping[0][curIndexPtr] == -5 && mapping[1][curIndexPtr] == -5 ){
			mapping[0][curIndexPtr] = VM; 
			mapping[1][curIndexPtr] = VD; 
			if ((curIndexPtr + 1) <= size){
				curIndexPtr ++; 
			}
		}
	}
	// to be called after addMapping 
	public void removeMapping(){
		curIndexPtr--;
		mapping[0][curIndexPtr] = -5; 
		mapping[1][curIndexPtr] = -5;
		
	}
	public void addVXMapping(int VX, boolean type, boolean increment){
		if (type){
			mapping[0][curIndexPtr] = VX;
		}else{
			mapping[1][curIndexPtr] = VX; 
		}
		
		if (increment){
			curIndexPtr ++;
		}
	}
	
	public int getMapValue(int type, int index){
		return mapping[type][index];
	}
	public boolean isComplete(){
		//this.printIsomorphism();
		return ((curIndexPtr) == size); //&& 
				//mapping[0][curIndexPtr-1] != -5 &&
				//mapping[1][curIndexPtr-1] != -5)? true : false; 
	}
}
