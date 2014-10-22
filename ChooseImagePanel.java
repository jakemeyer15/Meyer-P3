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

	JRadioButton small = new JRadioButton(smallString);
	small.setActionCommand(smallString);
	small.setSelected(true);

	JRadioButton medium = new JRadioButton(mediumString);
	medium.setActionCommand(mediumString);
	
	JRadioButton large = new JRadioButton(largeString);
	large.setActionCommand(largeString);

	JButton reset = new JButton("Reset");
	reset.addActionListener(new ResetListener());

	ButtonGroup group = new ButtonGroup();
	group.add(small);
	group.add(medium);
	group.add(large);

	small.addActionListener(new RadioListener());
	medium.addActionListener(new RadioListener());
	large.addActionListener(new RadioListener());

	JPanel radioPanel = new JPanel(new GridLayout(0, 1));
	radioPanel.add(small);
	radioPanel.add(medium);
	radioPanel.add(large);
	radioPanel.setBackground(Color.white);

	label = new JLabel("");
	
        imageView = new JLabel();
	
	log = new JTextArea(5,20);
	log.setMargin(new Insets(5,5,5,5));
	log.setEditable(false);
	JScrollPane logScrollPane = new JScrollPane(log);

	add(push);
	add(radioPanel);
	add(reset);
	add(label);
	add(logScrollPane);
	add(imageView);

	setBackground(Color.white);
	setPreferredSize(new Dimension(300,40));
    }

    private class ImageListener implements ActionListener{

	public void actionPerformed(ActionEvent event){
	    //frame.add(new FileChooser());
	    
	    int returnVal = fc.showOpenDialog(ChooseImagePanel.this);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION){
		try{
		    File file = fc.getSelectedFile();
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
    
    private class ResetListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	}
    } 

    private class RadioListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    Pixelator pix = new Pixelator(image);
	    pix.pixelate(e.getActionCommand());
	}
    }
}