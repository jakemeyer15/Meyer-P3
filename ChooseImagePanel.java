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
    JLabel direction;
    JPanel resetPanel;
    JRadioButton small;
    JRadioButton medium;
    JRadioButton large;
    JRadioButton reset;
    JRadioButton eightBit;

    public ChooseImagePanel(){
	
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files",
								     "jpg", "jpeg");
	fc = new JFileChooser();
	fc.setFileFilter(filter);
	
	setLayout(new BorderLayout());
	setBackground(Color.white);
	
	push = new JButton("Choose Image");
	push.addActionListener(new ImageListener());
	push.setBorder(BorderFactory.createEtchedBorder());
	push.setPreferredSize(new Dimension(10, 40));

	direction = new JLabel("Choose your pixelation level:");
	direction.setBorder(BorderFactory.createEtchedBorder());
	direction.setEnabled(false);

	String smallString = "Small";
	String mediumString = "Medium";
	String largeString = "Large";
	String eightBitString = "8-bit";

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

	resetPanel = new JPanel();
	resetPanel.setLayout(new BorderLayout());
	resetPanel.add(reset, BorderLayout.CENTER);
	resetPanel.setBorder(BorderFactory.createEtchedBorder());
	
	ButtonGroup group = new ButtonGroup();
	group.add(small);
	group.add(medium);
	group.add(large);
	group.add(reset);
	group.add(eightBit);
	
	small.addActionListener(new RadioListener());
	medium.addActionListener(new RadioListener());
	large.addActionListener(new RadioListener());
	reset.addActionListener(new RadioListener());

	JPanel radioPanel = new JPanel(new GridLayout(0, 1));
	radioPanel.add(direction);
	radioPanel.add(small);
	radioPanel.add(medium);
	radioPanel.add(large);
	radioPanel.add(eightBit);
	radioPanel.add(resetPanel);
	radioPanel.setBorder(BorderFactory.createEtchedBorder());
	
        imageView = new JLabel(new ImageIcon());
	
	log = new JTextArea(5,20);
	log.setMargin(new Insets(5,5,5,5));
	log.setEditable(false);
	JScrollPane logScrollPane = new JScrollPane(log);

	add(push, BorderLayout.NORTH);
	add(radioPanel, BorderLayout.WEST);
	add(logScrollPane, BorderLayout.SOUTH);
	add(imageView, BorderLayout.CENTER);
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
			System.out.println("The program could not read the file you submitted. Make sure it is located in the correct directory. It also must be an image file(\".jpg\"). Please try running the program again.");
			System.exit(0);
		    }
		    image = bi;
		    imageView.setIcon(new ImageIcon(image));
		    
		    log.append("Opening: " + file.getName() + ".\n");
		    
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
	    if(size.equals("Reset")){
		Pixelator pix = new Pixelator(fileName);
		int pix_size = 1;
		imageView.setIcon(new ImageIcon(pix.stopPixelate()));
		log.append("Back to normal:\n");
	    }
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