import javax.swing.*;
import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.util.Base64;

public class Main{

public final static int FILE_SIZE = 81920;
public static void main(String[] args) throws IOException{
    ServerSocket ss = new ServerSocket(4999); //socket for files
    ServerSocket sst = new ServerSocket(5000); //socket for string

    Path path = Paths.get("downloaded_file");

    TextReceiver rt = new TextReceiver(sst);
    rt.start();


    Socket s = ss.accept();


        InputStream in = s.getInputStream();
        byte[] buffer = new byte[FILE_SIZE];

        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path.toFile()));

        FileReceiver rs = new FileReceiver(in, out, buffer);
        rs.start(); 
  
        System.in.read();
        
        String fileType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(buffer));
        
            File oldFile = new File("downloaded_file");
            File newFile = new File("downloaded_file." + fileType);
            oldFile.renameTo(newFile);

        rs.stop();
        out.close();
        in.close();
        s.close();
    }
}