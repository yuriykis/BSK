package src;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.Key;

public class SecureKeyReceiver {

    private Connection connection;
    private Key publicKey = null;
    private Socket publicKeySocket;

    SecureKeyReceiver(Connection c) {
        try{
            this.connection = c;
            publicKeySocket = c.getPublicKeySocket();
            InputStream in = publicKeySocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            publicKey = (Key) objectInputStream.readObject();
        }catch(IOException e){

        } catch (ClassNotFoundException e) {
           
        }
    }

    public Key getPublicKey(){
        return publicKey;
    }
}