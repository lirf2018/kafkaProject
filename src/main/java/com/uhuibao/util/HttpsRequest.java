package com.uhuibao.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

/**
 * 创建人: lirf
 * 创建时间:  2018-04-03 16:15
 * 功能介绍:
 */
public class HttpsRequest {

    private static final Logger log = Logger.getLogger(HttpsRequest.class);
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";


    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;

    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new org.apache.http.conn.ssl.TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);//max connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * // 全部信任 不做身份鉴定
     * httpclient4.5 https请求 忽略身份验证
     *
     * @param url
     * @param jsonParam
     * @return
     */
    public static String httpsPost1(String url, String jsonParam) {
        log.info("开始访问：" + url);
        log.info("传入参数：" + jsonParam);
        SSLContext sslContext = null;
        String result = "";
        CloseableHttpClient httpClient = null;
        try {
            httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50 * 1000).setConnectTimeout(10 * 1000).build();
            httpPost.setConfig(requestConfig);

            // 设置头信息
            Header[] headers = new Header[1];
            headers[0] = new BasicHeader("Content-type", CONTENT_TYPE);
            httpPost.setHeaders(headers);

            // 设置请求参数
            HttpEntity entity = new StringEntity(jsonParam, Charset.forName("UTF-8"));
            httpPost.setEntity(entity);


            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = httpResponse.getEntity();
                result = EntityUtils.toString(resEntity);
            } else {
                result = readHttpResponse(httpResponse);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String httpsPost(String url, String jsonParam, Map<String, String> headersMap) {
        log.info("--->开始访问：" + url);
        log.info("-->传入参数：" + jsonParam);
        System.out.println(jsonParam);
        SSLContext sslContext = null;

        try {
            TrustManager manager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }
            };
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{manager}, null);
            // 忽略hostname验证
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                    sslContext, NoopHostnameVerifier.INSTANCE);

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", socketFactory).build();

            HttpClientBuilder builder = HttpClientBuilder.create();
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
            connectionManager.setDefaultMaxPerRoute(20);
            connectionManager.setMaxTotal(200);

            builder.setConnectionManager(connectionManager);

            HttpClient client = builder.build();

            HttpPost post = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50 * 1000).setConnectTimeout(10 * 1000).build();
            post.setConfig(requestConfig);
            if (null != headersMap) {
                for (Map.Entry<String, String> map : headersMap.entrySet()) {
                    post.addHeader(map.getKey(), map.getValue());
                }
            }

            HttpEntity entity = new StringEntity(jsonParam, Charset.forName("UTF-8"));
            post.setEntity(entity);

            HttpResponse res = client.execute(post);
//            HttpEntity en = res.getEntity();

            int statusCode = res.getStatusLine().getStatusCode();

            String result = "";
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = res.getEntity();
                result = EntityUtils.toString(resEntity);
            } else {
                result = readHttpResponse(res);
            }
            log.info("返回结果：" + result);
            return result;
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String httpsPostNameValuePair(String url, List<NameValuePair> paramsList, Map<String, String> headersMap) {
        log.info("--->开始访问：" + url);
        if (null != paramsList) {
            log.info("-->传入参数：" + JSONObject.toJSONString(paramsList));
        }
        System.out.println("--->开始访问：" + url);
        System.out.println("-->传入参数：" + JSONObject.toJSONString(paramsList));

        SSLContext sslContext = null;

        try {
            TrustManager manager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }
            };
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{manager}, null);
            // 忽略hostname验证
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                    sslContext, NoopHostnameVerifier.INSTANCE);

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", socketFactory).build();

            HttpClientBuilder builder = HttpClientBuilder.create();
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
            connectionManager.setDefaultMaxPerRoute(20);
            connectionManager.setMaxTotal(200);

            builder.setConnectionManager(connectionManager);

            HttpClient client = builder.build();

            HttpPost post = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50 * 1000).setConnectTimeout(10 * 1000).build();
            post.setConfig(requestConfig);
            if (null != headersMap) {
                for (Map.Entry<String, String> map : headersMap.entrySet()) {
                    post.addHeader(map.getKey(), map.getValue());
                }
            }

            if (null != paramsList) {
                post.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
            }

            HttpResponse res = client.execute(post);
            int statusCode = res.getStatusLine().getStatusCode();

            String result = "";
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = res.getEntity();
                result = EntityUtils.toString(resEntity);
            } else {
                result = readHttpResponse(res);
            }
            log.info("返回结果：" + result);
            return result;
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String httpsGetNameValuePair(String url, List<NameValuePair> paramsList, Map<String, String> headersMap) {
        log.info("--->开始访问：" + url);
        log.info("-->传入参数：" + paramsList);
        SSLContext sslContext = null;

        try {
            TrustManager manager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }
            };
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{manager}, null);
            // 忽略hostname验证
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                    sslContext, NoopHostnameVerifier.INSTANCE);

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", socketFactory).build();

            HttpClientBuilder builder = HttpClientBuilder.create();
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
            connectionManager.setDefaultMaxPerRoute(20);
            connectionManager.setMaxTotal(200);

            builder.setConnectionManager(connectionManager);

            CloseableHttpClient client = builder.build();
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50 * 1000).setConnectTimeout(10 * 1000).build();
            httpGet.setConfig(requestConfig);

            if (null != headersMap) {
                for (Map.Entry<String, String> map : headersMap.entrySet()) {
                    httpGet.addHeader(map.getKey(), map.getValue());
                }
            }

            CloseableHttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            String entityStr = EntityUtils.toString(entity, "UTF-8");
            log.info("返回结果：" + entityStr);
            return entityStr;
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String httpsGet(String url, String token) {

        // 获取连接客户端工具
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String entityStr = null;
        CloseableHttpResponse response = null;

        try {
            /*
             * 由于GET请求的参数都是拼装在URL地址后方，所以我们要构建一个URL，带参数
             */
            URIBuilder uriBuilder = new URIBuilder(url);

            // 根据带参数的URI对象构建GET请求对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());

            /*
             * 添加请求头信息
             */
            // 浏览器表示
            httpGet.addHeader("Accept", "application/json");
            // 传输的类型
            httpGet.addHeader("Authorization", "Bearer " + token);

            // 执行请求
            response = httpClient.execute(httpGet);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
        } catch (ClientProtocolException e) {
            System.err.println("Http协议出现问题");
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("解析错误");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            System.err.println("URI解析异常");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO异常");
            e.printStackTrace();
        } finally {
            // 释放连接
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    System.err.println("释放连接出错");
                    e.printStackTrace();
                }
            }
        }

        // 打印响应内容
        System.out.println(entityStr);
        return entityStr;
    }


    public static CloseableHttpClient getHttpClient() throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .build();
        return httpClient;
    }

    public static String readHttpResponse(HttpResponse httpResponse)
            throws ParseException, IOException {
        StringBuilder builder = new StringBuilder();
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        builder.append("status:" + httpResponse.getStatusLine());
        builder.append("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            builder.append("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            builder.append("response length:" + responseString.length());
            builder.append("response content:" + responseString.replace("\r\n", ""));
        }
        return builder.toString();
    }
}
