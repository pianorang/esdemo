package me.pianorang.esdemo.domain.vod;

import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Vod
 *
 */
@Getter
@Document(indexName = "vod-all-20240507-ddobokki-2")
public class Vod {
    private String title;
    private String refinedTitle;
    private String synopsis;

}
