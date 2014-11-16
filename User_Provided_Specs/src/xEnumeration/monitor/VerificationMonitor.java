package xEnumeration.monitor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;


public class VerificationMonitor {
	
	// Enumerator - Vector map
	private  HashMap<Enumeration, Vector> evMap;
	
	// Vector - State map
	private HashMap<Vector, Integer> vectorMap;
	
	// Enumerator - State map
	private HashMap<Enumeration, Integer> enumerationMap;
	
	public VerificationMonitor () {
		
		this.evMap = new HashMap<Enumeration, Vector>();
		this.vectorMap = new HashMap<Vector, Integer>();
		this.enumerationMap = new HashMap<Enumeration, Integer>();
		
	}

	/**
	 * Update the current state of this monitor according to the received event.
	 * Objects passed in parameter must match the event.
	 * If event is create, objects must be [<instance of Enumeration>, <instance of Vector>].
	 * If event is update, objects must be [<instance of Vector>].
	 * If event is next, objects must be [<instance of Enumeration>].
	 * @param event: Event
	 * @param objects: Object...
	 */
	public void updateState(Event event, Object... objects) {
		Enumeration enumeration = null;
		Vector vector = null;
		Integer state;
		
		switch(event){
			case create:
				
				// Map the enumeration with the corresponding vector
				
				// Get instances of each class (Enumeration and Vector)
				enumeration = (Enumeration) objects[0];
				vector = (Vector) objects[1];
				
				// Mapping
				this.evMap.put(enumeration, vector);
				
				// Initialize state
				if(this.vectorMap.containsKey(vector)){
					state = this.vectorMap.get(vector);				
					this.enumerationMap.put(enumeration, state);
				}
				else{
					this.enumerationMap.put(enumeration, 0);
					this.vectorMap.put(vector, 0);
				}
				break;
				
			case update:
				
				// Update vector's state
				
				// Get instanced class of Vector
				vector = (Vector) objects[0];
				
				// Update state
				if(this.vectorMap.containsKey(vector)){
					state = this.vectorMap.get(vector);
					state++;
					this.vectorMap.put(vector, state);
				}
				else{
					this.vectorMap.put(vector, 0);
				}
				break;
				
			case next:
				break;
			default:
				/* Not reachable */
		}
	}

	public Verdict currentVerdict (Event event, Object... objects) {
		Enumeration enumeration = null;
		Vector vector = null;
		Integer stateE = null;
		Integer stateV = null;
		Verdict verdict = Verdict.CURRENTLY_TRUE;
		
		// Get instance of Enumeration and Vector then compare their state.
		switch(event){
			case next:
				
				// Get instanced class of Enumeration
				enumeration = (Enumeration) objects[0];
				
				// Get the corresponding vector
				vector = this.evMap.get(enumeration);
				
				stateE = this.enumerationMap.get(enumeration);
				stateV = this.vectorMap.get(vector);
				
				if(stateE == stateV){
					verdict = Verdict.CURRENTLY_TRUE;
				}
				else{
					verdict = Verdict.FALSE;
				}
				break;
			default:
				/* verdict available only on next() */
		}
		
		return verdict;
	}

	/**
	 * Manage the received event.
	 * Objects passed in parameter must match the event.
	 * If event is create, objects must be [<instance of Enumeration>, <instance of Vector>].
	 * If event is update, objects must be [<instance of Vector>].
	 * If event is next, objects must be [<instance of Enumeration>].
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

