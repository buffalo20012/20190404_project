package kr.hs.dgsw.web_01_326.Controller;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_01_326.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/listcomment")
    public List<CommentUsernameProtocol> list() {
        return this.commentService.listAllComments();
    }

    @GetMapping("/comment")
    @ResponseBody
    public List<Comment> listall() {
        return this.commentService.FindAll();
    }

    @GetMapping("/findcomment/{id}")
    @ResponseBody
    public Comment find(@PathVariable String id){
        return this.commentService.FindComment(Long.parseLong(id));
    }

    @PostMapping("/addcomment")
    @ResponseBody
    public CommentUsernameProtocol add(@RequestBody Comment comment){
        return this.commentService.AddComment(comment);
    }

    @DeleteMapping("/deletecomment/{id}")
    @ResponseBody
    public boolean delete(@PathVariable Long id){
        return this.commentService.DeleteComment(id);
    }
}
