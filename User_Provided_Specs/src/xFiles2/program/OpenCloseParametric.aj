package xFiles2.program;
import java.io.RandomAccessFile;
import java.util.HashMap;

import xFiles2.monitor.Events;
import xFiles2.monitor.Monitor;


public aspect OpenCloseParametric {
	public static boolean enable = false;
	
	HashMap<RandomAccessFile, Monitor> monitors = new HashMap<RandomAccessFile, Monitor>(); 
	
	///// POINTCUTS /////
	pointcut open() :
		call(RandomAccessFile.new(String, String)) && if(enable);
	
	pointcut close(RandomAccessFile f) :
		target(f) && call(void java.io.RandomAccessFile.close()) && if(enable);
	
	
	///// ADVICES /////
	after() returning(RandomAccessFile f): open() {
		dispatchEvent(f, Events.open);
	}
	
	before(RandomAccessFile f) : close(f){
		dispatchEvent(f, Events.close);
	}
	
	
	///// METHODS /////
	private void dispatchEvent(RandomAccessFile f, Events e) {
		Monitor m = monitors.get(f);
		
		if(m == null){
			m = new Monitor(f.hashCode());
			monitors.put(f, m);
		}
		
		m.receiveEvent(e);
	}

	
}
