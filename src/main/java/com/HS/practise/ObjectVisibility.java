package com.HS.practise;

import org.testng.annotations.Test;

public class ObjectVisibility {
    int i = 0;

    @Test
    public void method1() {
	i = (int) Thread.currentThread().getId();
	System.out.println("i value in method1 : " + i + " in Thread : " + Thread.currentThread().getId());
	method2();
	method3();
	method4();
    }

    public void method2() {
	System.out.println("i value in method2 : " + i + " in Thread : " + Thread.currentThread().getId());
    }

    public void method3() {
	System.out.println("i value in method3 : " + i + " in Thread : " + Thread.currentThread().getId());
    }

    public void method4() {
	System.out.println("i value in method4 : " + i + " in Thread : " + Thread.currentThread().getId());
    }
}