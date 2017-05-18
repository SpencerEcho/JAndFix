package com.tmall.wireless.test;

import com.tmall.wireless.AndFixApplication;

import junit.framework.Test;

import java.lang.reflect.Method;

/**
 * Created by jingchaoqinjc on 17/4/4.
 */

public class Main {

    public static void main(String[] args) {

        ITest iTest = new TestImpl();
        try {
            Method method1 = ITest.class.getDeclaredMethod("test");
            Method method2 = TestImpl.class.getDeclaredMethod("test");
            method1.invoke(iTest);
            method2.invoke(iTest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
