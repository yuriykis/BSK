package src;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.directory.NoSuchAttributeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.*;

public class Encryptor {

    private SecretKey key;
    private Cipher cipher;

    Encryptor(){
        try{
            cipher = Cipher.getInstance("AES");
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128);
            key = keygen.generateKey();
            try{
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }catch(InvalidKeyException e){
                cipher = null;
                key = null;
            }
        } catch(NoSuchAlgorithmException nsae){
            cipher = null;
            key = null;
        }catch(NoSuchPaddingException nspe){
            cipher = null;
            key = null;
        }
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