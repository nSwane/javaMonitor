package xFiles3.program;


import java.io.RandomAccessFile;
import java.util.HashMap;

import xFiles3.monitor.Events;
import xFiles3.monitor.Monitor;


public aspect OpenCloseParametric {
	
	// Get runtime to retreive memory used
	private Runtime r = Runtime.getRuntime();
	
	// Initialize memory used
	private Long memoryUsed = new Long(0);
	
	// Enbale or not monitor calls
	public static boolean enable = false;
	
	HashMap<RandomAccessFile, Monitor> monitors = new HashMap<RandomAccessFile, Monitor>(); 
	
	///// POINTCUTS /////
	pointcut open(String mode) :
		call(RandomAccessFile.new(String, String)) && args(String, mode) && if(enable);
	
	pointcut close(RandomAccessFile f) :
		target(f) && call(void java.io.RandomAccessFile.close()) && if(enable);
	
	pointcut read(RandomAccessFile f) :
		target(f) && call(* java.io.RandomAccessFile.read*(..)) && if(enable);
	
	pointcut write(RandomAccessFile f) :
		target(f) && call(* java.io.RandomAccessFile.write*(..)) && if(enable);
	
	pointcut memory_track():
		withincode(public static void main(String[]));
	
	pointcut memory_end():
		execution(public static void main(String[]));
	
	///// ADVICES /////
	
	before(): memory_track(){
		Long mem = r.totalMemory() - r.freeMemory();
		if(memoryUsed == 0){
			memoryUsed = mem;
		}
		else{
			memoryUsed = (memoryUsed + mem)/2;
		}
	}
	
	after(): memory_end(){
		System.out.println("\nMEMORY USED: " + memoryUsed + " bytes\n");
	}
	
	after(String mode) returning(RandomAccessFile f): open(mode) {
		Monitor m = new Monitor(f.hashCode(), mode);
		monitors.put(f, m);
		dispatchEvent(f, Events.open);
	}
	
	before(RandomAccessFile f) : close(f){
		dispatchEvent(f, Events.close);
	}
	
	before(RandomAccessFile f) : read(f){
		dispatchEvent(f, Events.read);
	}
	
	before(RandomAccessFile f) : write(f){
		dispatchEvent(f, Events.write);
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
