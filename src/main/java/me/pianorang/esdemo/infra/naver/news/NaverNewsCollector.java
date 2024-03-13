package me.pianorang.esdemo.infra.naver.news;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NaverNewsCollector {

    final String clientId = "RuVRLqmrQQ_WreFYiu2T";
    final String secret = "zxMmDtpOOk";
    public void collect() {
        String url = "https://openapi.naver.com/v1/search/news.json?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=sim";
        RestTemplate restTemplate = new RestTemplateBuilder()
                .defaultHeader("X-Naver-Client-Id", clientId)
                .defaultHeader("X-Naver-Client-Secret", secret)
                .build();
        ResponseEntity<String> response = restTemplate.postForEntity(url, null,  String.class);

        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);

    }
}
