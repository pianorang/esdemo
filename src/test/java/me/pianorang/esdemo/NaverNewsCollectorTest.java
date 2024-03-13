package me.pianorang.esdemo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NaverNewsCollectorTest {
    final String clientId = "RuVRLqmrQQ_WreFYiu2T";
    final String secret = "zxMmDtpOOk";
    @Test
    public void collect() {
        String url = "https://openapi.naver.com/v1/search/news.json?query=윤석열&display=10&start=1&sort=sim";
        RestTemplate restTemplate = new RestTemplateBuilder()
                .defaultHeader("X-Naver-Client-Id", clientId)
                .defaultHeader("X-Naver-Client-Secret", secret)
                .build();

        //ResponseEntity<String> response = restTemplate.getForEntity(url, null,  String.class);
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);




    }
}
