package me.pianorang.esdemo.ui.demo;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchVodController {
    private final ElasticsearchOperations elasticsearchOperations;

    public SearchVodController(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @ResponseBody
    @GetMapping("/search/vod")
    public EsVod searchVod() {


        EsVod vod = elasticsearchOperations.get("C_CV000000000055561289_MIAN50YXSGL150000100", EsVod.class);
        return vod;
    }


    @Document(indexName = "idx_vod")
    public static record EsVod(String title, String categoryNm, String seriesName) {
    }
}
