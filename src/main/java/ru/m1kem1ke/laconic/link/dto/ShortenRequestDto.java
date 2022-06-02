package ru.m1kem1ke.laconic.link.dto;

public class ShortenRequestDto {
    private String url;

    public ShortenRequestDto() {}

    public ShortenRequestDto(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
