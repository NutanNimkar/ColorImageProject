import java.io.File;
import java.util.Arrays;
import java.util.Collections;

/**
*
* CSI 2120 Project
* Class for Color Image 
* @author Mohammed Shakir and Nutan Nimkar
*
*/
public class SimilaritySearch {
    public static void main(String[] args){
        if( args.length != 2){
            System.out.println("java SimilaritySearch image_name.ppm dataset_dir");
        }

        String imageFileName = args[0];
        String datasetDir = args[2];

        ColorImage colorImage = new ColorImage(imageFileName);
        colorImage.reduceColor(3);
        int d = colorImage.getDepth();
        ColorHistogram colorHistogram = new ColorHistogram(d);
        colorHistogram.setImage(colorImage);

        File datasetDirectory = new File(datasetDir);
        File[] files = datasetDirectory.listFiles();

        String[] filenames = new String[files.length];
        double[] comparison_res = new double[files.length];

        for(int i = 0; i < files.length; i++){
            if(files[i].isFile() && files[i].getName().toLowerCase().endsWith("jpg.txt")){
            
                ColorHistogram datasetHistogram = new ColorHistogram(files[i].getName());

                double comparison_value= colorHistogram.compare(datasetHistogram);

                filenames[i] = files[i].getName();
                comparison_res[i] = comparison_value;
            }

        }

        for(int i = 0; i < files.length; i++){
            for(int j = 0; j < files.length; j++){
                if( comparison_res[j] > comparison_res[i]){
                    double temp = comparison_res[i];
                    comparison_res[i] = comparison_res[j];
                    comparison_res[j] = temp;

                    String tempFilename = filenames[i];
                    filenames[i] = filenames[j];
                    filenames[j] = tempFilename;

                }
            }
        }

        for (int i = 0; i < 5; i++){
            System.out.println("Filename: " + filenames[i] + "comparison_score" + comparison_res[i]);
        }
        
        // colorHistogramTwo call constructor with filename to convert it to histogram objects
        //go through all files jpg.txt
        //array of histogram objects
        // stores filename
        //output of the comparison 
        // array doubles answer of the comparison
        //



        // if filename jpg in it: 
        // you can convert to Histogram using setImage

        


    }
}
