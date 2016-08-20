package com.sun.java.jdbc.batch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileTest {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\Sunny\\Desktop\\batch_insert.txt"));
		for(int i=0;i<50000;i++){
			String line = "Accoutn"+i+"|"+100+i+"|"+"SAVING";
			pw.println(line);
		}
		pw.flush();
		pw.close();
		System.out.println("Done");
	}

}
