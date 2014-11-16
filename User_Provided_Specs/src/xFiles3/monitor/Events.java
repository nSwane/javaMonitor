package xFiles3.monitor;

public enum Events {
	open ("open"), 
	close("close"),
	read("read"),
	write("write");
	
	private String name;
	
	Events (String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
