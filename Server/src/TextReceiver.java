import javax.swing.*;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TextReceiver extends Thread{
    InputStream in = null;
    ServerSocket sst = null;
    Socket s = null;
    String message = null;

    TextReceiver(ServerSocket sst){
        this.sst = sst;
    }

    @Override
    public void run(){
        try{
            s = sst.accept();
            readMessage();
            printMessage();
        }catch(IOException e){
            System.out.println("String socket error");
        }    
    }

    public void readMessage() throws IOException{
            in = s.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            message = dis.readUTF();
            System.out.println("You have a meesage");
            in.close();
            dis.close();
        
    }

    public void printMessage() {
        JOptionPane.showMessageDialog(null, this.message, "Wiadomość na serwerze", JOptionPane.INFORMATION_MESSAGE);
    }
}