import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI{
    
    public static void createAndShowGUI(){

	JFrame frame = new JFrame("Pixelator");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.setPreferredSize(new Dimension(640,480));
	
	ChooseImagePanel panel = new ChooseImagePanel();
	frame.getContentPane().add(panel);

	JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem quitMenuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        fileMenu.add(quitMenuItem);

	JMenu helpMenu = new JMenu("Help");
	helpMenu.setMnemonic(KeyEvent.VK_H);
	menuBar.add(helpMenu);

	JMenuItem getStartedMenuItem = new JMenuItem("Get Started", KeyEvent.VK_G);
	helpMenu.add(getStartedMenuItem);
	
	JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
	helpMenu.add(aboutItem);

	frame.setJMenuBar(menuBar);	
	frame.pack();
	frame.setVisible(true);
    }
}