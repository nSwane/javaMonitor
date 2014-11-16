package xFiles2.monitor;

public enum Events {
	open ("open"), 
	close("close");
	
	private String name;
	
	Events (String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
