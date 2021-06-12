package com.inhatc.secret;

public class BoardVO {
    private String bno;
    private String title;
    private String content;
    private String writer;
    private String regdate;
    private String viewcnt;

    @Override
    public String toString() {
        return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
                + regdate + ", viewcnt=" + viewcnt + "]";
    }
    public String getBno() {
        return bno;
    }
    public void setBno(String bno) {
        this.bno = bno;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getRegdate() {
        return regdate;
    }
    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
    public String getViewcnt() {
        return viewcnt;
    }
    public void setViewcnt(String viewcnt) {
        this.viewcnt = viewcnt;
    }
}