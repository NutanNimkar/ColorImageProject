/**
*
* CSI 2120 Project
* Class for Color Image 
* @author Mohammed Shakir - 300100792
*         Nutan Nimkar - 300127333
*
*/

import java.io.*;
import java.util.*;

public class SimilaritySearch {
    public static void main(String[] args){
        if( args.length != 2){
            System.out.println("java SimilaritySearch image_name.ppm dataset_dir");
        }

        String imageFileName = args[0];
        String datasetDir = args[1];

        ColorImage colorImage = new ColorImage(imageFileName);
        colorImage.reduceColor(3);
        int d = colorImage.getDepth();
        ColorHistogram colorHistogram = new ColorHistogram(d);
        colorHistogram.setImage(colorImage);

        File datasetDirectory = new File(datasetDir);
        File[] files = datasetDirectory.listFiles();

        ArrayList<String> filenames = new ArrayList<String>();
        ArrayList<Double> comparison_res = new ArrayList<Double>();

        for(int i = 0; i < files.length; i++){
            
            if(files[i].isFile() && files[i].getName().endsWith(".txt")){
                
                ColorHistogram datasetHistogram = new ColorHistogram(files[i].getPath());

                double comparison_value = colorHistogram.compare(datasetHistogram);

                filenames.add(files[i].getName());
                comparison_res.add(comparison_value);  
            }
        }

        for(int i = 0; i < filenames.size(); i++){
            for(int j = 0; j < filenames.size(); j++){
                if( comparison_res.get(j) < comparison_res.get(i)){

                    double temp = comparison_res.get(i);
                    comparison_res.set(i, comparison_res.get(j));
                    comparison_res.set(j, temp);

                    String tempFilename = filenames.get(i);
                    filenames.set(i, filenames.get(j));
                    filenames.set(j, tempFilename);
                }
            }
        }

        for (int i = 0; i < 5; i++){
            System.out.println( (i+1) + "- Filename: " + filenames.get(i) + ", Comparison Score: " + comparison_res.get(i));
        }      
    }
}
