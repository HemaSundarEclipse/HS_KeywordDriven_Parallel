/**
 * 
 */
package com.HS.practise;

/**
 * @author hemasundar
 *
 */
public class Test1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.out.println("Test");
	try {
	    int i = 1 / 0;
	} catch (Exception e) {
	    // System.out.println("in Catch block");
	    // throw new Error("Throwed error");
	}
	System.out.println("Test1");
    }
}
