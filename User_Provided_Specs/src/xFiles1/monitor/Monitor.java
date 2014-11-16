package xFiles1.monitor;

public class Monitor {

	private States currentState;
	private String name;
	
	public Monitor(){
		this.currentState = States.closed;
		this.name = "";
	}
	
	
	public Monitor(String name) {
		this.currentState = States.closed;
		this.name = name;
	}


	public Verdict receiveEvent(Events e) {
		System.out.println("=> Monitor "+this.name+" received event "+e);
		updateState(e);
		emitVerdict();
		return currentVerdict();
	}
	
	public void updateState(Events e) {
		switch (currentState) {
		case closed:
			switch(e){
			case open:
				currentState = States.opened;
				break;
				
			case terminate:
				currentState = States.definitlyClosed;
				break;
				
			case close:
				// nothing to do in this case (another requirement)
				break;		
			}
			break;
			
		case opened:
			switch(e){
			case close:
				currentState = States.closed;
				break;
				
			case terminate:
				currentState = States.error;
				break;
				
			case open:
				// nothing to do in this case
				break;
			}
			break;
			
		case definitlyClosed:
			// can't reach this code
			break;
			
		case error:
			// No need to execute any code.
			break;
		}
	}
	
	public Verdict currentVerdict () {
		switch(this.currentState) {
		case closed:
			return Verdict.CURRENTLY_TRUE;
		case opened:
			return Verdict.CURRENTLY_FALSE;
		case definitlyClosed:
			return Verdict.TRUE;
		case error:
			return Verdict.FALSE;
		default:
			return Verdict.FALSE;
		}
	}
	
	public void emitVerdict () {
		System.out.println("Monitor "+this.name+" verdict: "+currentVerdict());
	}
}
