package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.User;

import java.util.List;

public interface UserService {
    List<User> ListAll();

    User FindUser(Long id);

    User FindUser(Long id,String pw);

    User AddUser(User user);

    User UpdateUser(Long id, User user);

    boolean DeleteUser(Long id);
}
