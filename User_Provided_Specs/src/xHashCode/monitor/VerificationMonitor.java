package xHashCode.monitor;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import xHashCode.monitor.Event;
import xHashCode.monitor.Verdict;

public class VerificationMonitor {

	private int id;
	
	// Collection - HashSet map
	private HashMap<Collection, HashSet> chMap;
	
	public VerificationMonitor(){
		this.chMap = new HashMap<Collection, HashSet>();
	}
	
	public VerificationMonitor(int id){
		this.chMap = new HashMap<Collection, HashSet>();
	}
	
	/**
	 * Update the current state of this monitor according to the received event.
	 * Objects passed in parameter must match the event.
	 * If event is modify, objects must be [<instance of Collection>].
	 * If event is add, objects must be [<instance of HashSet>, <instance of Collection>].
	 * @param event: Event
	 * @param objects: Object...
	 */
	public void updateState(Event event, Object... objects) {
		Collection c = null;
		HashSet s = null;
		
		switch(event){
			case modify_collection:
				
				c = (Collection) objects[0];
				
				if(!this.chMap.containsKey(c)){
					this.chMap.put(c, null);
				}
				
				break;
				
			case add_collection:
				s = (HashSet) objects[0];
				c = (Collection) objects[1];
				
				this.chMap.put(c, s);
				break;
				
			default:
				/* Not reachable */
		}
	}

	public Verdict currentVerdict (Event event, Object... objects) {
		Collection c = null;
		HashSet s = null;
		Verdict v = Verdict.CURRENTLY_TRUE;
		
		switch(event){
			case modify_collection:
				
				c = (Collection) objects[0];
				
				s = this.chMap.get(c);
				if(s != null){
					v = Verdict.FALSE;
				}
				break;
				
			default:
				/* Verdict on add() */
		}
		return v;
	}

	/**
	 * Manage the received event.
	 * Objects passed in parameter must match the event.
	 * If event is modify, objects must be [<instance of Collection>].
	 * If event is add, objects must be [<instance of HashSet>, <instance of Collection>].
	 * @param event: Events
	 * @param objects: Object...
	 * @return
	 */
	public Verdict receiveEvent(Event event, Object... objects) {
		System.out.println("=> Monitor : received event "+event);
		updateState(event, objects);
		return currentVerdict(event, objects);
	}
	
}
