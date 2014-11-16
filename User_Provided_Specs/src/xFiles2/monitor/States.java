package xFiles2.monitor;

public enum States {
	opened("opened"), 
	closed("closed"),
	error("error");
	
	private String name;
	
	States (String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}