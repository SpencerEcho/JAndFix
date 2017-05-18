package com.tmall.wireless.jandfix;

import java.lang.reflect.Method;

import android.os.Build;

/**
 * Created by jingchaoqinjc on 17/5/15.
 */

public class MethodReplaceProxy implements IMethodReplace {

    private IMethodReplace realReplace;

    private MethodReplaceProxy() {
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 21) {
            realReplace = new MethodReplaceDalvik4_0();
        } else if (Build.VERSION.SDK_INT == 21) {
            realReplace = new MethodReplace5_0();
        } else if (Build.VERSION.SDK_INT == 22) {
            realReplace = new MethodReplace5_1();
        } else if (Build.VERSION.SDK_INT >= 23) {
            realReplace = new MethodReplace6_0();
        }
    }

    public static MethodReplaceProxy instance() {
        return Holder.instance;
    }

    @Override
    public void replace(Method src, Method dest) {
        realReplace.replace(src, dest);
    }

    private static class Holder {
        static MethodReplaceProxy instance = new MethodReplaceProxy();
    }

}
