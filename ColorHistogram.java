/**
*
* CSI 2120 Project
* Class for Color Histogram 
* @author Mohammed Shakir 
*
*/
import java.io.*;
import java.util.*;

public class ColorHistogram{
	
	private int[] hist;

	public ColorHistogram(int d){
		hist = new int[(int) Math.pow(2, d*3)];
	}

	public ColorHistogram(String filename){
		try{
			Scanner hist = new Scanner(new File(filename)); //Open and read file 
			this.hist = new int[hist.nextInt()];
			int i = 0;
			while (hist.hasNextInt()){
				this.hist[i] = hist.nextInt();
				i++;
			}
		}
		catch (IOException e){
			System.out.println("File not found!");
		}
	}

	public void setImage(ColorImage image){
		int d, r, g, b, index;
		d = image.getDepth();
		for (int i = 0; i < image.getHeight(); i++){
			for (int j = 0; j < image.getWidth(); j++){
				r = image.getPixel(i, j)[0];
				g = image.getPixel(i, j)[1];
				b = image.getPixel(i, j)[2];
				index = ((r << (2 * d)) + (g << d) + b);
				hist[index]++;
			}
		}
	}

	public double[] getHistogram(){
		double [] result = new double[hist.length];
		double total = 0;
		for (int i = 0; i < hist.length; i++){ //get total number of pixels
			total += hist[i];
		}
		for (int j = 0; j < result.length; j++){
			result[j] = ((double) hist[j]/total);
		}
		return result;
	}

	public double compare(ColorHistogram hist){
		double[] h1 = this.getHistogram();
		double[] h2 = hist.getHistogram();
		int len;
		double result = 0.0;
		if (h1.length < h2.length){
			len = h1.length;
		}
		else{
			len = h2.length;
		}	
		for(int i = 0; i < len; i++){
			result += Math.min(h1[i], h2[i]);
		}
		return result;
	}

	public void save(String filename){

		try{
			FileWriter output = new FileWriter(new File(filename));
			output.write(hist.length + "\n");
			for (int i = 0; i < hist.length; i++){
				output.write(hist[i] + " ");
			}
			output.close();
		}
		catch (IOException e){
			System.out.println("Error! File Could not be created.");
		}
	}

	public static void main(String[] args) {

		ColorImage c = new ColorImage("q00.ppm");
		c.reduceColor(3);

		ColorHistogram h = new ColorHistogram("25.jpg.txt");
		ColorHistogram h1 = new ColorHistogram(c.getDepth()); 

		h1.setImage(c);
		h1.save("test1.txt");

		System.out.println(h.compare(h1)); // higher number -> more similar		
	}

}