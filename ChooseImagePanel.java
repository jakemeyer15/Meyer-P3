import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO;

public class ChooseImagePanel extends JPanel{
    
    //Declaring variables that will be used in multiple parts of code
    String fileName;
    JButton open;
    JTextArea log;
    JFileChooser fc;
    BufferedImage image = null;
    JLabel imageView;
    JLabel direction;
    JPanel resetPanel;
    JRadioButton small;
    JRadioButton medium;
    JRadioButton large;
    JRadioButton reset;
    JRadioButton eightBit;

    //Constructs the panel that contains everything
    public ChooseImagePanel(){
	
	//Establishes a JFileChooser that only has the ability to take in .jpg files
	//Will be used later
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files",
								     "jpg", "jpeg");
	fc = new JFileChooser();
	fc.setFileFilter(filter);
	
	//Sets layout and background of main window
	setLayout(new BorderLayout());
	setBackground(Color.white);
	
	//Creates the button to choose image
	open = new JButton("Choose Image");
	open.addActionListener(new ImageListener());
	open.setBorder(BorderFactory.createEtchedBorder());
	open.setPreferredSize(new Dimension(10, 40));

	//Creates a label for the buttons that has directions
	direction = new JLabel("Choose your pixelation level:");
	direction.setBorder(BorderFactory.createEtchedBorder());
	direction.setEnabled(false);

	//Creates strings to be associated with radio buttons
	String smallString = "Small";
	String mediumString = "Medium";
	String largeString = "Large";
	String eightBitString = "8-bit";

	//Creates radio buttons for each manipulation option
	small = new JRadioButton(smallString);
	small.setActionCommand(smallString);
	small.setEnabled(false);

	medium = new JRadioButton(mediumString);
	medium.setActionCommand(mediumString);
	medium.setEnabled(false);
	
	large = new JRadioButton(largeString);
	large.setActionCommand(largeString);
	large.setEnabled(false);

	reset = new JRadioButton("Reset");
	reset.setActionCommand("Reset");
	reset.setEnabled(false);
	
	eightBit = new JRadioButton(eightBitString);
	eightBit.setActionCommand(eightBitString);
	eightBit.setEnabled(false);

	//Sets a border for the reset button to detach it from the rest
	resetPanel = new JPanel();
	resetPanel.setLayout(new BorderLayout());
	resetPanel.add(reset, BorderLayout.CENTER);
	resetPanel.setBorder(BorderFactory.createEtchedBorder());
	
	//Groups all the radio buttons to make them mutually exclusive
	ButtonGroup group = new ButtonGroup();
	group.add(small);
	group.add(medium);
	group.add(large);
	group.add(reset);
	group.add(eightBit);
	
	//Adds listeners for each button
	small.addActionListener(new RadioListener());
	medium.addActionListener(new RadioListener());
	large.addActionListener(new RadioListener());
	reset.addActionListener(new RadioListener());
	eightBit.addActionListener(new RadioListener());

	//Puts all radio buttons in one panel
	JPanel radioPanel = new JPanel(new GridLayout(0, 1));
	radioPanel.add(direction);
	radioPanel.add(small);
	radioPanel.add(medium);
	radioPanel.add(large);
	radioPanel.add(eightBit);
	radioPanel.add(resetPanel);
	radioPanel.setBorder(BorderFactory.createEtchedBorder());
	
	//Establishes image viewing area
        imageView = new JLabel(new ImageIcon());
	
	//Creates a log that describes what the program is doing
	log = new JTextArea(5,20);
	log.setMargin(new Insets(5,5,5,5));
	log.setEditable(false);
	JScrollPane logScrollPane = new JScrollPane(log);

	//Adds everything to the layout
	add(open, BorderLayout.NORTH);
	add(radioPanel, BorderLayout.WEST);
	add(logScrollPane, BorderLayout.SOUTH);
	add(imageView, BorderLayout.CENTER);
    }

    //Listener class for open image button
    public class ImageListener implements ActionListener{
	
	public void actionPerformed(ActionEvent event){
	    
	    //Opens file chooser to prompt the user to select an image
	    int returnVal = fc.showOpenDialog(ChooseImagePanel.this);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION){
		try{
		    //Reads the file into a buffered image
		    File file = fc.getSelectedFile();
		    fileName = fc.getName(file);
		    BufferedImage bi = null;
		    try{
			bi = ImageIO.read(file);
		    }
		    catch(IOException e){
			System.out.println("The program could not read the file you submitted. Make sure it is located in the correct directory. It also must be an image file(\".jpg\"). Please try running the program again.");
			System.exit(0);
		    }
		    image = bi;
		    //Displays the image in the image viewing area of the window
		    imageView.setIcon(new ImageIcon(image));
		    
		    //Records in log
		    log.append("Opening: " + file.getName() + ".\n");
		    
		    //Enables radio buttons once the image appears
		    small.setEnabled(true);
		    medium.setEnabled(true);
		    large.setEnabled(true);
		    reset.setEnabled(true);
		    direction.setEnabled(true);
		}
		catch(Exception e){
		    System.err.println("Oops.");
		    System.exit(0);
		}
	    } else{
		//Records in log
		log.append("Open command cancelled by user.\n");
	    }
	    log.setCaretPosition(log.getDocument().getLength());
	}
    }
    
    //Listener class for all radio buttons
    private class RadioListener implements ActionListener{
	
	public void actionPerformed(ActionEvent event){
	
	    //Takes in which button was pressed and stores a string accordingly
	    String size = event.getActionCommand();
	    //Calls pixelate method with the size associated with each string
	    if(size.equals("Small")){
		Pixelator pix = new Pixelator(fileName);
		int pix_size = 1;
		pix_size = 5;
		imageView.setIcon(new ImageIcon(pix.pixelate(pix_size)));
		log.append("With small pixelation:\n");
	    }
	    if(size.equals("Medium")){
		Pixelator pix = new Pixelator(fileName);
		int pix_size = 1;
		pix_size = 15;
		imageView.setIcon(new ImageIcon(pix.pixelate(pix_size)));
		log.append("With medium pixelation:\n");
	    }
	    if(size.equals("Large")){
		Pixelator pix = new Pixelator(fileName);
		int pix_size = 1;
		pix_size = 30;
		imageView.setIcon(new ImageIcon(pix.pixelate(pix_size)));
		log.append("With large pixelation:\n");
	    }

	    //Resets image back to normal by calling stopPixelate method
	    if(size.equals("Reset")){
		Pixelator pix = new Pixelator(fileName);
		int pix_size = 1;
		imageView.setIcon(new ImageIcon(pix.stopPixelate()));
		log.append("Back to normal:\n");
	    }

	    //Converts image to 8-bit with pixelate method
	    if(size.equals("8-bit")){
		Pixelator pix = new Pixelator(fileName);
		int pix_size = 1;
		pix_size = -1;
		imageView.setIcon(new ImageIcon(pix.pixelate(pix_size)));
		log.append("Converted to 8-bit:\n");
	    }
	}
    }
}