package com.server.java.util.http.factory;


import com.server.java.util.http.client.HttpAsyncClient;
import com.server.java.util.http.client.HttpSyncClient;
import com.server.java.util.http.client.OkClient;

/**
 *
 * httpclient 工厂类
 * */
public class HttpClientFactory {

    private static HttpAsyncClient httpAsyncClient = new HttpAsyncClient();

    private static HttpSyncClient httpSyncClient = new HttpSyncClient();

    private static OkClient okClient = new OkClient();

    private HttpClientFactory() {
    }

    private static HttpClientFactory httpClientFactory = new HttpClientFactory();

    public static HttpClientFactory getInstance() {

        return httpClientFactory;

    }

    public HttpAsyncClient getHttpAsyncClientPool() {
        return httpAsyncClient;
    }

    public HttpSyncClient getHttpSyncClientPool() {
        return httpSyncClient;
    }

    public OkClient getOkClientPool() {
        return okClient;
    }

}