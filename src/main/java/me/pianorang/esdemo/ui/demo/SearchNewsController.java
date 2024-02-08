package me.pianorang.esdemo.ui.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchNewsController {
    @GetMapping("/search/news")
    public String searchNews() {
        NewsResponse newsResponse = new NewsResponse("title", "content", "author", "url", "publishedAt");

        return "search/searchNews";
    }
}
