package xFiles.program;

import java.io.IOException;
import java.io.RandomAccessFile;

public class program2 {
	
	public static void main(String[] args) {
		RandomAccessFile file;
		String lecture;
		
		try {
			file = new RandomAccessFile("test.txt", "rw");
			lecture = file.readLine();
			file.writeBytes(lecture+lecture);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
