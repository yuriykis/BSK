package src;
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.*;
import java.net.Socket;

import javax.swing.JOptionPane;

public class TextManipulator {
    private String text;

    TextManipulator(){
        text = JOptionPane.showInputDialog("Proszę wpisać tekst");
    }

    public void sendText(Button button, Socket s){
        try{
            OutputStream out = s.getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(out);

            System.out.println("Wysyłam wiadomość do serwera");
            dataOut.writeUTF(text);
            dataOut.flush(); 
            dataOut.close();
        }catch(IOException e){

        }

    }
}