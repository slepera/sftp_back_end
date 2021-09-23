package fds.sftp;


import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.sftp.server.FileHandle;
import org.apache.sshd.sftp.server.Handle;
import org.apache.sshd.sftp.server.SftpEventListener;

import java.io.IOException;

public class SFTPListener implements SftpEventListener {
    private static final AttributeKey<SomeType> MY_SPECIAL_KEY = new Attribute<SomeType>();


    @Override
    public void opening(ServerSession session, String remoteHandle, Handle localHandle) throws IOException {
        localHandle.setAttribute(MY_SPECIAL_KEY, instanceOfSomeType);
    }

    @Override
    public void writing(
            ServerSession session, String remoteHandle, FileHandle localHandle,
            long offset, byte[] data, int dataOffset, int dataLen)
            throws IOException {
        SomeType myData = localHandle.getAttribute(MY_SPECIAL_KEY);
        ...do something based on my data...
    }
}


    SftpSubsystemFactory factory = new SftpSubsystemFactory();
factory.addSftpEventListener(new MySftpEventListener());
        sshd.setSubsystemFactories(Collections.<NamedFactory<Command>>singletonList(factory));