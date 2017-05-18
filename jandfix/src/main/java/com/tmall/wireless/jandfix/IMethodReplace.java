package com.tmall.wireless.jandfix;

import java.lang.reflect.Method;

/**
 * Created by jingchaoqinjc on 17/5/15.
 */

public interface IMethodReplace {

    public void replace(Method src, Method dest);

}
