package xEnumeration.program;

import java.util.Enumeration;
import java.util.Vector;

public class Program4 {

	public static void main(String[] args) {
		int n = 100;
		Vector<Integer> v = new Vector<Integer>();
		
		for(int i = 0; i <n; i++){
			v.add(i);
		}
		
		int x = 0;
		Enumeration<Integer> en = v.elements();
		while(en.hasMoreElements()){
			en.nextElement();
			
			if(x < 100){
				v.add(100); /* Inject error */
				x++;
			}
		}

	}

}