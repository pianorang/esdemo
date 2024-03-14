package me.pianorang.esdemo.infra.naver;

import org.springframework.beans.factory.annotation.Value;

public class NaverNewsCollector {
    @Value("naver.client.id")
    String clientId;
    @Value("naver.client.secret")
    String clientSecret;

    @Value("naver.news.url")
    String url;

    public void collect() {

    }




}
