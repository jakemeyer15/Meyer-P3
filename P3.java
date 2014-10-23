import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class P3{

    public static void main(String[] args){
	if(args.length>0){
	    System.out.println("You have entered too many arguments. This program only requires one argument.");
	    System.exit(0);
	}
	javax.swing.SwingUtilities.invokeLater(new Runnable(){
		public void run(){
		    GUI pixelator = new GUI();
		    pixelator.createAndShowGUI();
		}
	    });
    }
}