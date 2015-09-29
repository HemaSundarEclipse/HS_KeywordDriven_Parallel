package com.HS.practise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ManagingCSV {

    public static void main(String[] args) throws IOException {
	ManagingCSV obj = new ManagingCSV();
	obj.readCSVLineByLine();
	obj.readCSVCellByCell(2, 2);
    }

    private void readCSVLineByLine() {
	BufferedReader reader = null;
	try {
	    String line = "";
	    reader = new BufferedReader(new FileReader("D:\\Xebia_Projects\\Projects\\git\\Tribune\\Test.csv"));
	    // Skip reading the header
	    // reader.readLine();
	    while ((line = reader.readLine()) != null) {
		String[] cells = line.split(",");
		for (int i = 0; i < cells.length; i++) {
		    cells[i] = cells[i].trim();
		}
		System.out.println(Arrays.toString(cells));
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}
    }

    private void readCSVCellByCell(int row, int col) throws IOException {
	BufferedReader reader = null;

	String line = "";
	try {
	    reader = new BufferedReader(new FileReader("D:\\Xebia_Projects\\Projects\\git\\Tribune\\Test.csv"));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	for (int i = 0; i < row; i++) {
	    line = reader.readLine();
	}
	System.out.println("Current row is :" + line);
	String[] cells = line.split(",");
	try {
	    System.out.println("Cell required by user is :" + cells[col]);
	} catch (ArrayIndexOutOfBoundsException e) {
	    System.out.println("No value specified");
	}
    }

    private void WriteCSVCellByCell(int row, int col) {

    }
}
