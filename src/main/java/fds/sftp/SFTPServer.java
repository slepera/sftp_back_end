package fds.sftp;


import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.config.keys.AuthorizedKeysAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;


@Service
public class SFTPServer {
    @Autowired
    private SftpApplicationConfiguration sftpApplicationConfiguration;
    @PostConstruct
    public void SFTPServer() throws IOException
    {

        SshServer sshd = SshServer.setUpDefaultServer();
        sshd.setHost(sftpApplicationConfiguration.getServer_host());
        sshd.setPort(sftpApplicationConfiguration.getServer_port());
        Path path = Paths.get("host.ser");
        //Path path2 = Paths.get("host.ser");

        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(path));
        SftpSubsystemFactory factory = new SftpSubsystemFactory();
        factory.addSftpEventListener(new SFTPListener());
        sshd.setSubsystemFactories(Collections.singletonList(factory));
        sshd.setPasswordAuthenticator((username, password, session) -> username.equals(sftpApplicationConfiguration.getServer_username()) && password.equals(sftpApplicationConfiguration.getServer_password()));

        //sshd.setPublickeyAuthenticator(new AuthorizedKeysAuthenticator(path2));
        sshd.start();
    }
}
