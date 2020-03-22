package com.damon.httpclient;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * httpClient test
 */
public class HttpClientDemo03 {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://img2018.cnblogs.com/blog/524341/202002/524341-20200220180853616-342145893.png");
        CloseableHttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null){
            System.out.println("图片类型:"+entity.getContentType());
            InputStream content = entity.getContent();  // 获得输入流
            FileUtils.copyToFile(content,new File("d:/xx.png"));   // 拷贝图片至指定目录
        }
        response.close();
        client.close();
    }
}
