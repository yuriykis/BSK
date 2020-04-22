package src;
import java.awt.event.*;
import java.net.Socket;

public class FileButton extends Button {
    private static final long serialVersionUID = 2L;
    
    private Socket s = null;
    private Connection connection;

    FileButton(ButtonPanel buttonPanel, Connection c){
        super("Wyślij plik", buttonPanel);
        this.s = c.getSocket1();
    }

    @Override
    public void actionPerformed(ActionEvent e){   
        
        FileManipulator fm = new FileManipulator(connection);
        fm.sendFile(this, s);
        }
}