package com.inhatc.secret;

import java.util.Date;

public class MemberVO {
    private String id;
    private String pw;
    private String nickname;
    private String school;
    private Date birth;
    private Date date;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "MemberVO [id=" + id + ", pw=" + pw + ", nickname=" + nickname + ", school=" + school + ", birth="
                + birth + ", date=" + date + "]";
    }
}