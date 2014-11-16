package xFiles1.monitor;

public enum States {
	opened("opened"), 
	closed("closed"),
	definitlyClosed("definitlyClosed"),
	error("error");
	
	private String name;
	
	States (String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}