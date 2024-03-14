package me.pianorang.esdemo.application;

import me.pianorang.esdemo.domain.vod.Vod;

public record SearchNlpDto(
        String title
) {
    public static SearchNlpDto of(Vod vod) {
        return new SearchNlpDto(vod.getTitle());
    }
}
