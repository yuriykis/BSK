import java.io.*;
import java.net.*;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class SecureKeyManipulator {
    private Cipher cipher;
    private KeyPairGenerator keyPairGenerator;
    private KeyPair keyPair;
    private Key publicKey;
    private Key privateKey;

    SecureKeyManipulator() {
        try {
            cipher = Cipher.getInstance("RSA");
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPair = keyPairGenerator.generateKeyPair();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();

        } catch (NoSuchAlgorithmException e) {
           
        } catch (NoSuchPaddingException e) {
           
        } 

    }

    public void sendSessionKey(){
        try{
            Socket s = new Socket("localhost", 5003);
            OutputStream bout = s.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bout);
            objectOutputStream.writeObject(publicKey);
            objectOutputStream.close();
            bout.close();
            s.close();
        }catch(IOException e){

        }
    }

    public Key getPrivateKey(){
        return privateKey;
    }
}