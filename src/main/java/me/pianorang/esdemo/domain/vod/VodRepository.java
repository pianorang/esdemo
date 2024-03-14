package me.pianorang.esdemo.domain.vod;

import me.pianorang.esdemo.ui.demo.SearchNlpRequest;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface VodRepository {
    public Vod findById(String id);

    List<Vod> searchNlp(Query query);
}
