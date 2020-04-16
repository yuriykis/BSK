package src;

import java.io.IOException;
import java.awt.EventQueue;


public class Main{
 public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException{

   EventQueue.invokeLater(new Runnable(){
  
     @Override
     public void run() {
       new MainFrame();
     }
   });

  }
}