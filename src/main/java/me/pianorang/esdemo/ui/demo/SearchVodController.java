package me.pianorang.esdemo.ui.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchVodController {
    @GetMapping("/search/vod")
    public String searchVod() {
        return "search/searchVod";
    }
}
