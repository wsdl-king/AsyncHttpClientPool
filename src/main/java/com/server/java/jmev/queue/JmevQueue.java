package com.server.java.jmev.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author qiwenshuai
 * @note
 * @since 18-8-20 13:46 by jdk 1.8
 */
public class JmevQueue {

    private static final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();


    private static class HolderQueue {
        //静态内部类用到的时候再加载
        private final static JmevQueue instance = new JmevQueue();
    }

    public static JmevQueue getInstance() {
        return HolderQueue.instance;
    }


    public ConcurrentLinkedQueue<String> getQueue() {
        return queue;
    }

    public void add(String vin) {
        queue.offer(vin);
    }
}
