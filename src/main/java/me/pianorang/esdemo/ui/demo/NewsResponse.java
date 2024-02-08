package me.pianorang.esdemo.ui.demo;

import jakarta.validation.constraints.NotNull;

public record NewsResponse(
    String title,
    String content,
    String author,
    String url,
    String publishedAt
) {

}
