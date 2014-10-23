import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.awt.Color;
import java.awt.image.*;

public class Pixelator{

    //Establishes old image, new image, and the file name of the image
    private BufferedImage image;
    private BufferedImage new_image;
    private String image_name;

    //Constructs Pixelator class, takes in original image name
    public Pixelator(String orig_image_name){
	
	//Establishes buffered image
	BufferedImage orig_buff_image = null;
	try{
	    //Reads original image and assigns it to buffered image
	    orig_buff_image = ImageIO.read(new File(orig_image_name));
	}
	catch(IOException e){
	    System.out.println("Oops.");
	    System.exit(0);
	}
	//Assigns buffered image to image that is accessible everywhere
	this.image = orig_buff_image;
	//Assigns image name to name that is accessible everywhere
	this.image_name = orig_image_name;
    }

    //Takes in a desired pixelation level and returns a string of the new file name
    public String pixelate(int pix_size){
	
	//Takes in image and creates a raster to find the size and color values
	Raster original = image.getData();
	//Creates an editable raster the same size as the original image
	WritableRaster new_image = original.createCompatibleWritableRaster();
	//Makes sure it is to be pixelated
	if(pix_size>0){
	    //Searches through image for colors every pix_size pixels
	    for(int row=0; row<original.getHeight(); row+=pix_size){
		for(int col=0; col<original.getWidth(); col+=pix_size){
		    double[] pixel = new double[3];
		    pixel = original.getPixel(col, row, pixel);
		    //Sets all pixels in that range to that color
		    for(int yn=row; (yn<row+pix_size)&&(yn<new_image.getHeight()); yn++){
			for(int xn=col; (xn<col+pix_size)&&(xn<new_image.getWidth()); xn++){
			    new_image.setPixel(xn, yn, pixel);
			}
		    }
		}
	    }
	    //Sends output to new image
	    image.setData(new_image);
	    //Sets new image name
	    String new_image_name = image_name + "_" + pix_size + "_pixelated.jpg";
	    try{
		//Writes buffered image to file
		ImageIO.write(image, "jpg", new File(new_image_name));
	    }
	    catch(IOException e){
		System.err.println("Oops.");
		System.exit(0);
	    }
	    //Returns the file name of the new image
	    return new_image_name;
	}
	else{
	    //8-bit action
	    return image_name;
	}
    }
    
    //Returns original image if user presses Reset
    public String stopPixelate(){
	return image_name;
    }
}