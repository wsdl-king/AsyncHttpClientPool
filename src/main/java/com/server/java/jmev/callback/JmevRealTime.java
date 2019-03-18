package com.server.java.jmev.callback;

import com.alibaba.fastjson.JSONObject;
import com.server.java.jmev.security.SecretUtils;
import com.server.java.util.date.DateUtil;
import com.server.java.util.http.callback.AsyncHttpClientCallback;
import com.server.java.util.redis.RedisTool;
import com.server.scala.entity.VehicleOnlineStatus;
import com.server.scala.service.OnlineService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author qiwenshuai
 * @note 异步httpResponse处理逻辑需要写在completed
 * @since 18-8-18 15:03 by jdk 1.8
 */
public class JmevRealTime extends AsyncHttpClientCallback {


    //此标识用于判断车辆所属于的车辆--北里平台;
    private static final String B_FLAG = "jmev_b_flag{inter}";
    //此标识用于判断车辆所属于的车辆--e智;
    private static final String E_FLAG = "jmev_e_flag{inter}";

    private static Logger logger = LoggerFactory.getLogger(JmevRealTime.class);


    private RedisTool redisTool;

    private OnlineService onlineService;
    private String vin;

    public JmevRealTime(RedisTool redisTool, String vin, OnlineService onlineService) {
        this.redisTool = redisTool;
        this.vin = vin;
        this.onlineService = onlineService;
    }


    /**
     * 继承父类直接执行逻辑就OK,io流会自己关闭
     */
    @Override
    public void completed(HttpResponse response) {
        try {
            JSONObject returnJsonObj = getReturnJsonObj(response);
            judge(returnJsonObj);
        } catch (Exception e) {
            //出错,不执行任何操作
            logger.error("出现错误,错误原因 : {}", e);
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
    }

    @Override
    public void cancelled() {
        logger.info("异步调度取消");
    }

    @Override
    public void failed(Exception e) {
        logger.error("异步调度处理错误,错误原因 : {}", e);
    }

    private static JSONObject getReturnJsonObj(HttpResponse resp) throws IOException {
        if (resp.getStatusLine().getStatusCode() == 200) {
            //得到返回值
            String str = EntityUtils.toString(resp.getEntity());
            JSONObject jsonObjReturn = JSONObject.parseObject(str);
            //返回内容解密
            String dsecStr = SecretUtils.decrypt((String) jsonObjReturn.get("data"));
            JSONObject jsonObjData = JSONObject.parseObject(dsecStr);
            jsonObjReturn.put("data", jsonObjData);
            return jsonObjReturn;
        }
        return null;
    }

    private void judge(JSONObject jsonObject) throws ParseException {
        if (jsonObject != null) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null) {
                JSONObject object = data.getJSONObject(vin);
                //拿到数据为空,证明是E智，则返回false
                if (object != null) {
                    if (StringUtils.isNotBlank(object.get("10002").toString())) {
                        if (StringUtils.isNotBlank(object.get("2000").toString())) {
//                            北里时间
                            long time = DateUtil.stringToDate(object.get("2000").toString(), "").getTime();
                            logger.info("拿到北里数据 vin:{},time:{}",vin,object.get("2000"));
                            if (findHours(time)) {
                                //小于12小时,则是北里
                                logger.info("存储北里数据{} ",vin);
                                redisTool.zSetAdd(B_FLAG, vin, time);
                            } else {
                                // 需要新增一个判断条件,最后更新时间来判断,防止用户在12小时内没有开启盒子
                                List<VehicleOnlineStatus> vehicleOnlineStatuses = onlineService.selectOnline(vin);
                                if (vehicleOnlineStatuses != null && vehicleOnlineStatuses.size() > 0) {
                                    long eTime = vehicleOnlineStatuses.get(0).getUpdateTime().getTime();
                                    if (eTime > time) {
                                        //最后在E智上线的时间大于北里拿到的时间,且在12小时外,说明切回去了.
                                        // 又切回去的E智车辆
                                        logger.info("移除北里数据{} :",vin);
                                        redisTool.rmZset(B_FLAG, vin);
                                    }
                                }

                            }
                        }
                    }
                }
            }

        }

    }


    private boolean findHours(Long l) {
        //当前时间
        long now = System.currentTimeMillis();
        long difference = now - l;
        long hh = 1000 * 60 * 60;
        long l2 = difference / hh;
        return l2 < 12;
    }


}
