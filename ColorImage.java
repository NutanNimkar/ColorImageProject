/**
*
* CSI 2120 Project
* Class for Color Image 
* @author Mohammed Shakir and Nutan Nimkar
*
*/
import java.io.*;
import java.util.*;

public class ColorImage{

	public int[][][] pixelArr;
	private int width;
	private int height;
	private int depth;

	public ColorImage(String filename){

		try{
			File image = new File(filename); // open file, correct file namw will be check in main class 
			Scanner img = new Scanner(image); 

			for(int i = 0; i < 2; i++){ // skip first 2 lines 
				img.nextLine();
			}

			width = img.nextInt();
			height = img.nextInt();
			depth = (int) ((Math.log(img.nextInt() + 1))/Math.log(2));

			pixelArr = new int[height][width][3];
			
			for (int i = 0; i < height; i++){	
				for (int j = 0; j < width; j++){
					for (int k = 0; k < 3; k++){
						if (img.hasNext()){
							pixelArr[i][j][k] = img.nextInt(); // store pixel in a 2D Array 
						}
					}
				}
			}
			img.close();
		}
		catch (FileNotFoundException e){
			System.out.println(e);
		}
	}

	public int getWidth(){
		return width;
	}

	public int getheight(){
		return height;
	}

	public int getdepth(){
		return depth;
	}

	public int[3] getPixel(int i, int j){

	}

	public void reduceColor(int d){
		
	}

	// just for testing 
	public static void main(String[] args) {
		
		ColorImage a = new ColorImage("q00.ppm");

		for (int i = 0; i < a.pixelArr[0][0].length; i++){
			System.out.println(a.pixelArr[0][1][i]);
		}
	}
}