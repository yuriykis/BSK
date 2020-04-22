
package src;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.*;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class FileManipulator{
    private Path path = null;
    private String encryptMode = null;

    FileManipulator(){
        JFileChooser fileChooser = new JFileChooser();
        File curDir = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(curDir);
        int returnValue = fileChooser.showOpenDialog(fileChooser.getParent());
        if(returnValue == JFileChooser.APPROVE_OPTION){
        path = Paths.get(fileChooser.getSelectedFile().toURI());
        } else{
            this.path = null;
        }
    }


    public Path getPath(){
        return this.path;
    }


    public void sendFile(Button button, Socket s){
        BufferedInputStream in = null;
        byte[] buffer = null;
        byte[] encrypted_buffer = null;
        Encryptor encryptor = new Encryptor();
        try{
            in = new BufferedInputStream(new FileInputStream(path.toFile()));
        }catch(FileNotFoundException fn){
            JOptionPane.showMessageDialog(button, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
        }

        try{
            buffer = new byte[in.available()];
            in.read(buffer, 0, buffer.length);
            try{
                OutputStream out = s.getOutputStream();
                encrypted_buffer = encryptor.encryptFile(buffer);
                out.write(encrypted_buffer);
                out.flush();
                JOptionPane.showMessageDialog(button, "Success! File was sent.", "Success", JOptionPane.INFORMATION_MESSAGE);
                in.close();
                }
                catch(IOException e){
                    JOptionPane.showMessageDialog(button, "Connection error", "Error", JOptionPane.ERROR_MESSAGE);
                    in.close();
                    System.exit(0);
                }

        }catch(IOException e){
            JOptionPane.showMessageDialog(button, "Error read file", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

}