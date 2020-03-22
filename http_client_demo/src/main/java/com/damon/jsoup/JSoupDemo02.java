package com.damon.jsoup;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * httpClient test
 */
public class JSoupDemo02 {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.cnblogs.com/");
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36");
        CloseableHttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String entityStr = EntityUtils.toString(entity, "utf-8");
//        System.out.println(entityStr);
        response.close();
        client.close();
        //-----------------------jsoup---------------------
        Document doc = Jsoup.parse(entityStr);  // 获取document对象

        Elements selectElements = doc.select("#post_list .post_item .post_item_body h3 a"); // 根据选择器获取dom元素
        for (Element ele : selectElements) {
            System.out.println(ele.text());
        }

        Elements hrefElements = doc.select("a[href]");// 获取带有href属性的dom元素
        for (Element ele : hrefElements) {
            System.out.println(ele.toString());
        }

        Elements imgElements = doc.select("img[src$=.png]");// 获取src以.png为结尾的dom元素
        for (Element ele : imgElements) {
            System.out.println(ele.toString());
        }

        Element title = doc.getElementsByTag("title").first();// 获取tag为title的第一个dom元素
        System.out.println(title.text());
    }
}
