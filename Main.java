package Abhishek;


import javax.swing.*;

public class Main extends JFrame {

    public String getString(String args){
        return args;
    }

    public static void main(String[] args) {



        if(args.length == 0){
            System.out.println("Error! Please enter a filename");
            System.exit(1);
        }



        GUI myGUI;
        myGUI = new GUI();
      try {
          myGUI.getString(args[0]);
      }
      catch (NullPointerException e){
          System.out.println("File Doesn't exist!");
      }



        myGUI.setVisible(true);
        


    }
}
