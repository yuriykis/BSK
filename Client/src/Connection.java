package src;
import java.io.IOException;
import java.net.*;
public class Connection {

    private Socket s;
    private Socket st;
    private ServerSocket serverPublicKeyRecevier;
    private Socket publicKeyReceiver;

    public Connection() throws IOException{
        s = new Socket("localhost", 4999);
        st = new Socket("localhost", 5000);
        serverPublicKeyRecevier = new ServerSocket(5003); 
        publicKeyReceiver = serverPublicKeyRecevier.accept();
    }

    public Socket getSocket1(){
        if(s.isConnected()){
            return s;
        }else
        return null;
    }

    public Socket getSocket2(){
        if(st.isConnected()){
            return st;
        }else
        return null;
    }

    public Socket getPublicKeySocket(){
        if(publicKeyReceiver.isConnected()){
            return publicKeyReceiver;
        }else
        return null;
    }

}