package xHashCode.program;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Program1 {

	public static void main(String [] args){
		Set<Collection<String>> s1 = new HashSet<Collection<String>>();
		Collection<String> c = new ArrayList<String>();
		
		c.add("this is ok");
		s1.add(c);
		c.add("don�t do this");
		System.out.println(s1.contains(c));
	}
}
