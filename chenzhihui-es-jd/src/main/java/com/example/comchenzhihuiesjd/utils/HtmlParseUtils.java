package com.example.comchenzhihuiesjd.utils;

import com.example.comchenzhihuiesjd.pojo.Context;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 网页解析工具类
 * @Author: ChenZhiHui
 * @DateTime: 2023/5/16 15:06
 **/

@Component
public class HtmlParseUtils {

    public static void main(String[] args) throws IOException {
        new HtmlParseUtils().parseJD("java").forEach(System.out::println);
    }

    public List<Context> parseJD(String keyword) throws IOException {
        // 1、获取请求 https://search.jd.com/Search?keyword？
        String url = "https://search.jd.com/Search?keyword=" + keyword;

        // 2、解析网页 -> 若是中文的话，需要在这里进行转义处理
        Document document = Jsoup.parse(new URL(url), 30000);

        // 3、使用js技术
        Element element = document.getElementById("J_goodsList");

        // 4、获取所有的list元素
        Elements elements = element.getElementsByTag("li");

        List<Context> contexts = new ArrayList<>();

        // 获取元素中的内容
        for (Element el : elements) {
            // 图片需要特殊处理：延迟加载 source-data-lazy-img
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
//            String img2 = el.select("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            Context context = new Context();
            context.setImg(img);
            context.setPrice(price);
            context.setTitle(title);

            contexts.add(context);

        }
        return contexts;
    }
}
