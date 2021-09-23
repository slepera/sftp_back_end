package fds.sftp;


import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.sftp.server.FileHandle;
import org.apache.sshd.sftp.server.Handle;
import org.apache.sshd.sftp.server.SftpEventListener;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.util.Collection;

public class SFTPListener implements SftpEventListener {
    //private static final AttributeKey<SomeType> MY_SPECIAL_KEY = new Attribute<SomeType>();


    @Override
    public void opening(ServerSession session, String remoteHandle, Handle localHandle) throws IOException {
        System.out.println("incoming file: "+localHandle.toString());
        //localHandle.setAttribute(MY_SPECIAL_KEY, instanceOfSomeType);
    }

//    @Override
//    public void open(ServerSession session, String remoteHandle, Handle localHandle) throws IOException {
//        System.out.println("file opened");
//    }
//
//    @Override
//    public void initialized(ServerSession session, int version) throws IOException {
//        System.out.println("sftp session initialized");
//    }
//
//    @Override
//    public void closed(ServerSession session, String remoteHandle, Handle localHandle, Throwable thrown) throws IOException {
//        System.out.println("file closed");
//    }

    @Override
    public void written(ServerSession session, String remoteHandle, FileHandle localHandle, long offset, byte[] data, int dataOffset, int dataLen, Throwable thrown){
        System.out.println("file written");
    }

    @Override
    public void read(ServerSession session, String remoteHandle, FileHandle localHandle, long offset, byte[] data, int dataOffset, int dataLen, int readLen, Throwable thrown){
        System.out.println("file read");
    }

    /*
    @Override
    public void writing(
            ServerSession session, String remoteHandle, FileHandle localHandle,
            long offset, byte[] data, int dataOffset, int dataLen)
            throws IOException {
        SomeType myData = localHandle.getAttribute(MY_SPECIAL_KEY);
        ...do something based on my data...
    }*/
}


//    SftpSubsystemFactory factory = new SftpSubsystemFactory();
//factory.addSftpEventListener(new MySftpEventListener());
//        sshd.setSubsystemFactories(Collections.<NamedFactory<Command>>singletonList(factory));