import javax.swing.*;
import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.util.Base64;

public class FileReceiver extends Thread {
    public final static int FILE_SIZE = 100000;

    InputStream in = null;
    BufferedOutputStream out = null;
    byte[] buffer = null;

    FileReceiver(InputStream in, BufferedOutputStream out, byte[] buffer){
        this.in = in;
        this.out = out;
        this.buffer = buffer;
    }

    public void Read() throws IOException{
        int count;
        while((count = in.read(buffer)) != -1 ) {
            System.out.println(count);
            out.write(buffer, 0, count);
            out.flush();         
        } 
    }

    @Override
    public void run()
    {
        try{
            Read();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}