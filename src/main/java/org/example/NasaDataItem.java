package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaDataItem {
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String media_type;
    private final String service_version;
    private final String title;
    private final String url;

        public NasaDataItem (
                @JsonProperty("copyright") String copyright,
                @JsonProperty("date") String date,
                @JsonProperty("explanation") String explanation,
                @JsonProperty("hdurl") String  hdurl,
                @JsonProperty("media_type") String  media_type,
                @JsonProperty("service_version") String  service_version,
                @JsonProperty("title") String  title,
                @JsonProperty("url") String  url
        ) {
            this.copyright = copyright;
            this.date = date;
            this.explanation = explanation;
            this.hdurl = hdurl;
            this.media_type = media_type;
            this.service_version = service_version;
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
                    "hdurl" + this.hdurl + "\n" +
                    "media_type" + this.media_type + "\n" +
                    "service_version" + this.service_version + "\n" +
                    "title" + this.title + "\n" +
                    "url" + this.url;
        }

}
