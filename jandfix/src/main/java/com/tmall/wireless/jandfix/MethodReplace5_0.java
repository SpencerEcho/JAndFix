package com.tmall.wireless.jandfix;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by jingchaoqinjc on 17/5/15.
 */

public class MethodReplace5_0 implements IMethodReplace {


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
            Object o1 = artMethodField.get(src);
            Object o2 = artMethodField.get(dest);
            replaceReal(o1, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void replaceReal(Object src, Object dest) {
        Class c = src.getClass();
        try {
            while (c != Object.class) {
                for (Field f : c.getDeclaredFields()) {
                    f.setAccessible(true);
                    if ((f.getModifiers() & Modifier.STATIC) == 0) {
                        if (!f.getName().equals("declaringClass") && !f.getName().equals("methodIndex"))
                            f.set(src, f.get(dest));
                    }
                }
                c = c.getSuperclass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
