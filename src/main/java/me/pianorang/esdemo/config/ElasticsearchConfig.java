package me.pianorang.esdemo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

@Configuration
public class ElasticsearchConfig extends ElasticsearchConfiguration {
    private final ElasticsearchProperties elasticsearchProperties;


    public ElasticsearchConfig(ElasticsearchProperties elasticsearchProperties) {
        this.elasticsearchProperties = elasticsearchProperties;
    }

    //@Bean
    ElasticsearchClient elasticsearchClient() throws IOException {
        return elasticsearchProperties.isSecure() ? getEsClientWithSSL() : getEsClient();
    }

    private ElasticsearchClient getEsClient() {
        // Create the low-level client
        RestClient restClient = RestClient.builder(
                elasticsearchProperties.getHosts().stream().map(x->{
                    String[] spHost = x.split(":");
                    return new HttpHost(spHost[0], Integer.parseInt(spHost[1]));
                }).toArray(HttpHost[]::new)
        ).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);

        return client;
    }

    private ElasticsearchClient getEsClientWithSSL() throws IOException {
        File certFile = ResourceUtils.getFile(elasticsearchProperties.getCaPath());

        SSLContext sslContext = TransportUtils
                .sslContextFromHttpCaCrt(certFile);

        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials(elasticsearchProperties.getUsername(),
                        elasticsearchProperties.getPassword() )
        );

        RestClient restClient = RestClient
                .builder(
                        elasticsearchProperties.getHosts().stream().map(x->{
                            String[] spHost = x.split(":");
                            return new HttpHost(spHost[0], Integer.parseInt(spHost[1]), "https");
                        }).toArray(HttpHost[]::new)
                )
                .setHttpClientConfigCallback(hc -> hc
                        .setSSLContext(sslContext)
                        .setDefaultCredentialsProvider(credsProv)
                )
                .build();

        // Create the transport and the API client
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);
        return client;
    }


    @Override
    public ClientConfiguration clientConfiguration() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticsearchProperties.getHosts().toArray(new String[0]))
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(3))
                .build();
        return clientConfiguration;
    }
}
