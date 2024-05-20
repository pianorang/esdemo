package me.pianorang.esdemo.application;

import co.elastic.clients.elasticsearch._types.KnnQuery;
import co.elastic.clients.elasticsearch.core.search.FieldCollapse;
import me.pianorang.esdemo.config.ElasticsearchProperties;
import me.pianorang.esdemo.domain.vod.VodRepository;
import me.pianorang.esdemo.ui.api.SearchNlpRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchNlpService {
    private final VodRepository vodRepository;
    private final ElasticsearchProperties elasticsearchProperties;

    public SearchNlpService(VodRepository vodRepository, ElasticsearchProperties elasticsearchProperties) {
        this.vodRepository = vodRepository;
        this.elasticsearchProperties = elasticsearchProperties;
    }

    public List<SearchNlpDto> search(SearchNlpRequest searchNlpRequest) {
        return vodRepository.searchNlp(searchNlpRequest.srchWord()).stream()
                .map(SearchNlpDto::of)
                .collect(Collectors.toList());

    }
}
