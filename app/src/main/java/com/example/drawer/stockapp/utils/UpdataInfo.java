package com.example.drawer.stockapp.utils;

public class UpdataInfo {
    private String version;
    private String app_url;
    private String description;
    private String url_server;

    public String getUrl_server() {
        return url_server;
    }
    public void setUrl_server(String url_server) {
        this.url_server = url_server;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getUrl() {
        return app_url;
    }
    public void setUrl(String app_url) {
        this.app_url = app_url;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
