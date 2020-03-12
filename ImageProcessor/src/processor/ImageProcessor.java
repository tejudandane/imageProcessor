package processor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
//Java program to illustrate orientation modification of image 
import java.awt.image.BufferedImage; 
import java.awt.image.DataBufferByte; 
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Size;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Imgproc.*;
//import org.opencv.imgcodecs.Imgcodecs.imread;
import org.opencv.*;

public class ImageProcessor {
	
	public ImageProcessor() {
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	}

	public byte[] getByteArrayFromMat(Mat mat) {
		try {
	    	MatOfByte mob=new MatOfByte();
	    	Imgcodecs.imencode(".jpg", mat, mob);
	    	byte ba[]=mob.toArray();
	    	BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(ba)); 
	    
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	ImageIO.write(image1, "png", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	    return new byte[] {};
	}
	
	
	public static void rotateImage(String path) {
		System.out.println("IP rotateImage. Path : "+path);
		//Loading the OpenCV core library
	      
	    //Reading the Image from the file and storing it in to a Matrix object
	    String file = "C:\\test\\Dogimage.jpeg";
	    Mat src = Imgcodecs.imread(file);

	    System.out.println("Src Size : "+ src.cols());
	    //Creating an empty matrix to store the result
	    Mat dst = new Mat();
	   
	    //Creating the transformation matrix M
	    Mat rotationMatrix = Imgproc.getRotationMatrix2D(new Point(300, 200), 90, 1);

	    //Rotating the given image
	    Imgproc.warpAffine(src, dst,rotationMatrix, new Size(src.cols(), src.cols()));

	    System.out.println("Des size : " + dst.cols());
	    //Writing the image
	    Imgcodecs.imwrite("C:\\test\\rotate_output.jpg", dst);
	    System.out.println("Image Processed");
	}
	
	
	public void flipImage(String path, int flipDirection) {
		// input buffered image object 
	   // File input = new File("E:\\test.jpg"); 
	    String file = "C:\\test\\Dogimage.jpeg";
	    Mat src = Imgcodecs.imread(file);
	    Mat dst = new Mat();
	    Core.flip(src, dst, flipDirection);
	    System.out.println("Des size : " + dst.cols());
	    //Writing the image
	    
	    String outputFileName = "flip_output_" + flipDirection +".jpg";
	    Imgcodecs.imwrite("C:\\test\\" + outputFileName, dst);
	    System.out.println("Image Flipped");
	}
	
	public void grayScale() {
		// To Read the image 
		String file = "C:\\test\\Dogimage.jpeg";
	    
        Mat source = Imgcodecs.imread(file); 
  
        // Creating the empty destination matrix 
        Mat dst = new Mat(); 
  
        // Converting the image to gray scale and  
        // saving it in the dst matrix 
        Imgproc.cvtColor(source, dst, Imgproc.COLOR_RGB2GRAY); 
  
        // Writing the image 
        Imgcodecs.imwrite("C:\\test\\grayscaled_output.jpg", dst);
	    System.out.println("Image GrayScaled");
	}
	
	public byte[] flipDirectionImage(String path, int flipDirection) {
		// input buffered image object 
	    String file = "C:\\test\\Dogimage.jpeg";
	    Mat src = Imgcodecs.imread(path);
	    Mat dst = new Mat();
	    Core.flip(src, dst, flipDirection);
	    
	    return getByteArrayFromMat(dst);
	}
	
	public byte[] rotateDegreeImage(String path, int degree) {
		
		Mat src = Imgcodecs.imread(path);

	    Mat dst = new Mat();
	   
	    Mat rotationMatrix = Imgproc.getRotationMatrix2D(new Point(src.width()/2, src.height()/2), degree, 1);
	
	    //Rotating the given image
	    Imgproc.warpAffine(src, dst,rotationMatrix, new Size(src.width(), src.height()));

	    return getByteArrayFromMat(dst);
	}
	
	public byte[] rotateImage(String path, String direction) {
		
		// input buffered image object 
	    Mat src = Imgcodecs.imread(path);
	    Mat dst = new Mat();
	    if(direction.equalsIgnoreCase("left")) {
	    	Core.rotate(src, dst, Core.ROTATE_90_COUNTERCLOCKWISE);
	    }
	    else if(direction.equalsIgnoreCase("right"))
	    {
	    	Core.rotate(src, dst, Core.ROTATE_90_CLOCKWISE);
	    }
	    return getByteArrayFromMat(dst);
	}
	
	public byte[] resizeImage(String path, int height, int width) {
		//String file = "C:\\test\\Dogimage.jpeg";
		Mat src = Imgcodecs.imread(path);
		Mat resizeimage = new Mat();
		Size scaleSize = new Size(width,height);
	    Imgproc.resize(src, resizeimage, scaleSize , 0, 0, Imgproc.INTER_AREA);
	    // Writing the image 
	    return getByteArrayFromMat(resizeimage);

	}
	
	public byte[] grayScaleImage(String path) {

		// To Read the image 
		 Mat mat = Imgcodecs.imread(path); 
		 
	    // creating a new mat object and putting the modified input mat object by using flip() 
	    Mat dst = new Mat();
	    
        // Converting the image to gray scale and  
        // saving it in the dst matrix 
	    Imgproc.cvtColor(mat, dst, Imgproc.COLOR_RGB2GRAY); 
  
	    try {
	    	MatOfByte mob=new MatOfByte();
	    	Imgcodecs.imencode(".jpg", dst, mob);
	    	byte ba[]=mob.toArray();
	    	BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(ba)); 
	    
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	ImageIO.write(image1, "png", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	    return new byte[] {};
	}
	
	
	public void resize(int height, int width) {
		String file = "C:\\test\\Dogimage.jpeg";
		Mat src = Imgcodecs.imread(file);
		Mat resizeimage = new Mat();
		Size scaleSize = new Size(height,width);
	    Imgproc.resize(src, resizeimage, scaleSize , 0, 0, Imgproc.INTER_AREA);
	    // Writing the image 
        Imgcodecs.imwrite("C:\\test\\resized_output.jpg", resizeimage);
	    System.out.println("Image Resized");
	}
	
	public void rotate(String direction) {
		// input buffered image object 
	   // File input = new File("E:\\test.jpg"); 
	    String file = "C:\\test\\Dogimage.jpeg";
	    Mat src = Imgcodecs.imread(file);
	    Mat dst = new Mat();
	    if(direction.equalsIgnoreCase("left")) {
	    	Core.rotate(src, dst, Core.ROTATE_90_COUNTERCLOCKWISE);
	    	//Core.flip(src, dst, flipDirection);
	    }
	    else if(direction.equalsIgnoreCase("right"))
	    {
	    	Core.rotate(src, dst, Core.ROTATE_90_CLOCKWISE);
	    }
	    System.out.println("Des size : " + dst.cols());
	    //Writing the image
	    
	    String outputFileName = "flip_output_" + direction +".jpg";
	    Imgcodecs.imwrite("C:\\test\\" + outputFileName, dst);
	    System.out.println("Image Rotated");
	}
	
	/*public void test() {
		/*byte[] data = ((DataBufferByte) image1.getRaster().getDataBuffer()).getData(); 
	    Mat newDst = new Mat(image1.getHeight(),image1.getWidth(),CvType.CV_8UC3); 
	    newDst.put(0, 0, data);
	    Imgcodecs.imwrite("C:\\test\\grayscaledimage_old_output.jpg", dst);
	    
	    Imgcodecs.imwrite("C:\\test\\grayscaledimage_output.jpg", newDst);
	
	// converting the newly created mat object to buffered image object 
    //byte[] newData = new byte[dst.rows()*dst.cols()*(int)(dst.elemSize())]; 
    //dst.get(0, 0, newData); 
    //BufferedImage image1 = new BufferedImage(dst.cols(), dst.rows(), 5); 
    //image1.getRaster().setDataElements(0,0,dst.cols(),dst.rows(),newData); 
    
    /*String file = "C:\\test\\Dogimage.jpeg";
	    
		File input = new File(path); 
	    BufferedImage image = null;
		try {
			image = ImageIO.read(input);
			System.out.println(image.getType());
			image.getColorModel();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	  
	    // converting buffered image object to mat object 
	    //byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData(); 
	    //Mat mat = new Mat(image.getHeight(),image.getWidth());//,CvType.CV_8UC3); 
	    //mat.put(0, 0, data); 
	    
	}*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
