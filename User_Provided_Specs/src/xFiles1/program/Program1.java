package xFiles1.program;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Program1 {

	public static void main(String[] args) {
		RandomAccessFile file;
		String lecture;
		
		try {
			file = new RandomAccessFile("test.txt", "rw");
			lecture = file.readLine();
			file.writeBytes(lecture+lecture);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}