package src;
import java.awt.event.*;
import java.net.Socket;

public class FileButton extends Button {
    private static final long serialVersionUID = 2L;
    
    private Socket s = null;

    FileButton(ButtonPanel buttonPanel, Socket s){
        super("Wyślij plik", buttonPanel);
        this.s = s;
    }

    @Override
    public void actionPerformed(ActionEvent e){   
        
        FileManipulator fm = new FileManipulator();
        fm.sendFile(this, s);
        }
}