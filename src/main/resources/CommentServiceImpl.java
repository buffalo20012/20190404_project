import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_01_326.Repository.CommentRepository;
import kr.hs.dgsw.web_01_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        User u = this.userRepository.save(new User("abc", "abc@dgsw"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 111"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 222"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 333"));
    }

    @Override
    public List<CommentUsernameProtocol> listAllComments() {
        List<Comment> commentList = this.commentRepository.findAll();
        List<CommentUsernameProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment -> {
            Optional<User> found = this.userRepository.findById(comment.getUserId());
            String username = (found.isPresent()) ? found.get().getUsername() : null;
            cupList.add(new CommentUsernameProtocol(comment, username));
        });
        return cupList;
    }




}
