package xFiles3.program;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Memory used:
 * when monitor is called: 2685616 bytes
 * when monitor is not called: 2685144 bytes
 * 
 * @author marcg_000
 *
 */
public class program3 {
	public static void main(String[] args) {
		OpenCloseParametric.enable = true;
		
		RandomAccessFile file1, file2;
		
		try {
			file1 = new RandomAccessFile("test1.txt", "rw");
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
