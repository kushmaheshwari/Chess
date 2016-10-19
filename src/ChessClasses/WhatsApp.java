package ChessClasses;
//
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class WhatsApp{
	
	
	
	public static void main(String[] args){
		ArrayList<String> a = new ArrayList<String>();
		a.add("aaa,2.5");
		a.add("aaa,2.9");
		a.add("aaa,2.1");
		a.add("aaa,1.2");
		a.add("aaa,4.5");
		a.add("abb,2.5");
		a.add("aba,2.5");
		a.add("aaa,1.0");
		a.add("aaa,3.8");
		
		Collections.sort(a, new StringComparator());
		
		for(String rat: a){
			System.out.println(rat);
		}
			
	
	}
	
	 static class StringComparator implements Comparator<String>{
	     public int compare(String a, String b)
	     {
	         String[] aInfo = a.split(",");
	         String[] bInfo = b.split(",");
	         
	         if(aInfo[1].compareTo(bInfo[1]) < 0){
	        	 return -1;
	         }else if(aInfo[1].compareTo(bInfo[1]) > 0){
	        	 return 1;
	         }else{
	        	 return bInfo[0].compareTo(aInfo[0]);
	        	 
	         }
	         
	     }
	 }

	
}




