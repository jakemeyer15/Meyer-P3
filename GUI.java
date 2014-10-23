import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class GUI{
    
    //Creates GUI
    public void createAndShowGUI(){

	//Establishes frame
	JFrame frame = new JFrame("Pixelator");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.setPreferredSize(new Dimension(640,480));
	
	//Instantiates class that was constructed
	ChooseImagePanel panel = new ChooseImagePanel();
	frame.getContentPane().add(panel);

	//Adds menu bar
	JMenuBar menuBar = new JMenuBar();

	//Adds file menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

	//Adds quit item in file menu
        JMenuItem quitMenuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        fileMenu.add(quitMenuItem);
	quitMenuItem.addActionListener(new QuitListener());

	//Adds help menu
	JMenu helpMenu = new JMenu("Help");
	helpMenu.setMnemonic(KeyEvent.VK_H);
	menuBar.add(helpMenu);

	//Adds get started item in help menu
	JMenuItem getStartedMenuItem = new JMenuItem("Get Started", KeyEvent.VK_G);
	helpMenu.add(getStartedMenuItem);
	getStartedMenuItem.addActionListener(new GetStartedListener());
	
	//Adds about item in help menu
	JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
	helpMenu.add(aboutItem);
	aboutItem.addActionListener(new AboutListener());

	//Displays frame
	frame.setJMenuBar(menuBar);	
	frame.pack();
	frame.setVisible(true);
    }

    //Listener class for quit button
    public class QuitListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    //Exits system
	    System.exit(0);
	}
    }
    
    //Listener class for get started button
    public class GetStartedListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    //Creates a string to read the file and string to contain the contents
	    String getStarted = "";
	    String output = "";
	    try{
		//Reads get started file
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
	    //Displays get started in dialog box
	    JOptionPane.showMessageDialog(null, output);
	}
    }

    //Listener class for about button
    public class AboutListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    //Creates a string to read the file and a string to contain the contents
	    String about = "";
	    String output = "";
	    try{
		//Reads about file
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
	    //Displays about in dialog box
	    JOptionPane.showMessageDialog(null, output);
	}
    }
}