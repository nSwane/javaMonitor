package xFiles2.program;

import java.io.IOException;
import java.io.RandomAccessFile;

public class program1 {
	public static void main(String[] args) {
		OpenCloseParametric.enable = true;
		
		RandomAccessFile file1, file2, file3;
		
		try {
			file1 = new RandomAccessFile("test1.txt", "rw");
			file2= new RandomAccessFile("test2.txt", "rw");
			file2.close();
			file3 = new RandomAccessFile("test1.txt", "rw");
			file1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
