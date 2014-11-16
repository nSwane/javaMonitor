package xEnumeration.program;

import java.util.Enumeration;
import java.util.Vector;

public class Program2 {

	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>();
		Vector<Integer> v2 = new Vector<Integer>();
		v.add(1);
		v.add(2);
		v.add(3);
		Enumeration<Integer> en = v.elements();
		
		while(en.hasMoreElements()) {
		Integer i = (Integer)en.nextElement();
			if (i == 2) {
				v2.add(4); /** this is ok **/
			}
			else {
				System.out.println(i);
			}
		}

	}

}
