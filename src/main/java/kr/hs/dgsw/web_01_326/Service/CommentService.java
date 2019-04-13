package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {
    List<CommentUsernameProtocol> listAllComments();
    CommentUsernameProtocol AddComment(Comment comment);
    Comment FindComment(Long id);
    List<Comment> FindAll();
    CommentUsernameProtocol UpdateComment(Long id, Comment comment);
    boolean DeleteComment(Long id);
}
