package src;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.directory.NoSuchAttributeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.*;

public class Encryptor {

   // private SecretKey key;
    private SecretKeySpec key;
    private Cipher cipher;
    String stringKey = "QWERTYUI12345678";

    Encryptor(){
        try{
            cipher = Cipher.getInstance("AES");
            sendKey();
            key = new SecretKeySpec(stringKey.getBytes(), "AES");
            try{
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }catch(InvalidKeyException e){
                System.out.println("Invalid Key");
                cipher = null;
                key = null;
            }
        } catch(NoSuchAlgorithmException nsae){
            cipher = null;
            key = null;
        }catch(NoSuchPaddingException nspe){
            cipher = null;
            key = null;
        }catch(IOException e){

        }
    }

    public void sendKey() throws IOException{
        Socket s = new Socket("localhost", 5001);
        OutputStream bout = s.getOutputStream();
        bout.write(stringKey.getBytes());
        s.close();
    }

    public byte[] encryptFile(byte[] bytes){

        byte[] encryptedFile = null;

        try{
            encryptedFile = cipher.doFinal(bytes);

        } catch(IllegalBlockSizeException ibse){
            
        } catch(BadPaddingException bpe){

        }
        return encryptedFile;
    }

}