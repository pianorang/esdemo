package me.pianorang.esdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import java.time.Duration;

@Profile("spring-data")
@Configuration
public class ElasticsearchConfigSpringData extends ElasticsearchConfiguration {
    private final ElasticsearchProperties elasticsearchProperties;

    public ElasticsearchConfigSpringData(ElasticsearchProperties elasticsearchProperties) {
        this.elasticsearchProperties = elasticsearchProperties;
    }

    @Override
    public ClientConfiguration clientConfiguration() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticsearchProperties.getHosts().toArray(new String[0]))
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(10))
                .build();
        return clientConfiguration;
    }
}
