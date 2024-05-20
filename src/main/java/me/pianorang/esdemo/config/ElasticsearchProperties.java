package me.pianorang.esdemo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ToString
@Getter
@Setter
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchProperties {
    private List<String> hosts;
    private boolean secure;
    private String caPath;
    private String username;
    private String password;
    private String mlModelId;
}
