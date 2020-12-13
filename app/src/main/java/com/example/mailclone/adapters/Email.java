package com.example.mailclone.adapters;

public class Email {
    public String email;
    public String subject;
    public String content;
    public String time;
    public String avatar;
    public boolean checked;

    public Email(String email, String subject, String content, String time, String avatar, boolean checked) {
        this.email = email;
        this.content = content;
        this.subject = subject;
        this.time = time;
        this.avatar = avatar;
        this.checked = checked;
    }
}
