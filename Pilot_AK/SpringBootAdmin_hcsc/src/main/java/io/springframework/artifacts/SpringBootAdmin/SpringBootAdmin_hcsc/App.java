package io.springframework.artifacts.SpringBootAdmin.SpringBootAdmin_hcsc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception

	{
		System.out.println("Hello World!");
		App memoryTest = new App();
		memoryTest.generateOOM();
		memoryTest.generateDiskSpaceIssue();
	}

	public void generateOOM() throws Exception {
		int iteratorValue = 20;
		System.out.println("\n=================> OOM test started..\n");
		for (int outerIterator = 1; outerIterator < 20; outerIterator++) {
			System.out.println("Iteration " + outerIterator + " Free Mem: " + Runtime.getRuntime().freeMemory());
			int loop1 = 2;
			int[] memoryFillIntVar = new int[iteratorValue];
			// feel memoryFillIntVar array in loop..
			do {
				memoryFillIntVar[loop1] = 0;
				loop1--;
			} while (loop1 > 0);
			iteratorValue = iteratorValue * 5;
			System.out.println("\nRequired Memory for next loop: " + iteratorValue);
			Thread.sleep(2000);
		}
	}

	String encoding = "UTF-8";
	long maxlines = 1000000000;
	BufferedReader reader = null;
	BufferedWriter writer = null;

	public void generateDiskSpaceIssue() throws Exception {
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("/bigfile.txt"), encoding));
			int count = 0;
			for (String line; (line = reader.readLine()) != null;) {
				if (count++ % maxlines == 0) {
					reader.close();
					writer = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream("/smallfile" + (count / maxlines) + ".txt"), encoding));
				}
				writer.write(line);
				writer.newLine();
			}
		} finally {
			writer.close();
			reader.close();
		}
	}
}