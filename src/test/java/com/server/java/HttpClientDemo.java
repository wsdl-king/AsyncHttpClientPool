package com.server.java;

import com.server.java.util.redis.RedisTool;
import com.server.scala.AnalysisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * http client 使用
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnalysisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HttpClientDemo {

    @Autowired
    RedisTool redisTool;

    @Test
    public void getResult() {


    }


}