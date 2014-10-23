import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class P3{

    //Main method
    public static void main(String[] args){
	//Catches command line input error
	if(args.length>0){
	    System.out.println("You have entered too many arguments. This program only requires one argument.");
	    System.exit(0);
	}

	//Runs Swing and instantiates a new GUI object to show the frame
	javax.swing.SwingUtilities.invokeLater(new Runnable(){
		public void run(){
		    GUI pixelator = new GUI();
		    pixelator.createAndShowGUI();
		}
	    });
    }
}