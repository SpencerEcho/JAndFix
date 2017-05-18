package com.tmall.wireless.jandfix;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jingchaoqinjc on 17/5/15.
 */

public class MethodReplace6_0 implements IMethodReplace {

    static Field artMethodField;

    static {
        try {
            Class absMethodClass = Class.forName("java.lang.reflect.AbstractMethod");
            artMethodField = absMethodClass.getDeclaredField("artMethod");
            artMethodField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void replace(Method src, Method dest) {
        try {
            long artMethodSrc = (long) artMethodField.get(src);
            long artMethodDest = (long) artMethodField.get(dest);
            replaceReal(artMethodSrc, artMethodDest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void replaceReal(long src, long dest) throws Exception {
        int methodSize = MethodSizeUtils.methodSize();
        int methodIndexOffset = MethodSizeUtils.methodIndexOffset();
        //methodIndex need not replace,becase the process of finding method in vtable
        int methodIndexOffsetIndex = methodIndexOffset / 4;
        //why 1? index 0 is declaring_class, declaring_class need not replace.
        for (int i = 1, size = methodSize / 4; i < size; i++) {
            if (i != methodIndexOffsetIndex) {
                int value = UnsafeProxy.getIntVolatile(dest + i * 4);
                UnsafeProxy.putIntVolatile(src + i * 4, value);
            }
        }
    }
}
