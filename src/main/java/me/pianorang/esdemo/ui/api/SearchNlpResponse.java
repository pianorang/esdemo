package me.pianorang.esdemo.ui.api;

import me.pianorang.esdemo.application.SearchNlpDto;

public record SearchNlpResponse(
        String title,
        String refinedTitle,
        String synopsis
) {
    public static SearchNlpResponse of(SearchNlpDto searchNlpDto) {
        return new SearchNlpResponse(searchNlpDto.title(), searchNlpDto.refinedTitle(), searchNlpDto.synopsis());
    }
}
