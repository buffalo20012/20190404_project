package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_01_326.Repository.CommentRepository;
import kr.hs.dgsw.web_01_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CommentUsernameProtocol> listAllComments() {

        //  List<Comment> commentList = this.commentRepository.findAll();
        List<Comment> commentList = this.commentRepository.findAllByOrderByCreatedAsc();
        List<CommentUsernameProtocol> cupList = new ArrayList<>();

        commentList.forEach(comment -> {
            Optional<User> found = this.userRepository.findById(comment.getUserId());
            String username = (!found.isPresent()) ? null : found.get().getUsername();
            cupList.add(new CommentUsernameProtocol(comment,username));
        });

        return cupList;
    }

    @Override
    public CommentUsernameProtocol AddComment(Comment comment) {
        Comment added = this.commentRepository.save(comment);
        Optional<User> user = userRepository.findById(added.getUserId());

        if(user.isPresent()) {
            return new CommentUsernameProtocol(comment, user.get().getUsername());
        }else{
            return new CommentUsernameProtocol(comment, null);
        }
    }

    @Override
    public Comment FindComment(Long id) {
        Optional<Comment> found = this.commentRepository.findById(id);
        Comment comment = new Comment();

        if(found.isPresent()){
            comment.setId(found.get().getId());
            comment.setContent(found.get().getContent());
            comment.setUserId(found.get().getUserId());
            comment.setFilename(found.get().getFilename());
            comment.setFilepath(found.get().getFilepath());
            comment.setCreated(found.get().getCreated());
            comment.setModified(found.get().getModified());
        }
        return comment;
    }

    @Override
    public List<Comment> FindAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public CommentUsernameProtocol UpdateComment(Long id, Comment comment) {
        return null;
    }

    @Override
    public boolean DeleteComment(Long id) {
        Optional<Comment> found = commentRepository.findById(id);

        if(found.isPresent()){
            try{
                this.commentRepository.deleteById(id);
            }catch (Exception e){
                boolean b = false;
                return b;
            }
        }else{
            return false;
        }
        return true;
    }
}
