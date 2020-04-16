package src;
import java.io.IOException;

import javax.swing.*;


public class MainFrame extends JFrame{
    
    private static final long serialVersionUID = 1L;
    private Connection con;

    public MainFrame(){
        super("Program do wysyłania plików");
        try{
        connect();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        addPanel();
        setSize(600,600);
        setVisible(true);
        }catch (IOException e){
            JOptionPane.showMessageDialog(this, "Server connection error", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }catch(NullPointerException ne){
            JOptionPane.showMessageDialog(this, "Socket error", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void addPanel() throws NullPointerException{
        JPanel panel = new ButtonPanel(con);
        this.getContentPane().add(panel);
        pack();
    }
   
    private void connect() throws IOException{
      con = new Connection();
    }


}