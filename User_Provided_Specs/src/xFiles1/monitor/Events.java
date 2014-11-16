package xFiles1.monitor;

public enum Events {
	open ("open"), 
	close("close"), 
	terminate("terminate");
	
	private String name;
	
	Events (String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
