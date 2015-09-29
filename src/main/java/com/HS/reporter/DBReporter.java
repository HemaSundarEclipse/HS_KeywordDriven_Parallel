/**
 * 
 */
package com.HS.reporter;

import com.HS.utils.Log;

/**
 * @author hemasundar
 *
 */
public class DBReporter implements Reporter {
    public Log logger;

    /**
    * 
    */
    public DBReporter() {
	logger = new Log(getClass().getSimpleName());
    }
}
