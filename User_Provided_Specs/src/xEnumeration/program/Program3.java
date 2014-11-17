package xEnumeration.program;

import java.util.Enumeration;
import java.util.Vector;

public class Program3 {

	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>();
		Vector<Integer> v2 = new Vector<Integer>();
		v.add(1);
		v2.add(9);
		Enumeration<Integer> en = v.elements();
		Enumeration<Integer> en2 = v2.elements();
		
		v.add(8);
		v2.add(9);
		System.out.println(en.nextElement());
		System.out.println(en2.nextElement());

	}

}
