package com.dmc.model;

public class mockInfoEntity {
    private Integer id;

    private String alias;

    private String proto;

    private String domain;

    private String url;

    private Integer bind;

    private Integer port;

    private String filename;

    private Integer status;

    private String completeUrl;

    private String json;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto == null ? null : proto.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getBind() {
        return bind;
    }

    public void setBind(Integer bind) {
        this.bind = bind;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl == null ? null : completeUrl.trim();
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json == null ? null : json.trim();
    }
}