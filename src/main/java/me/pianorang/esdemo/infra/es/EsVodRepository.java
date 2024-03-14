package me.pianorang.esdemo.infra.es;

import me.pianorang.esdemo.domain.vod.Vod;
import me.pianorang.esdemo.domain.vod.VodRepository;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
class EsVodRepository implements VodRepository {

    private final ElasticsearchOperations operations;

    EsVodRepository(ElasticsearchOperations operations) {
        this.operations = operations;
    }

    public List<Vod> searchNlp(Query query) {
        SearchHits<Vod> hits = operations.search(query, Vod.class);
        return hits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public Vod findById(String id) {
        return operations.get(id, Vod.class);
    }
}
