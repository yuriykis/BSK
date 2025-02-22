import java.io.*;
import java.io.IOException;
import java.net.*;

public class SessionKeyReceiver extends Thread {

    private ServerSocket ss;
    private Socket s;
    private byte[] byteKey;

    SessionKeyReceiver(byte[] key){
        ss = null;
        s = null;
        byteKey = key;
    }


    @Override
    public void run(){
        try{
            ss = new ServerSocket(5001);
            s = ss.accept();
            InputStream bis = s.getInputStream();
            bis.read(byteKey);
            s.close();
            ss.close();
        }catch(IOException e){
            System.out.println("Key Receiver socket error");
        }
    }
}