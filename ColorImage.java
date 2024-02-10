/**
*
* CSI 2120 Project
* Class for Color Image 
* @author Mohammed Shakir - 300100792
* 		  Nutan Namkir - 300127333
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

	public int getHeight(){
		return height;
	}

	public int getDepth(){
		return depth;
	}

	public int[] getPixel(int i, int j){
		return pixelArr[i][j];
	}

	public void reduceColor(int d){
		for (int i = 0; i < height; i++){	
			for (int j = 0; j < width; j++){
				for (int k = 0; k < 3; k++){
					pixelArr[i][j][k] = pixelArr[i][j][k] >> (depth-d);						
				}
			}
		}
		depth = d;
	}
}
