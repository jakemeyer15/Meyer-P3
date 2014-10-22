import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.awt.Color;
import java.awt.image.*;

public class Pixelator{

    private BufferedImage image;
    private BufferedImage new_image;
    private String image_name;

    public Pixelator(String orig_image_name){
	
	BufferedImage orig_buff_image = null;
	try{
	    orig_buff_image = ImageIO.read(new File(orig_image_name));
	}
	catch(IOException e){
	    System.out.println("Oops.");
	    System.exit(0);
	}
	this.image = orig_buff_image;
	this.image_name = orig_image_name;
    }

    public String pixelate(int pix_size){
	
	Raster original = image.getData();

	WritableRaster new_image = original.createCompatibleWritableRaster();
	
	for(int y=0; y<original.getHeight(); y+=pix_size){
	    for(int x=0; x<original.getWidth(); x+=pix_size){
		double[] pixel = new double[pix_size];
		pixel = original.getPixel(x, y, pixel);

		for(int yn=y; (yn<y+pix_size)&&(yn<new_image.getHeight()); yn++){
		    for(int xn=x; (xn<x+pix_size)&&(xn<new_image.getWidth()); xn++){
			new_image.setPixel(xn, yn, pixel);
		    }
		}
	    }
	}

	image.setData(new_image);

	String new_image_name = image_name + "_" + pix_size + "_pixelated.jpg";
	try{
	    ImageIO.write(image, "jpg", new File(new_image_name));
	}
	catch(IOException e){
	    System.err.println("Oops.");
	    System.exit(0);
	}
	return new_image_name;
    }
    
    public String stopPixelate(){
	return image_name;
    }
}