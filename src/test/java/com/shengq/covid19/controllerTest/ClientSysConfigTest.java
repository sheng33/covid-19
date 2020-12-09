package com.shengq.covid19.controllerTest;

import com.github.kevinsawicki.http.HttpRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClientSysConfigTest {
    static String cookies = "";
    @BeforeAll
    public static void initialize(){
        CharSequence url = "http://localhost:8080/admin/login?username=admin1&password=123";
        HttpRequest httpRequest = HttpRequest.post(url);
        String body = httpRequest.body();
        String cookie = httpRequest.headers().get("Set-Cookie").get(0).substring(11,43);
        System.out.println("cookie：：："+cookie);
        cookies = cookie;
    }
    @Test
    public void addMenu(){
        String menuName = "test1";
        String menuUrl = "/info";
        int status = 0;
        int auth = 0;
        String imgUrl = "http://www.baidu.com";
        String imgName = "imgName1";
        String sendJson = "{menuName:\""+menuName+"\",menuUrl:\""+menuUrl+"\"," +
                "status:\""+status+"\",auth:\""+auth+"\",imgUrl:\""+imgUrl+"\"," +
                "imgName:\""+imgName+"\"}";
        System.out.println("sendJson:"+sendJson);

        String url = "http://localhost:8080/clientsysConfig/addMenu";
        HttpRequest httpRequest = HttpRequest.post(url);
        httpRequest.header("cookie",cookies);
        httpRequest.send(sendJson);
        System.out.println(httpRequest.body());
    }

}
