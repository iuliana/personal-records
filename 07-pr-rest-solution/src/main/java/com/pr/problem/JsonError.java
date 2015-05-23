package com.pr.problem;

/**
 * Created by iuliana.cosmina on 5/23/15.
 */
public class JsonError {

    private String url;
    private String message;

    public JsonError(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
