package me.pianorang.esdemo.infra.es;

import co.elastic.clients.elasticsearch._types.KnnQuery;
import co.elastic.clients.elasticsearch.core.search.FieldCollapse;
import me.pianorang.esdemo.application.SearchNlpDto;
import me.pianorang.esdemo.config.ElasticsearchProperties;
import me.pianorang.esdemo.domain.vod.Vod;
import me.pianorang.esdemo.domain.vod.VodRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Profile("spring-data")
@Repository
class SpringDataVodRepository implements VodRepository {

    private final ElasticsearchOperations operations;
    private final ElasticsearchProperties elasticsearchProperties;

    SpringDataVodRepository(ElasticsearchOperations operations, ElasticsearchProperties elasticsearchProperties) {
        this.operations = operations;
        this.elasticsearchProperties = elasticsearchProperties;
    }

    public List<Vod> searchNlp(String searchWord) {
        Query query = NativeQuery.builder()
                .withKnnQuery(KnnQuery.of(q->{
                    return q.queryVectorBuilder(b->{
                                return b.textEmbedding(te->{
                                    te.modelId(elasticsearchProperties.getMlModelId());
                                    te.modelText(searchWord);
                                    return te;
                                });
                            })
                            .field("synopsis_embedding")
                            .k(100)
                            .numCandidates(1000);
                }))
                .withFieldCollapse(FieldCollapse.of(c->c.field("contsId")))
                .build();

        SearchHits<Vod> hits = operations.search(query, Vod.class);
        return hits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public Vod findById(String id) {
        return operations.get(id, Vod.class);
    }
}
