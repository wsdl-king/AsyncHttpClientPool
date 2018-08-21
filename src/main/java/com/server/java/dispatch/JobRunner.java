package com.server.java.dispatch;

import com.server.java.jmev.http.JmevHttp;
import com.server.java.jmev.queue.JmevQueue;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qiwenshuai
 * @note
 * @since 18-8-20 14:08 by jdk 1.8
 */
public class JobRunner implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(JobRunner.class);

    @Override
    public void run() {
        while (JmevQueue.getInstance().getQueue().size() > 0) {
            String vin = JmevQueue.getInstance().getQueue().poll();
            if (StringUtils.isNotBlank(vin)) {
                try {
                    JmevHttp.cycleExecute("10002,2000", vin);
                } catch (Exception e) {
                    logger.error("出现错误,问题为:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
