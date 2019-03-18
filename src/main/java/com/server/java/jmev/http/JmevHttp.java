package com.server.java.jmev.http;


import com.alibaba.fastjson.JSONObject;
import com.server.java.jmev.callback.JmevRealTime;
import com.server.java.jmev.security.SecretUtils;
import com.server.java.util.config.ConfigUtils;
import com.server.java.util.redis.RedisTool;
import com.server.java.util.spring.SpringUtils;
import com.server.scala.service.OnlineService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.server.java.util.http.HttpClientUtil.httpAsyncPost;

/**
 * @author qiwenshuai
 * @note 江铃新能源部分的http处理逻辑
 * @since 18-8-19 10:10 by jdk 1.8
 */

public class JmevHttp {


    // jmev realTime data
    private static final String url = ConfigUtils.getPropValues("realTimeUrl");
    // current contentType
    private static final String contentYpe = "application/json; charset=utf-8";
    // jmev authorization
    private static final String Authorization = ConfigUtils.getPropValues("authorization");

    //创建一个延迟调度线程池
    private static final ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(4);

    private static final RedisTool redisTool = SpringUtils.getBean("redisTool");

    private static final OnlineService onlineService = SpringUtils.getBean("onlineService");

    private static void execute(String codesStr, String vin) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", contentYpe);
        headers.put("Authorization", Authorization);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataIds", codesStr);
        jsonObject.put("vins", vin);
        //请求参数加密
        String secConStr = SecretUtils.encrypt(jsonObject.toString());
        JSONObject jsonObjHead = new JSONObject();
        jsonObjHead.put("requestMsg", secConStr);
        jsonObjHead.put("sign", "");
        JmevRealTime jmevRealTime = new JmevRealTime(redisTool, vin,onlineService);
        httpAsyncPost(url, headers, jsonObjHead.toJSONString(), null, jmevRealTime);
    }


    /**
     * 循环调度器，调度完成后每隔1分钟继续调度.
     */
    public static void cycleExecute(String codesStr, String vin) throws Exception {
        execute(codesStr, vin);
        //延时8秒执行
        threadPool.schedule(() -> {
            try {
                cycleExecute(codesStr, vin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 60, TimeUnit.SECONDS);

    }

}
