package me.pianorang.esdemo.domain.vod;

import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Vod
 *
 */
@Getter
@Document(indexName = "idx_vod")
public class Vod {
    private String title;


}
