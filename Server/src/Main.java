import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {

    public final static int FILE_SIZE = 81920;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(4999); // socket for files
        ServerSocket sst = new ServerSocket(5000); // socket for string

        Path path = Paths.get("downloaded_file");

        // waiting for text message
        TextReceiver rt = new TextReceiver(sst);
        rt.start();

        // waiting for key
        byte[] byteKey = new byte[16];
        KeyReceiver kr = new KeyReceiver(byteKey);
        kr.start();

        // waiting for file
        Socket s = ss.accept();
        kr.join();

        InputStream in = s.getInputStream();
        byte[] buffer = new byte[FILE_SIZE];

        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path.toFile()));

        FileReceiver rs = new FileReceiver(in, out, buffer);
        rs.start();

        System.in.read();

        // String stringKey = "QWERTYUI12345678";
        // decrypt file
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path.toFile()));

        SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
        byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        try {
            Cipher decryptCipher = Cipher.getInstance("AES/OFB/PKCS5Padding");
            byte[] decBuffer = new byte[bis.available()];
            byte[] decBytes = new byte[bis.available()];
            bis.read(decBuffer);
            try {
                decryptCipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
                try {
                    decBytes = decryptCipher.doFinal(decBuffer);

                    Path decrypyFile = Paths.get("decrypt_file");
                    BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(decrypyFile.toFile()));
                    bout.write(decBytes);
                    bout.close();
                } catch (IllegalBlockSizeException be) {
                    System.out.println("Illegal Block Size");
                } catch (BadPaddingException bpe) {
                    System.out.println("Invalid Padding");
                }
            } catch (InvalidKeyException | InvalidAlgorithmParameterException ie) {
                System.out.println("Invalid Key");
            }
        }catch(NoSuchAlgorithmException e){

        }catch(NoSuchPaddingException ne){

        }

    
        //end
        rs.stop();
        bis.close();
        out.close();
        in.close();
        s.close();
        ss.close();
    }
}