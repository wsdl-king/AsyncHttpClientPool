package com.server.java.dispatch;

import com.server.java.jmev.queue.JmevQueue;
import com.server.java.util.redis.RedisTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * @author qiwenshuai
 * @note
 * @since 18-8-19 09:52 by jdk 1.8
 */
@Component//被spring容器管理
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序，越小优先级越高
public class JobManager implements ApplicationRunner {
    //此标识用于判断车辆所属于的车辆--北里平台;
    private static final String B_FLAG = "jmev_b_flag{inter}";
    //此标识用于判断车辆所属于的车辆--e智;
    private static final String E_FLAG = "jmev_e_flag{inter}";

    @Autowired
    RedisTool redisTool;
    private static Logger logger = LoggerFactory.getLogger(JobManager.class);


    @Override
    public void run(ApplicationArguments applicationArguments) {
        logger.info("-------------->" + "项目启动,开始循环调度，now=" + new Date());
        logger.info("----------------清除现有的基础数据----------------");
        redisTool.del(E_FLAG);
        redisTool.del(B_FLAG);
        logger.info("----------------读取外部list数据----------------------");
        List<String> list = readTxt();
        logger.info("----------------读取外部list数据成功,放入队列----------------------");
        logger.info("----------------重新存储的基础数据----------------");
        for (String vin : list) {
            redisTool.zSetAdd(E_FLAG, vin, 0L);
            JmevQueue.getInstance().add(vin);
        }
        logger.info("----------------重新存储的E智平台的基础数据成功----------------");
        logger.info("----------------读取外部数据成功,大小为" + JmevQueue.getInstance().getQueue().size() + "----------------");
        logger.info("----------------开启线程进行轮循----------------");
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new JobRunner());
            thread.start();
        }

    }


    private static List<String> readTxt() {
        List<String> list = new LinkedList<>();
        try {
            InputStream resourceAsStream = JobManager.class.getResourceAsStream("/datas/upgrade-vin-datas.txt");
            String lineinfo = "";
            InputStreamReader read = new InputStreamReader(resourceAsStream);
            BufferedReader bufferReader = new BufferedReader(read);
            while ((lineinfo = bufferReader.readLine()) != null) {
//                StringTokenizer stk = new StringTokenizer(lineinfo, ",");//被读取的文件的字段以","分隔
//                String[] strArrty = new String[stk.countTokens()];
//                int i = 0;
//                while (stk.hasMoreTokens()) {
//                    strArrty[i++] = stk.nextToken();
//                }
                list.add(lineinfo);
            }
            read.close();
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;

    }

    public static void main(String[] args) {

    }

}
