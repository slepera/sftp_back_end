package fds.sftp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import com.mysql.cj.xdevapi.*;
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

    @GetMapping ("/mysql_document_store")
    public String ProvaMySQL() throws IOException {
        Session mySession = new SessionFactory().getSession("mysqlx://uqiqrf3ae3eo7tep:W1uRj2qfAzukKLgMLDXr@b7c6bxkiztedyuahpq3l-mysql.services.clever-cloud.com:33060/b7c6bxkiztedyuahpq3l");
        //Session mySession = new SessionFactory().getSession("mysqlx://b7c6bxkiztedyuahpq3l-mysql.services.clever-cloud.com:3306/b7c6bxkiztedyuahpq3l?user=uqiqrf3ae3eo7tep&password=W1uRj2qfAzukKLgMLDXr");

        Schema myDb = mySession.getSchema("b7c6bxkiztedyuahpq3l");

        Collection myColl = myDb.getCollection("countryinfo");

        //DocResult myDocs = myColl.find("name like :param").limit(1).bind("param", "L%").execute();
        DocResult myDocs = myColl.find().execute();
        String res = myDocs.fetchOne().toString();

        mySession.close();
        return res;
    }


}
