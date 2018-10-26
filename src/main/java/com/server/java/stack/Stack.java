package com.server.java.stack;

/**
 * @author qiwenshuai
 * @note 栈内存结构, 暂时不考虑并发
 * @since 18-10-26 11:23 by jdk 1.8
 */
public class Stack {

    //栈的总大小
    private int maxSize;
    //当前指向的位置
    private int currentSize = 0;
    private Object[] objects;
    public Object[] buildStack(int n) {
        if (objects == null) {
            synchronized (Stack.class) {
                if (objects != null) {
                    return objects;
                }
                maxSize = n;
                objects = new Object[n];
                return objects;
            }
        } else {
            return objects;
        }
    }

    //入栈 1-2-3-4-5
    public Object stackPush(Object o) {
            if (currentSize == maxSize) {
                //栈满了
                return null;
            }
            objects[currentSize] = o;
            ++currentSize;
            return objects;
    }

    //出栈
    public Object stackPull() {
            if (currentSize == 0) {
                return null;
            }
            Object object = objects[currentSize - 1];
            --currentSize;
            return object;
    }
}
