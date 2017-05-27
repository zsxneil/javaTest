package poi.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class FileUtil {
	
	public static void main(String[] args) throws Exception {
		String pathname = "E://test.txt";
		File file = new File(pathname);
		BufferedReader in
		   = new BufferedReader(new FileReader(pathname));
		StringBuffer buffer = new StringBuffer();
		String line = null;
		while((line = in.readLine()) != null) {
			System.out.println(line);
		}
		in.close();
		
		FileOutputStream outputStream = new FileOutputStream(file, true);
		byte[] b = {'*','*','*','*'};
		outputStream.write(b);
		outputStream.flush();
		outputStream.close();
		//System.out.println(buffer.toString());
	}
}
