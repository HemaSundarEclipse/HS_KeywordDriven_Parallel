/**
 * 
 */
package com.HS.reporter;

import com.HS.utils.Log;

/**
 * @author hemasundar
 *
 */
public class ExcelReporter implements Reporter {
    public Log logger;

    /**
    * 
    */
    public ExcelReporter() {
	logger = new Log(getClass().getSimpleName());
    }
}
