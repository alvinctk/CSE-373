import java.io.*; 
public class TestMatch {
	public static void main (String [] args){
		
		if (args.length % 2 == 0){
			try {
				String strA = ""; 
				String strB = ""; 
				BufferedReader a = null; 
				BufferedReader b = null; 
				for (int i = 0; i < args.length; i += 2){
					a = new BufferedReader (new FileReader (args[i]));
					b = new BufferedReader (new FileReader (args[i+1]));

					System.out.print("\n Test of " + args[i] + " and " +args[i+1]);
					while (	(strA = a.readLine()) != null &&
							(strB = b.readLine()) != null
							&& strA != "" && strA != " " 
							&& strB != "" && strB != " "){
						
						if (strA != strB){
							System.out.print("\nNot the same");
							System.out.print("\nString A: " + strA);
							System.out.print("\nString B: " + strB);
						}
					}
					
					if (strA != strB){
						System.out.print("\nComparison ended earlier; One of file has more than other");
						System.out.print("\nString A: " + strA);
						System.out.print("\nString B: " + strB);
					}else{
						System.out.print("\n Good job! Same :)");
					}
				}
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		

		
	}
}
