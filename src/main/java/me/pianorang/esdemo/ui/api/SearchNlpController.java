package me.pianorang.esdemo.ui.api;

import lombok.extern.slf4j.Slf4j;
import me.pianorang.esdemo.application.SearchNlpDto;
import me.pianorang.esdemo.application.SearchNlpService;
import me.pianorang.esdemo.domain.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@Slf4j
@RestController
public class SearchNlpController {
    private final SearchNlpService searchNlpService;

    public SearchNlpController(SearchNlpService searchNlpService) {
        this.searchNlpService = searchNlpService;
    }



    @GetMapping("/search/vod")
    public ApiResponse<List<SearchNlpResponse>> searchVod(String searchWord) {
        log.info("search word q: {}", searchWord);
        List<SearchNlpDto> queryResult = searchNlpService.search(SearchNlpRequest.of(searchWord));
        return ApiResponse.ok(queryResult.stream().map(SearchNlpResponse::of).toList());
    }



}
