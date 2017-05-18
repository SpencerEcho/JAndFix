package com.tmall.wireless;

import android.app.Application;

import com.tmall.wireless.jandfix.MethodReplaceProxy;
import com.tmall.wireless.test.Test1;
import com.tmall.wireless.test.Test2;

import java.lang.reflect.Method;

/**
 * Created by jingchaoqinjc on 17/3/20.
 */

public class AndFixApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Method method1 = Test1.class.getDeclaredMethod("string");
            Method method2 = Test2.class.getDeclaredMethod("string");
            MethodReplaceProxy.instance().replace(method1, method2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
