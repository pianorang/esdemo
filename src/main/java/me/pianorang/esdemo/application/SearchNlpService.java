package me.pianorang.esdemo.application;

import co.elastic.clients.elasticsearch._types.KnnQuery;
import co.elastic.clients.elasticsearch.core.search.FieldCollapse;
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

    public SearchNlpService(VodRepository vodRepository) {
        this.vodRepository = vodRepository;
    }

    public List<SearchNlpDto> search(SearchNlpRequest searchNlpRequest) {
        Query query = NativeQuery.builder()
                .withKnnQuery(KnnQuery.of(q->{
                    return q.queryVectorBuilder(b->{
                        return b.textEmbedding(te->{
                            te.modelId("ddobokki__electra-small-nli-sts");
                            te.modelText(searchNlpRequest.srchWord());
                            return te;
                        });
                    })
                    .field("synopsis_embedding")
                    .k(100)
                    .numCandidates(1000);
                }))
                .withFieldCollapse(FieldCollapse.of(c->c.field("contsId")))
                .build();

        //Query query = new StringQuery("{ \"match\": { \"title\": { \"query\": \""+searchNlpRequest.srchWord()+"\" } } } ");

        return vodRepository.searchNlp(query).stream()
                .map(SearchNlpDto::of)
                .collect(Collectors.toList());

    }
}
