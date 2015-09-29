package com.HS.practise;

import java.io.FileNotFoundException;
import java.io.IOException;

public class OpenCSVPOC {

    public static void main(String[] args) throws IOException {
	OpenCSVPOC obj = new OpenCSVPOC();
	obj.readFromCSV();
	// obj.writeToCSV();
	obj.appendToCSV();

    }

    private void readFromCSV() throws FileNotFoundException {
	/*
	 * CSVReader reader = new CSVReader(new FileReader(
	 * "D:\\Xebia_Projects\\Projects\\git\\Tribune\\Test.csv")); String[]
	 * nextLine = null; try { while ((nextLine = reader.readNext()) != null)
	 * { System.out.println(Arrays.toString(nextLine)); } } catch
	 * (IOException e) { e.printStackTrace(); }
	 */
    }

    private void writeToCSV() throws IOException {
	/*
	 * CSVWriter writer = new CSVWriter(new FileWriter(
	 * "D:\\Xebia_Projects\\Projects\\git\\Tribune\\Test.csv")); String[]
	 * record = "Test5,Test6".split(","); writer.writeNext(record);
	 * writer.close();
	 */
    }

    private void appendToCSV() throws IOException {
	/*
	 * CSVWriter writer = new CSVWriter(new FileWriter(
	 * "D:\\Xebia_Projects\\Projects\\git\\Tribune\\Test.csv", true));
	 * String[] record = "Test5,Test6".split(","); String[] newline =
	 * "\r\n".split(","); writer.writeNext(newline);
	 * writer.writeNext(record); writer.close();
	 */
    }
}
