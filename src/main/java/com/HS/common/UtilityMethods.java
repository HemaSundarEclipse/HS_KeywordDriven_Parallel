/**
 * 
 */
package com.HS.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author hemasundar
 * 
 *         All the common/utility methods which can be used across the
 *         framework.
 * 
 *         Currently planning to use these methods with on the spot object
 *         creation. So methods need to be independent to others.
 *
 */
public class UtilityMethods {
    /**
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String readFile(File file) throws FileNotFoundException, IOException {
	BufferedReader br = new BufferedReader(new FileReader(file));
	String line = "", oldtext = "";
	while ((line = br.readLine()) != null) {
	    oldtext += line + "\r\n";
	    // System.out.println("Older HTML text is - " + oldtext);
	}
	br.close();
	return oldtext;
    }

    /**
     * @param oldtext
     * @throws IOException
     */
    public void writeFile(File file, String oldtext) throws IOException {
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	bw.write(oldtext);
	bw.flush();
	bw.close();
    }

    /**
     * @param threadLogs
     */
    public void createCurrentAndParentDirectories(File threadLogs) {
	if (!threadLogs.exists()) {
	    threadLogs.mkdirs();
	}
    }
}
