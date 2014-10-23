import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class GUI{
    
    public void createAndShowGUI(){

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
	quitMenuItem.addActionListener(new QuitListener());

	JMenu helpMenu = new JMenu("Help");
	helpMenu.setMnemonic(KeyEvent.VK_H);
	menuBar.add(helpMenu);

	JMenuItem getStartedMenuItem = new JMenuItem("Get Started", KeyEvent.VK_G);
	helpMenu.add(getStartedMenuItem);
	getStartedMenuItem.addActionListener(new GetStartedListener());
	
	JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
	helpMenu.add(aboutItem);
	aboutItem.addActionListener(new AboutListener());

	frame.setJMenuBar(menuBar);	
	frame.pack();
	frame.setVisible(true);
    }

    public class QuitListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    System.exit(0);
	}
    }
    
    public class GetStartedListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    String getStarted = "";
	    String output = "";
	    try{
		FileReader reader = new FileReader("GetStarted.txt");
		BufferedReader bReader = new BufferedReader(reader);
		while((getStarted = bReader.readLine()) != null){
		    output += (getStarted + "\n");
		}
		reader.close();
	    }
	    catch(Exception e){
		e.printStackTrace();
	    }			      
	    JOptionPane.showMessageDialog(null, output);
	}
    }

    public class AboutListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    String about = "";
	    String output = "";
	    try{
		FileReader reader = new FileReader("About.txt");
		BufferedReader bReader = new BufferedReader(reader);
		while((about = bReader.readLine()) != null){
		    output += (about + "\n");
		}
		reader.close();
	    }
	    catch(Exception e){
		e.printStackTrace();
	    }			      
	    JOptionPane.showMessageDialog(null, output);
	}
    }
}