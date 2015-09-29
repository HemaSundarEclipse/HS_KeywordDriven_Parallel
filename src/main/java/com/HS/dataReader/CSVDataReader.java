package com.HS.dataReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.HS.utils.Log;

/**
 * All Utility functions that assist in communicating with external files like
 * TestCase sheet, DataData sheet and Object Repository sheet etc are listed
 * here.
 * 
 * @author
 *
 */
public class CSVDataReader {
    public Log logger;
    public String objectRepositoryFileLocation;
    public String testCaseFileLocation;

    public CSVDataReader() {
	logger = new Log(getClass().getSimpleName());
    }

    /**
     * 
     */
    public void setFilePaths() {
	// Name and location of Object Repository.
	objectRepositoryFileLocation = "src/main/java/com/HS/executionData/ObjectRepository";
	/*
	 * Location of test cases. Will be used in setFileLocation() method of
	 * ReaderUtility.java
	 */
	testCaseFileLocation = "src/main/java/com/HS/executionData/TestCases";

    }

    /**
     * Helper function that takes a file (excel) and name of sheet in that file
     * and Returns a table representing data from given sheet in tabular format.
     * 
     */
    public List<String[]> getRequiredRows(String FilePath) throws Exception {
	File inFile = new File(FilePath);
	List<String[]> completeData = new ArrayList<String[]>();
	BufferedReader reader = null;
	try {
	    String indRow = "";
	    reader = new BufferedReader(new FileReader(inFile));
	    // Skip reading the header
	    // reader.readLine();
	    while ((indRow = reader.readLine()) != null) {
		String[] indRowAsArray = indRow.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		for (int i = 0; i < indRowAsArray.length; i++) {
		    indRowAsArray[i] = indRowAsArray[i].trim();
		    if (indRowAsArray[i].startsWith("\"") && indRowAsArray[i].endsWith("\"")) {
			indRowAsArray[i] = indRowAsArray[i].replace("\"", "");
			indRowAsArray[i] = indRowAsArray[i].trim();
		    }
		}
		completeData.add(indRowAsArray);
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return completeData;
    }

    /**
     * Get the data from Object Repository, implicitly calls 'getRequiredRows()'
     * 
     * @return <b>ResultSet object of Data.</b>
     * @throws Exception
     */
    public List<String[]> getORData() {
	List<String[]> objTb = null;
	try {
	    objTb = getRequiredRows(objectRepositoryFileLocation + ".csv");

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return objTb;
    }

    /**
     * Get the data from Test case file.
     * 
     * @return <b>ResultSet object of the data </b>
     * @throws Exception
     */
    public List<String[]> getTestCaseData() {
	List<String[]> rs = null;
	try {
	    rs = getRequiredRows(testCaseFileLocation + ".csv");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rs;
    }

    public String getCellValue(List<String[]> sheet, int row, String ColHeader) {
	int colNum = 0;

	String[] header = sheet.get(0);
	try {
	    for (int i = 0; i < header.length; i++) {
		if (header[i].equalsIgnoreCase(ColHeader)) {
		    colNum = i;
		    break;
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error(e.getLocalizedMessage());
	}
	String[] specificRow = null;
	try {
	    specificRow = sheet.get(row);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error(e.getLocalizedMessage());
	}
	return specificRow[colNum];

    }
}
