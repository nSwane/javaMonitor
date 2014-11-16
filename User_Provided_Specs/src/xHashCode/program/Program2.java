package xHashCode.program;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Program2 {

	public static void main(String[] args) {
		Set<Collection<String>> s1 = new HashSet<Collection<String>>();
		Set<Collection<String>> s2 = new HashSet<Collection<String>>();
		Collection<String> c1 = new ArrayList<String>();
		Collection<String> c2 = new ArrayList<String>();
		
		c1.add("this is ok");
		s1.add(c1);
		c1.add("don’t do this");
		
		c2.add("this is ok2");
		s2.add(c2);
		c2.add("don’t do this2");
	}

}
