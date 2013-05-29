
public class VertexSet {
	private int vertices [];
	int size;
	
	public VertexSet(int size){
		this (size, new int[size]);
		for(int i = 0; i < size; i++){
		   vertices[i] = i;
		}
	}

	public VertexSet(int size, int [] vertices){
		this.size = size; 
		this.vertices = vertices;
	}

	public VertexSet(VertexSet toBeCopy, int indexToBeRemove){
		this(toBeCopy.size-1, new int[toBeCopy.size-1]);
		if (this.size != 0){
			int i = 0; 
			int copyMe [] = toBeCopy.vertices;
			for (i =0; i < indexToBeRemove; i++){
				vertices[i] = copyMe[i];
			}
			for (int j = i; j < this.size; j++){
				vertices[j] = copyMe[j + 1];
			}
		}
	}
	public int getVertice(int index){
		
		return (index < size)? vertices[index]: -5;
	}
	
	public boolean isEmpty(VertexSet VM){
		return VM.size == 1; 
	}

	
	
}
