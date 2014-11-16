package xHashCode.program;

import java.util.Collection;
import java.util.Set;

import xHashCode.monitor.Event;
import xHashCode.monitor.Verdict;
import xHashCode.monitor.VerificationMonitor;

public aspect Aspect {

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
	
	pointcut add_collection(Collection c): call(boolean java.util.Collection.add(Object)) && target(c) && if(!Set.class.isInstance(c));
	pointcut add_Set(Set s, Collection c): call(boolean java.util.Set.add(Object)) && target(s) && args(c);
	
	before(Collection c): add_collection(c){
		Verdict v = dispatchEvent(Event.modify_collection, c);
		System.out.println("Monitor "+c+": "+v);
	}
	
	before(Set s, Collection c): add_Set(s, c){
		Verdict v = dispatchEvent(Event.add_collection, s, c);
		System.out.println("Monitor "+c+": "+v);
	}
	
}
