package com.uhuibao.util;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    private static final Logger LOG = Logger.getLogger(HttpUtils.class);

    public static String httpPost(String httpUrl, String httpJson, Map<String, String> headersMap) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url
                    .openConnection();
            if (null != headersMap) {
                for (Map.Entry<String, String> map : headersMap.entrySet()) {
                    httpURLConnection.setRequestProperty(map.getKey(), map.getValue());
                }
            } else {
                httpURLConnection.setRequestProperty("content-type", "text/xml");
                httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
                httpURLConnection.setRequestProperty("contentType", "UTF-8");
            }


            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(10 * 1000);
            httpURLConnection.setReadTimeout(60 * 1000);
            httpURLConnection.connect();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    httpURLConnection.getOutputStream(), "UTF-8"));
            out.write(httpJson);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream(), "UTF-8"));
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                } else {
                    result.append(line);
                }
            }

            LOG.info("http POST请求地址：" + httpUrl);
            LOG.info("http POST请求参数：" + httpJson);
            LOG.info("http POST请求结果：" + result);
        } catch (Exception e) {
            LOG.info("http POST请求地址：" + httpUrl);
            LOG.info("http POST请求参数：" + httpJson);
            LOG.info("http POST请求异常：", e);
        }
        return result.toString();
    }

    public static String httpPost(String url, List<NameValuePair> params) {
        String response = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse httpResponse = httpclient.execute(httppost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                response = EntityUtils.toString(httpResponse.getEntity());
            }
            httpclient.close();
        } catch (Exception e) {
            LOG.info("http POST请求异常：", e);
        }
        return response;
    }
}
