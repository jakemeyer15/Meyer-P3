import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO;

public class ChooseImagePanel extends JPanel{
    
    private String fileName;
    private JButton push;
    private JLabel label;
    JButton openButton;
    JTextArea log;
    JFileChooser fc;
    BufferedImage image = null;
    JLabel imageView;
    JRadioButton small;
    JRadioButton medium;
    JRadioButton large;
    JRadioButton reset;

    public ChooseImagePanel(){
	
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files",
								     "jpg", "png", "jpeg");
	fc = new JFileChooser();
	fc.setFileFilter(filter);
	
	push = new JButton("Choose Image");
	push.addActionListener(new ImageListener());

	String smallString = "Small";
	String mediumString = "Medium";
	String largeString = "Large";

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
	
	ButtonGroup group = new ButtonGroup();
	group.add(small);
	group.add(medium);
	group.add(large);
	group.add(reset);
	
	small.addActionListener(new RadioListener());
	medium.addActionListener(new RadioListener());
	large.addActionListener(new RadioListener());
	reset.addActionListener(new RadioListener());

	JPanel radioPanel = new JPanel(new GridLayout(1, 0));
	radioPanel.add(small);
	radioPanel.add(medium);
	radioPanel.add(large);
	radioPanel.add(reset);
	radioPanel.setBackground(Color.black);

	label = new JLabel("");
	
        imageView = new JLabel(new ImageIcon());
	
	log = new JTextArea(5,20);
	log.setMargin(new Insets(5,5,5,5));
	log.setEditable(false);
	JScrollPane logScrollPane = new JScrollPane(log);

	add(push);
	add(radioPanel);
	add(label);
	add(logScrollPane);
	add(imageView);

	setBackground(Color.gray);
	setPreferredSize(new Dimension(300,40));
    }

    public class ImageListener implements ActionListener{

	public void actionPerformed(ActionEvent event){
	    
	    int returnVal = fc.showOpenDialog(ChooseImagePanel.this);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION){
		try{
		    File file = fc.getSelectedFile();
		    fileName = fc.getName(file);
		    BufferedImage bi = null;
		    try{
			bi = ImageIO.read(file);
		    }
		    catch(IOException e){
			System.out.println("The program could not read the file you submitted. Make sure it is located in the correct directory. It also must be an image file(\".png\" or \".jpg\"). Please try running the program again.");
			System.exit(0);
		    }
		    image = bi;
		    imageView.setIcon(new ImageIcon(image));
		    
		    log.append("Opening: " + file.getName() + ".\n");
		    
		    small.setEnabled(true);
		    medium.setEnabled(true);
		    large.setEnabled(true);
		    reset.setEnabled(true);
		}
		catch(Exception e){
		    System.err.println("Oops.");
		    System.exit(0);
		}
	    } else{
		log.append("Open command cancelled by user.\n");
	    }
	    log.setCaretPosition(log.getDocument().getLength());
	}
    }
    
    private class RadioListener implements ActionListener{

	public void actionPerformed(ActionEvent event){

	    String size = event.getActionCommand();
	    if(size.equals("Small")){
		Pixelator pix = new Pixelator(fileName);
		int pix_size = 1;
		pix_size = 5;
		imageView.setIcon(new ImageIcon(pix.pixelate(pix_size)));
	    }
	    if(size.equals("Medium")){
		Pixelator pix = new Pixelator(fileName);
		int pix_size = 1;
		pix_size = 15;
		imageView.setIcon(new ImageIcon(pix.pixelate(pix_size)));
	    }
	    if(size.equals("Large")){
		Pixelator pix = new Pixelator(fileName);
		int pix_size = 1;
		pix_size = 30;
		imageView.setIcon(new ImageIcon(pix.pixelate(pix_size)));
	    }
	    if(size.equals("Reset")){
		Pixelator pix = new Pixelator(fileName);
		int pix_size = 1;
		imageView.setIcon(new ImageIcon(pix.stopPixelate()));
	    }
	}
    }
}