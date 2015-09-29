/**
 * 
 */
package com.HS.utils;

import java.util.ArrayList;

import com.HS.dataReader.CSVDataReader;

/**
 * @author hemasundar
 *
 */
public class CommonMethods {
    public Log logger;
    public CSVDataReader reader;
    public ArrayList<String> TestCaseIDsForExecution;

    /**
    * 
    */
    public CommonMethods() {
	logger = new Log(getClass().getSimpleName());
	reader = new CSVDataReader();
	TestCaseIDsForExecution = new ArrayList<String>();
    }

}
