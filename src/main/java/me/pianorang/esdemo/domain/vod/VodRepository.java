package me.pianorang.esdemo.domain.vod;

import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public interface VodRepository {
    public Vod findById(String id);

    List<Vod> searchNlp(String searchWord);
}
