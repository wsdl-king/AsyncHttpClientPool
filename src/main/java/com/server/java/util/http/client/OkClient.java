package com.server.java.util.http.client;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author qws
 * @note 还没用到
 * @since 2018年08月20日14:27:28
 */
public class OkClient {


    public OkHttpClient getHttpClient() {
        ConnectionPool pool = new ConnectionPool(5, 10, TimeUnit.MINUTES);
        OkHttpClient client = new OkHttpClient.Builder() //
                .connectTimeout(3, TimeUnit.MINUTES) //
                .followRedirects(true)
                .readTimeout(3, TimeUnit.MINUTES)
                .retryOnConnectionFailure(false)
                .writeTimeout(3, TimeUnit.MINUTES)
                .connectionPool(pool)
                .build();
        return client;
    }

    String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }


}