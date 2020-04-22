import java.io.*;
import java.io.IOException;
import java.net.*;

public class EncryptionModeReceiver extends Thread {

    private ServerSocket ss;
    private Socket s;
    private byte[] encryptionModeByte;

    EncryptionModeReceiver(byte[] emode){
        ss = null;
        s = null;
        encryptionModeByte = emode;
    }


    @Override
    public void run(){
        try{
            ss = new ServerSocket(5002);
            s = ss.accept();
            InputStream bis = s.getInputStream();
            bis.read(encryptionModeByte);
            s.close();
            ss.close();
        }catch(IOException e){
            System.out.println("Encryption Mode Receiver socket error");
        }
    }
}