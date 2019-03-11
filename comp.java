import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class comp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			BufferedReader tokens = new BufferedReader(new FileReader("result3.txt"));
			BufferedReader matches = new BufferedReader(new FileReader("result2.txt"));
			String token;
			String match;
			int right=1,wrong = 0;
			while((token=tokens.readLine())!=null){
				match=matches.readLine();
				String[] to = token.split("\\s+");
				String[] ma = match.split("\\s+");
				if((to.length==3)&&(ma.length==3)){
					
					if(to[2].equals(ma[2]))
						right=right+1;
					else
						wrong=wrong+1;
				}
				else
					wrong=wrong+1;
			}
			tokens.close();
			matches.close();
			System.out.println("right="+right);
			System.out.println("wrong="+wrong);
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
