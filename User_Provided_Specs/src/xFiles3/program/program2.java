package xFiles3.program;

import java.io.IOException;
import java.io.RandomAccessFile;

public class program2 {
	public static void main(String[] args) {
		OpenCloseParametric.enable = true;
		
		RandomAccessFile file1, file2;
		
		try {
			file1 = new RandomAccessFile("test1.txt", "rw");
			file2= new RandomAccessFile("test1.txt", "r");
			file1.writeBytes("test");
			file1.close();
			file2.close();
			file2.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}