package fds.sftp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/sftp_client")
public class SFTPRestController {
    @Autowired
    private SFTPClient sftpClient;

    @PutMapping ("/put/{id}")
    public void SFTPClientPut(@PathVariable("id") String id) throws IOException {
        sftpClient.PutFile(id);
    }

    @GetMapping ("/get/{id}")
    public void SFTPClientGet(@PathVariable("id") String id) throws IOException {
        sftpClient.GetFile(id);
    }

}
