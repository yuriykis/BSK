package src;
import javax.swing.*;

import java.awt.*;
import java.net.Socket;


public class ButtonPanel extends JPanel{
  private static final long serialVersionUID = 2L;
    private Button fileButton;
    private Button textButton;
 
    public ButtonPanel(Connection c){

      setLayout(new FlowLayout());
      fileButton = new FileButton(this, c.getSocket1());
      textButton = new TextButton(this, c.getSocket2());

      setAlignmentX(Component.LEFT_ALIGNMENT);
      setPreferredSize(new Dimension(150,600));
      setMaximumSize(new Dimension(150,600));
       add(fileButton);
       add(textButton);
      setBorder(BorderFactory.createTitledBorder("Wybór opcji wysyłania"));
    }
    
}