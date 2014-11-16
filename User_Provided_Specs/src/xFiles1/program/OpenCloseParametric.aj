package xFiles1.program;

import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map.Entry;

import xFiles1.monitor.Events;
import xFiles1.monitor.Monitor;


public aspect OpenCloseParametric {
	public static boolean enable = false;
	
	HashMap<RandomAccessFile, Monitor> monitors = new HashMap<RandomAccessFile, Monitor>(); 
	
	///// POINTCUTS /////
	pointcut open(String name) :
		call(RandomAccessFile.new(String, String)) && args(name, String) && if(enable);
	
	pointcut close(RandomAccessFile f) : 
		target(f) && call(void java.io.RandomAccessFile.close()) && if(enable);
	
	pointcut terminate () : execution (public static void main (String[])) && if(enable);
	
	
	///// ADVICES /////
	after() : terminate(){
		// we send event to all monitors
		for(Entry<RandomAccessFile, Monitor> entry : monitors.entrySet()) {
		    Monitor m = entry.getValue();
		    m.receiveEvent(Events.terminate);
		}
	}
	
	after(String name) returning(RandomAccessFile f): open(name) {
		Monitor m = monitors.get(f);
		
		if(m == null){
			m = new Monitor(f.hashCode()+"(\""+name+"\")");
			monitors.put(f, m);
		}
		
		m.receiveEvent(Events.open);
	}
	
	before(RandomAccessFile f) : close(f){
		Monitor m = monitors.get(f);
		m.receiveEvent(Events.close);
	}
}
