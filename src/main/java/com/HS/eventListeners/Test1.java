package com.HS.eventListeners;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Test1 {

    @org.testng.annotations.Test
    public void main() {
	System.out.println("Execution of Main test is carring on");
    }

    @BeforeMethod
    public void beforeMethod() {

	System.out.println("Execution of Before method is carring on");
    }

    @AfterMethod
    public void afterMethod() {

	System.out.println("Execution of After method is carring on");
    }
}
