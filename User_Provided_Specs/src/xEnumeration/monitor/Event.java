package xEnumeration.monitor;

public enum Event {
	create("create"), next("next"), update("update");
	
	private String name;
	
	private Event(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
