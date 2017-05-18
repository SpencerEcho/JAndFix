package com.tmall.wireless.jandfix;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jingchaoqinjc on 17/5/16.
 */

public class MethodSize5_1 implements IMethodSize {

    private static int size =Constants.INVALID_SIZE;
    private static int methodIndexOffset = Constants.INVALID_SIZE;

    static {
        try {
            Field artMethodField;
            Class absMethodClass = Class.forName("java.lang.reflect.AbstractMethod");
            artMethodField = absMethodClass.getDeclaredField("artMethod");
            artMethodField.setAccessible(true);

            //init size
            Method method1 = MethodSizeCase.class.getDeclaredMethod("method1");
            Method method2 = MethodSizeCase.class.getDeclaredMethod("method2");

            Object object1 = artMethodField.get(method1);
            Object object2 = artMethodField.get(method2);

            long method1Addr = UnsafeProxy.getObjectAddress(object1);
            long method2Addr = UnsafeProxy.getObjectAddress(object2);
            size = (int) (method2Addr - method1Addr);
            if (size < 0) {
                size = -size;
            }

            //init methodIndexOffset
            Class artMethodClass = object1.getClass();
            Field methodIndexField = artMethodClass.getDeclaredField("methodIndex");

            methodIndexOffset = UnsafeProxy.objectFieldOffset(methodIndexField);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int methodSize() throws Exception {
        return size;
    }

    @Override
    public int methodIndexOffset() throws Exception {
        return methodIndexOffset;
    }
}
