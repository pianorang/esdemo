package me.pianorang.esdemo.infra.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.KnnQuery;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.KnnSearchRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.knn_search.KnnSearchQuery;
import me.pianorang.esdemo.config.ElasticsearchProperties;
import me.pianorang.esdemo.domain.vod.Vod;
import me.pianorang.esdemo.domain.vod.VodRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Profile("es-client")
@Repository
public class EsClientVodRepository implements VodRepository {

    private final ElasticsearchClient esClient;
    private final ElasticsearchProperties elasticsearchProperties;

    public EsClientVodRepository(ElasticsearchClient esClient, ElasticsearchProperties elasticsearchProperties) {
        this.esClient = esClient;
        this.elasticsearchProperties = elasticsearchProperties;
    }

    @Override
    public Vod findById(String id) {
        try {
            GetResponse<Vod> vod = esClient.get(g -> g.index("vod").id(id), Vod.class);
            if (vod.found()) {
                return vod.source();
            }
            return null;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vod> searchNlp(String searchWord) {

        KnnQuery query = KnnQuery.of(k -> k.queryVectorBuilder(b -> b.textEmbedding(te -> te
                                .modelId(elasticsearchProperties.getMlModelId())
                                .modelText(searchWord)
                        ))
                        .field("synopsis_embedding")
                        .k(100)
                        .numCandidates(1000)
        );

        try {

            SearchResponse<Vod> search = esClient.search(SearchRequest.of(q ->
                    q.knn(query).collapse(c->c.field("contsId"))), Vod.class);

            if (search.hits().total().value() > 0) {
                return search.hits().hits().stream().map(h -> h.source()).toList();
            }

            return null;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
