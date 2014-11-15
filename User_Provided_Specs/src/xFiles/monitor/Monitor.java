package xFiles.monitor;

public class Monitor {

	private States currentState;
	
	public Monitor(){
		currentState = States.closed;
	}
	
	
	public Verdict receiveEvent(Events e) {
		System.out.println("=> Monitor received event "+e);
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
				
			default:
				// nothing to do in others cases
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
				
			default:
				// nothing to do in others cases
				break;
			}
			break;
			
		default:
			// nothing to do in others cases
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
		System.out.println("Monitor verdict: "+currentVerdict());
	}
}
