package kr.hs.dgsw.web_01_326.Protocol;

import kr.hs.dgsw.web_01_326.Domain.Comment;

public class CommentUsernameProtocol extends Comment {
    private String username;

    public CommentUsernameProtocol() {
    }

    public CommentUsernameProtocol(Comment comment, String username) {
        super(comment);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
