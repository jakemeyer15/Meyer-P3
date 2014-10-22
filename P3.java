import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class P3{

    public static void main(String[] args){
	javax.swing.SwingUtilities.invokeLater(new Runnable(){
		public void run(){
		    GUI pixelator = new GUI();
		    pixelator.createAndShowGUI();
		}
	    });
    }
}