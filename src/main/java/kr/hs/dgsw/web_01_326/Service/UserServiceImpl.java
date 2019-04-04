package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> ListAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User FindUser(Long id) {
        User user = new User();
        Optional<User> found = userRepository.findById(id);

        if (found.isPresent()) {
            user.setEmail(found.get().getEmail());
            user.setId(found.get().getId());
            user.setJoined(found.get().getJoined());
            user.setModified(found.get().getModified());
            user.setUsername(found.get().getUsername());
        }

        return user;
    }

    @Override
    public User AddUser(User user) {
        Optional<User> found = this.userRepository.findByEmail(user.getEmail());
        if (found.isPresent()) return null;
        else return this.userRepository.save(user);
    }

    @Override
    public User UpdateUser(Long id, User user) {
        return this.userRepository.findById(id).map(found ->{
            found.setUsername(Optional.ofNullable(user.getUsername()).orElse(found.getUsername()));
            found.setEmail(Optional.ofNullable(user.getEmail()).orElse(found.getEmail()));
            found.setFilename(Optional.ofNullable(user.getFilename()).orElse(found.getFilename()));
            found.setFilepath(Optional.ofNullable(user.getFilepath()).orElse(found.getFilepath()));
            return this.userRepository.save(found);
        }).orElse(null);
    }

    @Override
    public boolean DeleteUser(Long id) {
        Optional<User> found = this.userRepository.findById(id);

        if (found.isPresent()) {
            try {
                this.userRepository.deleteById(id);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
