package com.xyz.domain;

import java.io.Serializable;

public class ArticalWithBLOBs extends Artical implements Serializable {
    private String content;

    private String contentRich;

    private static final long serialVersionUID = 1L;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getContentRich() {
        return contentRich;
    }

    public void setContentRich(String contentRich) {
        this.contentRich = contentRich == null ? null : contentRich.trim();
    }
}