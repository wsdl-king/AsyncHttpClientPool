package com.server.java.util.http;

import com.server.java.util.http.factory.HttpClientFactory;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * http client 业务逻辑处理类
 */
public class HttpClientUtil {

    private static Logger LOG = LoggerFactory.getLogger(HttpClientUtil.class);

    private static String utf8Charset = "utf-8";

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /**
     * 向指定的url发送一次post请求,参数是List<NameValuePair>
     *
     * @param baseUrl 请求地址
     * @param list    请求参数,格式是List<NameValuePair>
     * @return 返回结果, 请求失败时返回null
     * @apiNote http接口处用 @RequestParam接收参数
     */
    public static String httpSyncPost(String baseUrl, List<BasicNameValuePair> list) {

        CloseableHttpClient httpClient = HttpClientFactory.getInstance().getHttpSyncClientPool().getHttpClient();
        HttpPost httpPost = new HttpPost(baseUrl);

        //Parameters
        LOG.warn("==== Parameters ======" + list);
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            response = httpClient.execute(httpPost);
            LOG.warn("========HttpResponseProxy：========" + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
                LOG.warn("========Response=======" + result);
            }
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 向指定的url发送一次post请求,参数是字符串
     *
     * @param baseUrl    请求地址
     * @param postString 请求参数,格式是json.toString()
     * @return 返回结果, 请求失败时返回null
     * @apiNote http接口处用 @RequestBody接收参数
     */
    public static String httpSyncPost(String baseUrl, String postString) {

        CloseableHttpClient httpClient = HttpClientFactory.getInstance().getHttpSyncClientPool().getHttpClient();
        HttpPost httpPost = new HttpPost(baseUrl);
        //parameters
        LOG.warn("==== Parameters ======" + postString);
        CloseableHttpResponse response = null;
        try {
            if (postString == null || "".equals(postString)) {
                throw new Exception("missing post String");
            }

            StringEntity stringEntity = new StringEntity(postString.toString(), utf8Charset);
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

            response = httpClient.execute(httpPost);
            LOG.warn("========HttpResponseProxy：========" + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
                LOG.warn("========Response=======" + result);
            }
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 向指定的url发送一次get请求,参数是List<NameValuePair>
     *
     * @param baseUrl 请求地址
     * @param list    请求参数,格式是List<NameValuePair>
     * @return 返回结果, 请求失败时返回null
     * @apiNote http接口处用 @RequestParam接收参数
     */
    public static String httpSyncGet(String baseUrl, List<BasicNameValuePair> list) {

        CloseableHttpClient httpClient = HttpClientFactory.getInstance().getHttpSyncClientPool().getHttpClient();
        HttpGet httpGet = new HttpGet(baseUrl);
        //Parameters
        LOG.warn("==== Parameters ======" + list);
        CloseableHttpResponse response = null;
        try {

            if (list != null) {
                String getUrl = EntityUtils
                        .toString(new UrlEncodedFormEntity(list));
                httpGet.setURI(new URI(httpGet.getURI().toString()
                        + "?" + getUrl));
            } else {
                httpGet.setURI(new URI(httpGet.getURI().toString()));
            }

            response = httpClient.execute(httpGet);
            LOG.warn("========HttpResponseProxy：========" + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
                LOG.warn("========Response=======" + result);
            }
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 向指定的url发送一次get请求,参数是字符串
     *
     * @param baseUrl   请求地址
     * @param urlParams 请求参数,格式是String
     * @return 返回结果, 请求失败时返回null
     * @apiNote http接口处用 @RequestParam接收参数
     */
    public static String httpSyncGet(String baseUrl, String urlParams) {

        CloseableHttpClient httpClient = HttpClientFactory.getInstance().getHttpSyncClientPool().getHttpClient();
        HttpGet httpGet = new HttpGet(baseUrl);
        //Parameters
        LOG.warn("==== Parameters ======" + urlParams);
        CloseableHttpResponse response = null;
        try {

            if (null != urlParams || "".equals(urlParams)) {
                httpGet.setURI(new URI(httpGet.getURI().toString()
                        + "?" + urlParams));
            } else {
                httpGet.setURI(new URI(httpGet.getURI().toString()));
            }

            response = httpClient.execute(httpGet);
            LOG.warn("========HttpResponseProxy：========" + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
                LOG.warn("========Response=======" + result);
            }
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 向指定的url发送一次get请求,参数是字符串
     *
     * @param baseUrl 请求地址
     * @return 返回结果, 请求失败时返回null
     * @apiNote http接口处用 @RequestParam接收参数
     */
    public static String httpSyncGet(String baseUrl) {

        CloseableHttpClient httpClient = HttpClientFactory.getInstance().getHttpSyncClientPool().getHttpClient();
        HttpGet httpGet = new HttpGet(baseUrl);

        CloseableHttpResponse response = null;
        try {
            httpGet.setURI(new URI(httpGet.getURI().toString()));
            response = httpClient.execute(httpGet);
            LOG.warn("========HttpResponseProxy：========" + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
                LOG.warn("========Response=======" + result);
            }
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 向指定的url发送一次异步post请求,参数是字符串
     *
     * @param baseUrl    请求地址
     * @param postString 请求参数,格式是json.toString()
     * @param urlParams  请求参数,格式是String
     * @param callback   回调方法,格式是FutureCallback
     * @return 返回结果, 请求失败时返回null
     * @apiNote http接口处用 @RequestParam接收参数
     */
    public static void httpAsyncPost(String baseUrl, Map<String, String> headers, String postString,
                                     String urlParams, FutureCallback callback) throws Exception {
        if (baseUrl == null || "".equals(baseUrl)) {
            LOG.warn("we don't have base url, check config");
            throw new Exception("missing base url");
        }
        CloseableHttpAsyncClient hc = HttpClientFactory.getInstance().getHttpAsyncClientPool().getAsyncHttpClient();
        try {
            hc.start();
            HttpPost httpPost = new HttpPost(baseUrl);
            if (null != headers) {
                for (String header : headers.keySet()) {
                    httpPost.setHeader(header, headers.get(header));
                }
            }
            if (null != postString) {
                LOG.debug("exeAsyncReq post postBody={}", postString);
                StringEntity entity = new StringEntity(postString, utf8Charset);
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }

            if (null != urlParams) {

                httpPost.setURI(new URI(httpPost.getURI().toString() + "?" + urlParams));
            }

//            LOG.warn("exeAsyncReq url=:" + httpPost.getURI() + "reqJson" + postString);

            hc.execute(httpPost, callback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 向指定的url发送一次异步post请求,参数是字符串
     *
     * @param baseUrl   请求地址
     * @param urlParams 请求参数,格式是List<BasicNameValuePair>
     * @param callback  回调方法,格式是FutureCallback
     * @return 返回结果, 请求失败时返回null
     * @apiNote http接口处用 @RequestParam接收参数
     */
    public static void httpAsyncPost(String baseUrl, Map<String, String> headers, List<BasicNameValuePair> postBody,
                                     List<BasicNameValuePair> urlParams, FutureCallback callback) throws Exception {
        if (baseUrl == null || "".equals(baseUrl)) {
            LOG.warn("we don't have base url, check config");
            throw new Exception("missing base url");
        }
        try {
            CloseableHttpAsyncClient hc = HttpClientFactory.getInstance().getHttpAsyncClientPool().getAsyncHttpClient();
            hc.start();
            HttpPost httpPost = new HttpPost(baseUrl);
            if (null != headers) {
                for (String header : headers.keySet()) {
                    httpPost.setHeader(header, headers.get(header));
                }
            }
            if (null != postBody) {
                LOG.debug("exeAsyncReq post postBody={}", postBody);
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                        postBody, "UTF-8");
                httpPost.setEntity(entity);
            }
            if (null != urlParams) {
                String getUrl = EntityUtils.toString(new UrlEncodedFormEntity(urlParams));
                httpPost.setURI(new URI(httpPost.getURI().toString() + "?" + getUrl));
            }

            LOG.warn("exeAsyncReq getparams:" + httpPost.getURI());
            hc.execute(httpPost, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向指定的url发送一次异步get请求,参数是String
     *
     * @param baseUrl   请求地址
     * @param urlParams 请求参数,格式是String
     * @param callback  回调方法,格式是FutureCallback
     * @return 返回结果, 请求失败时返回null
     * @apiNote http接口处用 @RequestParam接收参数
     */
    public static void httpAsyncGet(String baseUrl, String urlParams, FutureCallback callback) throws Exception {

        if (baseUrl == null || "".equals(baseUrl)) {
            LOG.warn("we don't have base url, check config");
            throw new Exception("missing base url");
        }
        CloseableHttpAsyncClient hc = HttpClientFactory.getInstance().getHttpAsyncClientPool()
                .getAsyncHttpClient();
        try {
            hc.start();
            HttpGet httpGet = new HttpGet(baseUrl);
            if (null != urlParams || "".equals(urlParams)) {

                httpGet.setURI(new URI(httpGet.getURI().toString()
                        + "?" + urlParams));
            } else {
                httpGet.setURI(new URI(httpGet.getURI().toString()));
            }

            LOG.warn("exeAsyncReq getparams:" + httpGet.getURI());


            hc.execute(httpGet, callback);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 向指定的url发送一次异步get请求,参数是List<BasicNameValuePair>
     *
     * @param baseUrl   请求地址
     * @param urlParams 请求参数,格式是List<BasicNameValuePair>
     * @param callback  回调方法,格式是FutureCallback
     * @return 返回结果, 请求失败时返回null
     * @apiNote http接口处用 @RequestParam接收参数
     */
    public static void httpAsyncGet(String baseUrl, String port, Map<String, String> headers, List<BasicNameValuePair> urlParams, FutureCallback callback) throws Exception {
        if (baseUrl == null || "".equals(baseUrl)) {
            LOG.warn("we don't have base url, check config");
            throw new Exception("missing base url");
        }

        try {
            CloseableHttpAsyncClient hc = HttpClientFactory.getInstance().getHttpAsyncClientPool().getAsyncHttpClient();
            hc.start();
            HttpPost httpGet = new HttpPost(baseUrl);
            if (null != headers) {
                for (String header : headers.keySet()) {
                    httpGet.setHeader(header, headers.get(header));
                }
            }
//            httpGet.setHeader("Connection","close");

            if (null != urlParams) {

                String getUrl = EntityUtils.toString(new UrlEncodedFormEntity(urlParams));

                httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + getUrl));
            }

            LOG.warn("exeAsyncReq getparams:" + httpGet.getURI());
            hc.execute(httpGet, callback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String OkSyncPost(String url, String json) throws IOException {

        OkHttpClient okClient = HttpClientFactory.getInstance().getOkClientPool().getHttpClient();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = okClient.newCall(request).execute()) {

            return response.body().string();
        }
    }

    public static void OkAsyncPost(String url, String json) throws IOException {
        OkHttpClient okClient = HttpClientFactory.getInstance().getOkClientPool().getHttpClient();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = okClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                LOG.warn("OkAsyncPost回调:" + response.body().string());
            }
        });

    }


    public static void OkAsyncPost(String url, Map<String, String> map) throws IOException {
        OkHttpClient okClient = HttpClientFactory.getInstance().getOkClientPool().getHttpClient();

        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBodyBuilder.build())
                .build();
        Call call = okClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                LOG.warn("OkAsyncPost回调:" + response.body().string());
            }
        });

    }

    public static void OkAsyncPost(String url, Map<String, String> map, Callback callback) throws IOException {
        OkHttpClient okClient = HttpClientFactory.getInstance().getOkClientPool().getHttpClient();

        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        }

        Request request = new Request.Builder()
                .url(url)
                .post(formBodyBuilder.build())
                .build();
        Call call = okClient.newCall(request);
        call.enqueue(callback);

    }

    public static String OkSyncGet(String url) throws IOException {

        OkHttpClient okClient = HttpClientFactory.getInstance().getOkClientPool().getHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = okClient.newCall(request).execute()) {

            return response.body().string();
        }
    }

    public static void OkAsyncGet(String url) throws IOException {

        OkHttpClient okClient = HttpClientFactory.getInstance().getOkClientPool().getHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                LOG.warn("OkAsyncGet回调:" + response.body().string());
            }
        });
    }

}