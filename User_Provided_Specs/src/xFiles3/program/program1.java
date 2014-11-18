package xFiles3.program;

import java.io.IOException;
import java.io.RandomAccessFile;

public class program1 {
	public static void main(String[] args) {
		
		RandomAccessFile file1, file2;
		
		try {
			file1 = new RandomAccessFile("test1.txt", "r");
			file2= new RandomAccessFile("test1.txt", "r");
			file1.writeBytes("test");
			file2.read();
			file1.close();
			file2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
