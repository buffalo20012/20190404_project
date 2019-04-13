package kr.hs.dgsw.web_01_326.Controller;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_01_326.Service.CommentService;
import kr.hs.dgsw.web_01_326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.UUID;

@RestController
public class AttachmentController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile file) {
        String destFilename = "D:/Web_Code/20190326/upload/"
                + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try {
            File destfile = new File(destFilename);
            destfile.getParentFile().mkdirs();
            file.transferTo(destfile);
            return new AttachmentProtocol(destFilename, file.getOriginalFilename());
        } catch (IOException ex) {
            return null;
        }
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(@PathVariable String type, @PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try{
            String filename;
            String filepath;
            if(type.equals("user")){
                User user = this.userService.FindUser(Long.parseLong(id));
                filepath = user.getFilepath();
                filename = user.getFilename();

            }else{
                Comment comment = this.commentService.FindComment(Long.parseLong(id));
                filepath = comment.getFilepath();
                filename = comment.getFilename();
            }
            File file = new File(filepath);
            if(file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if(mimeType == null) mimeType = "application/octet-stream";

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition","inline: filename=\"" + filename + "\"");
            response.setContentLength((int)file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is,response.getOutputStream());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/attachment/{id}")
    public void contentImageDownload(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
        try{
            String filename;
            String filepath;
            Comment comment= this.commentService.FindComment(id);
                filepath = comment.getFilepath();
                filename = comment.getFilename();
            System.out.println(filepath + " / " + filename);

            File file = new File(filepath);
            if(file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if(mimeType == null) mimeType = "application/octet-stream";

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition","inline: filename=\"" + filename + "\"");
            response.setContentLength((int)file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is,response.getOutputStream());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
