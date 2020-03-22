package com.damon.httpclient;

import org.apache.http.HttpConnectionFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * httpClient test
 */
public class HttpClientDemo01 {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.tuicool.com");
        httpGet.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36");
        CloseableHttpResponse response = client.execute(httpGet);
        System.out.println("status:"+response.getStatusLine());
        System.out.println("statusCode:"+response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        System.out.println("content-type:"+entity.getContentType().getValue());
        String entityStr = EntityUtils.toString(entity, "utf-8");
//        System.out.println(entityStr);
        response.close();
        client.close();
    }
}
