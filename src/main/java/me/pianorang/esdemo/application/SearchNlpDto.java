package me.pianorang.esdemo.application;

import me.pianorang.esdemo.domain.vod.Vod;

public record SearchNlpDto(
        String title,
        String refinedTitle,
        String synopsis
) {
    public static SearchNlpDto of(Vod vod) {
        return new SearchNlpDto(vod.getTitle(), vod.getRefinedTitle(), vod.getSynopsis());
    }
}
