package me.pianorang.esdemo.ui.demo;

import lombok.extern.slf4j.Slf4j;
import me.pianorang.esdemo.application.SearchNlpDto;
import me.pianorang.esdemo.application.SearchNlpService;
import me.pianorang.esdemo.domain.ApiResponse;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class SearchNlpController {
    private final SearchNlpService searchNlpService;

    public SearchNlpController(SearchNlpService searchNlpService) {
        this.searchNlpService = searchNlpService;
    }


    @ResponseBody
    @GetMapping("/search/vod")
    public ApiResponse<List<SearchNlpResponse>> searchVod(String q) {
        log.info("search word q: {}", q);
        List<SearchNlpDto> queryResult = searchNlpService.search(SearchNlpRequest.of(q));
        return ApiResponse.ok(queryResult.stream().map(SearchNlpResponse::of).toList());
    }



}
