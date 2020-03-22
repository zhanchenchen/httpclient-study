package com.damon.jsoup;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
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
public class JSoupDemo01 {
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
        Elements elements = doc.getElementsByTag("title");  // 获取tag为title的dom元素
        Element element = elements.get(0);
        System.out.println(element.text());

        Element element2 = doc.getElementById("site_nav_top");   // 获取id为site_nav_top的dom元素
        System.out.println(element2.text());

        Elements widthElements = doc.getElementsByClass("post_item");   // 获取class为post_item的dom元素
        for (Element ele : widthElements) {
            System.out.println(ele.html());
            System.out.println("------------------");
        }

        Elements attrElements = doc.getElementsByAttribute("width"); // 获取attibute为width的dom元素
        for (Element ele : attrElements) {
            System.out.println(ele.toString());
            System.out.println("------------------");
        }

        Elements attributeValueEelements = doc.getElementsByAttributeValue("target", "_blank"); // 获取指定attributeValue的dom元素
        for (Element ele : attributeValueEelements) {
            System.out.println(ele.toString());
            System.out.println("------------------");
        }

    }
}
