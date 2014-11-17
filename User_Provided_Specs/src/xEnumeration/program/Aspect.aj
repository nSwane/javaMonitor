package xEnumeration.program;

import java.util.Enumeration;
import java.util.Vector;

import xEnumeration.monitor.Event;
import xEnumeration.monitor.Verdict;
import xEnumeration.monitor.VerificationMonitor;

public aspect Aspect {

	private Runtime r = Runtime.getRuntime();
	private Long memoryUsed = new Long(0);
	private static boolean enabled = false;
	// Program4:
	//	1999695 bytes when false		--> depends on where the pointcuts are set
	//	2271583 bytes when true
	
	// Enumerator - Vector map
	private VerificationMonitor monitor = new VerificationMonitor();
	
	/**
	 * Objects passed in parameter must match the event.
	 * If event is create, objects must be [<instance of Enumeration>, <instance of Vector>].
	 * If event is update, objects must be [<instance of Vector>].
	 * If event is next, objects must be [<instance of Enumeration>].
	 * 
	 */
	public Verdict dispatchEvent(Event event, Object... objects){
		Verdict v = monitor.receiveEvent(event, objects);
		return v;
	}
	
	pointcut create(Vector v): call(Enumeration<E> java.util.Vector.elements()) && target(v) && if(enabled);
	pointcut next(Enumeration e): call(E java.util.Enumeration.nextElement()) && target(e) && if(enabled);
	pointcut update(Vector v, Object o): call(* java.util.Vector.add(Object)) && target(v) && args(o) && if(enabled);
	pointcut memory_track(): withincode(public static void main(String[]));
	pointcut memory_end(): execution(public static void main(String[]));
	
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
	
	Enumeration around(Vector v): create(v){
		Enumeration e = proceed(v);
		dispatchEvent(Event.create, e, v);
		return e;
	}
	
	before(Enumeration e): next(e){
		Verdict v = dispatchEvent(Event.next, e);
		System.out.println("Monitor "+e.hashCode()+": "+v);
	}
	
	before(Vector v, Object o): update(v, o){
		dispatchEvent(Event.update, v);
	}
	
}
