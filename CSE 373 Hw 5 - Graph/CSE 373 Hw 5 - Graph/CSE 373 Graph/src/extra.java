
public class extra {
	public VertexSet copyVertexSet(VertexSet toBeCopy, int indexToBeRemove){
		int size = toBeCopy.size - 1; 
		int copy [] = new int [size]; 
		int i = 0; 
		for (i =0; i < indexToBeRemove; i++){
			copy[i] = toBeCopy.vertices[i];
		}
		for (int j = i; j < size; j++){
			copy[j] = copy[j + 1];
		}
		return new VertexSet (size, copy);
	}
}
