package xHashCode.program;

import java.util.Collection;
import java.util.Set;

import xHashCode.monitor.Event;
import xHashCode.monitor.Verdict;
import xHashCode.monitor.VerificationMonitor;


public aspect Aspect {

	// Get runtime to retreive memory used
	private Runtime r = Runtime.getRuntime();
	
	// Initialize memory used
	private Long memoryUsed = new Long(0);
	
	// Enbale or not monitor calls
	private static boolean enabled = true;
	
	VerificationMonitor monitor = new VerificationMonitor();
	
	/**
	 * Objects passed in parameter must match the event.
	 * If event is modify, objects must be [<instance of Collection>].
	 * If event is add, objects must be [<instance of HashSet>, <instance of Collection>].
	 * 
	 */
	public Verdict dispatchEvent(Event e, Object... objects){
		return monitor.receiveEvent(e, objects);
	}
	
	// Pointcuts
	// Pointcuts for properties
	pointcut add_collection(Collection c): call(boolean java.util.Collection.add(Object)) && target(c) && if(!Set.class.isInstance(c)) && if(enabled);
	pointcut add_Set(Set s, Collection c): call(boolean java.util.Set.add(Object)) && target(s) && args(c) && if(enabled);
	
	// Pointcuts for memory used
	pointcut memory_track(): execution(public static void main(String[]));
	pointcut memory_end(): execution(public static void main(String[]));
	
	// Advices
	// Advices for memory used
	before(): memory_track(){
		Long mem = r.totalMemory() - r.freeMemory();
		if(memoryUsed == 0){
			memoryUsed = mem;
		}
		else{
			memoryUsed = (memoryUsed + mem)/2;
		}
	}
	
	after(): memory_end(){
		System.out.println("\nMEMORY USED: " + memoryUsed + " bytes\n");
	}
	
	// Advices for properties
	before(Collection c): add_collection(c){
		Verdict v = dispatchEvent(Event.modify_collection, c);
		System.out.println("Monitor "+c.hashCode()+": "+v);
	}
	
	before(Set s, Collection c): add_Set(s, c){
		Verdict v = dispatchEvent(Event.add_collection, s, c);
		System.out.println("Monitor "+c.hashCode()+": "+v);
	}
	
}
