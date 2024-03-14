package me.pianorang.esdemo.ui.demo;

import me.pianorang.esdemo.application.SearchNlpDto;

public record SearchNlpResponse(
        String title
) {
    public static SearchNlpResponse of(SearchNlpDto searchNlpDto) {
        return new SearchNlpResponse(searchNlpDto.title());
    }
}
