package com.dmc;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.net.Proxy;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

@Slf4j
public class HttpAPI {

    private HttpAPI() {
    }

    private static final Integer CONNECT_TIMEOUT = 60000;
    private static final Integer CONNECTION_REQUEST_TIMEOUT = 60000;
    private static final Integer SOCKET_TIMEOUT = 60000;
    private static final Integer SO_TIMEOUT = 60000;
    private static final Integer MAX_CONN_TOTAL = 200;
    private static final Integer MAX_CONN_PER_ROUTE = 100;

    /**
     * 尝试重试次数
     */
    public static final Integer RETRY_TIME = 3;

    private static CloseableHttpClient httpClient = null;

    static {
        RequestConfig config = RequestConfig.custom()
            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build();

        SocketConfig socketConfig = SocketConfig.custom()
            .setTcpNoDelay(true)
            .setSoTimeout(SO_TIMEOUT)
            .build();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setDefaultSocketConfig(socketConfig);
        connManager.setMaxTotal(MAX_CONN_TOTAL);
        connManager.setDefaultMaxPerRoute(MAX_CONN_PER_ROUTE);
        connManager.setValidateAfterInactivity(1000);

        httpClient = HttpClientBuilder.create()
            .setDefaultRequestConfig(config)
            .setConnectionManager(connManager)
            .build();
    }

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     */
    public static HttpResult doGet(String url) throws Exception {
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        try {
            // 声明 http get 请求
            httpGet = new HttpGet(url);

            // 发起请求
            response = httpClient.execute(httpGet);

            // 判断状态码是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回响应体的内容
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                    response.getEntity(), "UTF-8"));
            }
            return null;
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
                response.close();
            }
            if (null != httpGet) {
                httpGet.releaseConnection();
            }
        }
    }

    public static HttpResult doGet(String url, Map<String, Object> headers, Map<String, Object> params) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (params != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (null != entry.getKey() && null != entry.getValue()) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
                }
            }
        }

        // 调用不带参数的get请求
        return doGet(uriBuilder.build().toString(), headers);
    }

    public static HttpResult doGet(String url, Map<String, Object> headers) throws Exception {
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        try {
            // 声明 http get 请求
            httpGet = new HttpGet(url);
            if (null != headers) {
                for (Map.Entry<String, Object> entry : headers.entrySet()) {
                    if (null != entry.getKey() && null != entry.getValue()) {
                        httpGet.setHeader(entry.getKey(), entry.getValue().toString());
                    }
                }
            }
            // 发起请求
            response = httpClient.execute(httpGet);

            // 判断状态码是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回响应体的内容
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                    response.getEntity(), "UTF-8"));
            }
            return null;
        } finally {
            if (null != response) {
                EntityUtils.consume(response.getEntity());
                response.close();
            }
            if (null != httpGet) {
                httpGet.releaseConnection();
            }
        }
    }

    /**
     * 带参数的post请求
     */
    public static HttpResult doPost(String url, Map<String, Object> params) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        return doPost(httpPost, params);
    }

    private static HttpResult doPost(HttpPost httpPost, Map<String, Object> params) throws Exception {
        httpPost.setProtocolVersion(HttpVersion.HTTP_1_1);
        httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        CloseableHttpResponse response = null;
        try {
            // 判断map是否为空，不为空则进行遍历，封装from表单对象
            if (params != null) {
                List<NameValuePair> list = Lists.newArrayList();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    if (null != entry.getKey() && null != entry.getValue()) {
                        list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                    }
                }
                // 构造from表单对象
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

                // 把表单放到post里
                httpPost.setEntity(urlEncodedFormEntity);
            }

            // 发起请求
            response = httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
                response.close();
            }
            httpPost.releaseConnection();
        }
    }

    /**
     * 带HttpEntity类型参数的post请求
     */
    public static HttpResult doPost(String url, Map<String, Object> headers, HttpEntity httpEntity) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(httpEntity);
        if (null != headers) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue().toString());
            }
        }
        return doPost(httpPost, null);
    }

    /**
     * 带参数的post请求
     */
    public static HttpResult doPost(String url, Map<String, Object> headers, Map<String, Object> params) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        if (null != headers) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                if (null != entry.getKey() && null != entry.getValue()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        return doPost(httpPost, params);
    }

    public static HttpResult parseHttpResult(String url, Map<String, Object> params) {
        try {
            return HttpAPI.doPost(url, params);
        } catch (Exception e) {
            log.error("failed to send retrieve all orders request url:{} with params:{}", url, params, e);
            return null;
        }
    }

    public static HttpResult parseHttpResult(String url, Map<String, Object> headers, Map<String, Object> params) {
        try {
            return HttpAPI.doGet(url, headers, params);
        } catch (Exception e) {
            log.error("failed to send retrieve all orders request url:{}, headers:{} with params:{}", url, headers, params, e);
            return null;
        }
    }

    public static HttpResult doGetWithProxy(String url, String proxyHostname, Integer port) throws Exception{
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        try {
            // 声明 http get 请求
            httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(new HttpHost(proxyHostname, port))
                .build();
            httpGet.setConfig(requestConfig);

            // 发起请求
            response = httpClient.execute(httpGet);

            // 判断状态码是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回响应体的内容
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                    response.getEntity(), "UTF-8"));
            }
            return null;
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
                response.close();
            }
            if (null != httpGet) {
                httpGet.releaseConnection();
            }
        }
    }

    public static HttpResult doPostWithProxy(String url, String proxyHostname, Integer port) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.custom().setProxy(new HttpHost(proxyHostname, port)).build());
        return doPost(httpPost, Maps.newHashMap());
    }

    public static void main(String[] args) {
//        '0d','1H','1v';
        System.out.println('z' - 'd' + 'H'-'0');
        System.out.println('v' - 'H');
    }

}
