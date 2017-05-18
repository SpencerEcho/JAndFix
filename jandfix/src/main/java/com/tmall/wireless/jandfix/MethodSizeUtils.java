package com.tmall.wireless.jandfix;

import android.os.Build;

/**
 * Created by jingchaoqinjc on 17/5/16.
 */

public class MethodSizeUtils {

    private static int size = Constants.INVALID_SIZE;
    private static IMethodSize methodSize = null;
    private static int methodIndexOffset = Constants.INVALID_SIZE;

    static {
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 21) {
        } else if (Build.VERSION.SDK_INT == 21) {
        } else if (Build.VERSION.SDK_INT == 22) {
            methodSize = new MethodSize5_1();
        } else if (Build.VERSION.SDK_INT >= 23) {
            methodSize = new MethodSize6_0();
        }
    }

    public static int methodSize() throws Exception {
        if (size == Constants.INVALID_SIZE) {
            size = methodSize.methodSize();
        }
        if(size == Constants.INVALID_SIZE){
            throw new RuntimeException();
        }
        return size;
    }

    public static int methodIndexOffset() throws Exception {
        if (methodIndexOffset == Constants.INVALID_SIZE) {
            methodIndexOffset = methodSize.methodIndexOffset();
        }

        if(methodIndexOffset == Constants.INVALID_SIZE){
            throw new RuntimeException();
        }

        return methodIndexOffset;
    }

}
