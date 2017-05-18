package com.tmall.wireless.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.tmall.wireless.R;
import com.tmall.wireless.test.ITest;
import com.tmall.wireless.test.Test1;
import com.tmall.wireless.test.TestImpl;

import java.lang.reflect.Method;

/**
 * Created by jingchaoqinjc on 17/3/20.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(new Test1().string() + new Test1().string2());
//        main(null);
    }

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
