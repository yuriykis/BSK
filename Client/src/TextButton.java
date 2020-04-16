package src;
import java.awt.event.*;
import java.net.Socket;

public class TextButton extends Button{
    private static final long serialVersionUID = 2L;
    private Socket s = null; 

    TextButton(ButtonPanel buttonPanel, Socket s){
        super("Wyślij tekst", buttonPanel);
        this.s = s;
    }

    @Override
    public void actionPerformed(ActionEvent e){    
            TextManipulator textManipulator = new TextManipulator();
            textManipulator.sendText(this, s);
        }
}