import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class tokens {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			
			BufferedReader tokens = new BufferedReader(new FileReader("labelled-tokens.txt"));
			String token;
			while((token=tokens.readLine())!=null){
				String best_str="";
				float best_val=0,val;
				String[] to = token.split("\\s+");
				BufferedReader dic = new BufferedReader(new FileReader("dict.txt"));
				String line;
				while((line=dic.readLine())!=null){
					val=getDistance(line,to[0]);
					if(val>best_val){
						best_val=val;
						best_str=line;
					}
				}
				dic.close();
				try{
					if(to[0].equals(best_str)){
						PrintWriter writer = new PrintWriter(new FileWriter("result3.txt", true));
						writer.print(to[0] + "  IV  " + best_str);
						writer.println();
						writer.close();
					}
					else{
						PrintWriter writer = new PrintWriter(new FileWriter("result3.txt", true));
						writer.print(to[0] + "  OOV  " + best_str);
						writer.println();
						writer.close();
					}
				}
				catch(FileNotFoundException e)
				{
					//If dictionary.txt doesn't exist,create a new one.
					
					File ass1 = new File("result3.txt");
					try {
						ass1.createNewFile();
						try{
							
							if(to[0].equals(best_str)){
								PrintWriter writer = new PrintWriter(new FileWriter("result3.txt", true));
								writer.print(to[0] + "  IV  " + best_str);
								writer.println();
								writer.close();
							}
							else{
								PrintWriter writer = new PrintWriter(new FileWriter("result3.txt", true));
								writer.print(to[0] + "  OOV  " + best_str);
								writer.println();
								writer.close();
							}
						} catch (IOException a) {
							
						}
					} catch (IOException a) {
						
					}
					
				
				} catch (IOException e) {
			// TODO Auto-generated catch block
					e.printStackTrace();
					}
				
			
			}
			tokens.close();
			System.out.println("Done");
		}catch (FileNotFoundException a) {
				// TODO Auto-generated catch block
				a.printStackTrace();
		}
		

	}
	public static float getDistance(String source, String target) {  
		   int sl = source.length();  
		   int tl = target.length();  
		     
		   if (sl == 0 || tl == 0) {  
		     if (sl == tl) {  
		       return 1;  
		     }  
		     else {  
		       return 0;  
		     }  
		   }  
		  
		   int n=3;
		   int cost = 0;  
		   if (sl < n || tl < n) {  
		     for (int i=0,ni=Math.min(sl,tl);i<ni;i++) {  
		       if (source.charAt(i) == target.charAt(i)) {  
		         cost++;  
		       }  
		     }  
		     return (float) cost/Math.max(sl, tl);  
		   }  
		  
		   char[] sa = new char[sl+n-1];  
		   float p[]; //'previous' cost array, horizontally  
		   float d[]; // cost array, horizontally  
		   float _d[]; //placeholder to assist in swapping p and d  
		     
		  
		   for (int i=0;i<sa.length;i++) {  
		     if (i < n-1) {  
		       sa[i]=0; //add prefix  
		     }  
		     else {  
		       sa[i] = source.charAt(i-n+1);  
		     }  
		   }  
		   p = new float[sl+1];   
		   d = new float[sl+1];   
		   
		   // indexes into strings s and t  
		   int i; // iterates through source  
		   int j; // iterates through target  
		  
		   char[] t_j = new char[n]; // jth n-gram of t  
		  
		   
		   for (i = 0; i<=sl; i++) {  
		       p[i] = i;  
		   }  
		  
		   for (j = 1; j<=tl; j++) { 
		       
		       if (j < n) { 
		         for (int ti=0;ti<n-j;ti++) {  
		           t_j[ti]=0; //add prefix  
		         }  
		         for (int ti=n-j;ti<n;ti++) {  
		           t_j[ti]=target.charAt(ti-(n-j));  
		         }  
		       }  
		       else { 
		         t_j = target.substring(j-n, j).toCharArray();  
		       }  
		       d[0] = j;  
		       for (i=1; i<=sl; i++) {  
		           cost = 0;  
		           int tn=n;  
		           
		           for (int ni=0;ni<n;ni++) {  
		             if (sa[i-1+ni] != t_j[ni]) {  
		               cost++;  
		             }  
		             else if (sa[i-1+ni] == 0) { //discount matches on prefix  
		               tn--;  
		             }  
		           }  
		           float ec = (float) cost/tn;  
		           // minimum of cell to the left+1, to the top+1, diagonally left and up +cost  
		           d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+ec);  
		       }  
		       // copy current distance counts to 'previous row' distance counts  
		       _d = p;  
		       p = d;  
		       d = _d;  
		   }  
		  
		   // our last action in the above loop was to switch d and p, so p now  
		   // actually has the most recent cost counts  
		   return 1.0f - ((float) p[sl] / Math.max(tl, sl));  
		 }  

}


