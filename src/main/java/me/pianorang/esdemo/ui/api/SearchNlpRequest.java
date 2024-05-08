package me.pianorang.esdemo.ui.api;

public record SearchNlpRequest(
        String srchWord
) {
    public static SearchNlpRequest of(String srchWord) {
        return new SearchNlpRequest(srchWord);
    }
}
