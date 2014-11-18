package xEnumeration.program;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Memory used:
 * when monitor is called: 4200565 bytes in 1754 ms.
 * when monitor is not called: 2212039 bytes in 10 ms.
 * 
 * @author marcg_000
 *
 */
public class Program4 {

	public static void main(String[] args) {
		int n = 1000;
		Vector<Integer> v = new Vector<Integer>();
		
		for(int i = 0; i < n; i++){
			v.add(i);
		}
		
		for(int i = 0; i < n; i++){
			/* Bad execution as soon as the trace "* CREATE UPDATE NEXT" is generated */
			Enumeration<Integer> en = v.elements();
			v.add(i);
			en.nextElement();
		}

	}

}
