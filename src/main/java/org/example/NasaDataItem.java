package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaDataItem {
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;

        public NasaDataItem (
                @JsonProperty("copyright") String copyright,
                @JsonProperty("date") String date,
                @JsonProperty("explanation") String explanation,
                @JsonProperty("hdurl") String hdurl,
                @JsonProperty("media_type") String mediaType,
                @JsonProperty("service_version") String serviceVersion,
                @JsonProperty("title") String  title,
                @JsonProperty("url") String  url
        ) {
            this.copyright = copyright;
            this.date = date;
            this.explanation = explanation;
            this.hdurl = hdurl;
            this.mediaType = mediaType;
            this.serviceVersion = serviceVersion;
            this.title = title;
            this.url = url;
        }

        public String getContentUrl() {
            return this.hdurl;
        }

        public String toString() {
            return "NASA DataItem : \n" +
                    "copyright: " + this.copyright + "\n" +
                    "date: " + this.date + "\n" +
                    "explanation: " + this.explanation + "\n" +
                    "hdurl: " + this.hdurl + "\n" +
                    "mediaType: " + this.mediaType + "\n" +
                    "serviceVersion: " + this.serviceVersion + "\n" +
                    "title: " + this.title + "\n" +
                    "url: " + this.url;
        }

}
