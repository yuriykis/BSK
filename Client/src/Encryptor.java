package src;

import java.io.*;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.directory.NoSuchAttributeException;
import javax.swing.JOptionPane;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.*;

public class Encryptor {

    // private SecretKey key;
    private SecretKeySpec key;
    private Cipher cipher;
    private byte[] myKey;
    private IvParameterSpec ivParameterSpec;
    private String[] encrytionMode = {"ECB", "CBC", "CFB", "OFB"};
    private int encrytionModeInteger = 0;
 
    Encryptor() {
        encrytionModeInteger = JOptionPane.showOptionDialog(null, "Proszę wybrać metodę szyfrowania", "Metoda szyfrowania", JOptionPane.DEFAULT_OPTION,
         JOptionPane.INFORMATION_MESSAGE, null, encrytionMode, encrytionMode[0]);
        try {
            cipher = Cipher.getInstance("AES/" + encrytionMode[encrytionModeInteger] + "/PKCS5Padding");
            generateKey();
            sendKey();
            sendEncryptionMode();
            byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
            ivParameterSpec = new IvParameterSpec(iv);
            key = new SecretKeySpec(myKey, "AES");
            try {
                if(encrytionMode[encrytionModeInteger] == "ECB"){
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                }else{
                    cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
                }
            } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
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

    private void generateKey(){
        Random random = ThreadLocalRandom.current();
        myKey = new byte[16];
        random.nextBytes(myKey);
    }

    private void sendKey() throws IOException{
        Socket s = new Socket("localhost", 5001);
        OutputStream bout = s.getOutputStream();
        bout.write(myKey);
        bout.close();
        s.close();
    }

    private void sendEncryptionMode() throws IOException{
        Socket s = new Socket("localhost", 5002);
        OutputStream bout = s.getOutputStream();
        bout.write(encrytionMode[encrytionModeInteger].getBytes());
        bout.close();
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