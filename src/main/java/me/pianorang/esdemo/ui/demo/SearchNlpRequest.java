package me.pianorang.esdemo.ui.demo;

public record SearchNlpRequest(
        String srchWord
) {
    public static SearchNlpRequest of(String srchWord) {
        return new SearchNlpRequest(srchWord);
    }
}
