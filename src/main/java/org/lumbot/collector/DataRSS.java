package org.lumbot.collector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DataRSS {

    public static final DateTimeFormatter dateAdapt = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH/mm/ss");
    private String title;
    private LocalDateTime pubDate;
    private String media;
    private String url;

    public DataRSS() {
    }

    public DataRSS(String title, LocalDateTime pubDate, String media, String url) {
        this.title = title;
        this.pubDate = pubDate;
        this.media = media;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.title + " - " + this.pubDate.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRSS dataRSS = (DataRSS) o;
        return Objects.equals(title, dataRSS.title) && Objects.equals(pubDate, dataRSS.pubDate) && Objects.equals(url, dataRSS.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pubDate, url);
    }
}
