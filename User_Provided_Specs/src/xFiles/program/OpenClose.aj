package xFiles.program;

import java.io.RandomAccessFile;

import xFiles.monitor.Events;
import xFiles.monitor.Monitor;

public aspect OpenClose {

	private Monitor monitor = new Monitor();
	
	///// POINTCUTS /////
	pointcut open() :
		call(RandomAccessFile.new(String, String));
	
	pointcut close(RandomAccessFile f) : 
		target(f) && call(void java.io.RandomAccessFile.close()) ;
	
	pointcut terminate () : execution (public static void main (String[]));
	
	
	///// ADVICES /////
	after() : terminate(){
		this.monitor.receiveEvent(Events.terminate);
	}
	
	after() returning(RandomAccessFile f): open() {
		this.monitor.receiveEvent(Events.open);
	}
	
	before(RandomAccessFile f) : close(f){
		this.monitor.receiveEvent(Events.close);
	}
}
