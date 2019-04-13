package kr.hs.dgsw.web_01_326.Controller;

import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public List<User> list() {
        return this.userService.ListAll();
    }

    @GetMapping("/finduser/{id}")
    @ResponseBody
    public User find(@PathVariable String id) {
        return this.userService.FindUser(Long.parseLong(id));
    }

    @GetMapping("/finduser/{id}/{pw}")
    public User findUser(@PathVariable String id, @PathVariable String pw) {
        try{
            return this.userService.FindUser(Long.parseLong(id), pw);
        }catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/adduser")
    @ResponseBody
    public User add(@RequestBody User user) {
        return this.userService.AddUser(user);
    }

    @PutMapping("/updateuser/{id}")
    @ResponseBody
    public User update(@PathVariable String id ,@RequestBody User user){
        return this.userService.UpdateUser(Long.parseLong(id),user);
    }

    @DeleteMapping("/deleteuser/{id}")
    @ResponseBody
    public boolean delete(@PathVariable String id) {
        return this.userService.DeleteUser(Long.parseLong(id));
    }
}
