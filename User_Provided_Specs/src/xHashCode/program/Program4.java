package xHashCode.program;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Memory used:
 * when monitor is called: 2699360 bytes
 * when monitor is not called: 2699400 bytes
 * 
 * @author marcg_000
 *
 */
public class Program4 {

	public static void main(String[] args) {
		Set<Collection<String>> s1 = new HashSet<Collection<String>>();
		Collection<String> c = new ArrayList<String>();
		int n = 1000;
		
		for(int i = 0; i < n; i++){
			c.add("this is ok");
			s1.add(c);
			c.add("don’t do this"); /* Inject errors */
		}

	}

}
