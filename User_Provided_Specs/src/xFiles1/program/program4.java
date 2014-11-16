package xFiles1.program;

import java.io.IOException;
import java.io.RandomAccessFile;

public class program4 {
	public static void main(String[] args) {
		OpenCloseParametric.enable = true;
		
		RandomAccessFile file1, file2, file3;
		String lecture;
		
		try {
			file1 = new RandomAccessFile("test1.txt", "rw");
			lecture = file1.readLine();
			file1.writeBytes(lecture+lecture);
			file2= new RandomAccessFile("test2.txt", "rw");
			file2.close();
			file3 = new RandomAccessFile("test1.txt", "rw");
			file1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
