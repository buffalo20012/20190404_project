package kr.hs.dgsw.web_01_326.Controller;

import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class AttachmentController {

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
}
