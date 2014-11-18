package xHashCode.program;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Memory used:
 * when monitor is called: 1989792 bytes in 1202 ms.
 * when monitor is not called: 1989792 bytes in 20 ms.
 * 
 * @author marcg_000
 *
 */
public class Program4 {

	public static void main(String[] args) {
		int n = 1000;
		
		for(int i = 0; i < n; i++){
			Set<Collection<String>> s1 = new HashSet<Collection<String>>();
			Collection<String> c = new ArrayList<String>();
			c.add("this is ok");	/* Initialize with null */
			s1.add(c); 				/* Map the collection to the hashSet */
			c.add("don’t do this"); /* Inject errors */
		}

	}

}
