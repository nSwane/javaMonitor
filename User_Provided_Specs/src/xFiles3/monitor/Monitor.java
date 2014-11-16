package xFiles3.monitor;

public class Monitor {

	private States currentState;
	private int id;
	private String fileMode;
		
	public Monitor(int id) {
		this.currentState = States.closed;
		this.id = id;
		this.fileMode = "";
	}

	public Monitor(int id, String mode) {
		this.currentState = States.closed;
		this.id = id;
		this.fileMode = mode;
	}

	public Verdict receiveEvent(Events e) {
		System.out.println("=> Monitor "+this.id+" received event "+e);
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
				
			case close:
				currentState = States.error;
				break;
				
			case read:
				currentState = States.error;
				break;
				
			case write:
				currentState = States.error;
				break;
			}
			break;
			
		case opened:
			switch(e){
			case close:
				currentState = States.closed;
				break;
				
			case open:
				// nothing to do in this case
				break;
				
			case read:
				if(!fileMode.contains("r")){
					currentState = States.error;
				}
				break;
				
			case write:
				if(!fileMode.contains("w")){
					currentState = States.error;
				}
				break;
			}
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
			return Verdict.CURRENTLY_TRUE;
		case error:
			return Verdict.FALSE;
		default:
			return Verdict.FALSE;
		}
	}
	
	public void emitVerdict () {
		System.out.println("Monitor "+this.id+" verdict: "+currentVerdict());
	}
}
