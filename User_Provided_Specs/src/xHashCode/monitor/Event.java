package xHashCode.monitor;

public enum Event {
	modify_collection("modify"), add_collection("add");
	
	private String name;
	
	private Event(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
