package src;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.*;

import java.net.Socket;


public class Button extends JButton implements ActionListener{
    private static final long serialVersionUID = 1L;
    protected JPanel buttonPanel;


    Button(String title, JPanel buttonPanel){
        super(title);
        this.buttonPanel = buttonPanel;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){    
       
        }

}