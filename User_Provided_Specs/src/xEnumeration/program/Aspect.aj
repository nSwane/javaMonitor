package xEnumeration.program;

import java.util.Enumeration;
import java.util.Vector;

import xEnumeration.monitor.Event;
import xEnumeration.monitor.Verdict;
import xEnumeration.monitor.VerificationMonitor;

public aspect Aspect {

	// Enumerator - Vector map
	private VerificationMonitor monitor = new VerificationMonitor();
	
	public Verdict dispatchEvent(Event event, Object... objects){
		Verdict v = monitor.receiveEvent(event, objects);
		return v;
	}
	
	pointcut create(Vector v): call(Enumeration<E> java.util.Vector.elements()) && target(v);
	pointcut next(Enumeration e): call(E java.util.Enumeration.nextElement()) && target(e);
	pointcut update(Vector v, Object o): call(* java.util.Vector.add(Object)) && target(v) && args(o);
	
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
