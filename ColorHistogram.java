/**
*
* CSI 2120 Project
* Class for Color Histogram 
* @author Mohammed Shakir 
*
*/

public class ColorHistogram{
	
	private double[] hist;
//	private ColorImage image;

	public ColorHistogram(int d){
		hist = new double[(int) Math.pow(2, d*3)];
		System.out.println(hist.length);
	}

	public ColorHistogram(String filename){
	}

/*	public void setImage(ColorImage image){
		this.image = image;
	}
*/
	public double[] getHistogram(){
		double [] result = new double[hist.length];

		for (int i = 0; i < result.length; i++){
			result[i] = (hist[i]/hist.length);
		}
		return result;
	}

	public double compare(ColorHistogram hist){

		double[] h1 = getHistogram();
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
	}

	public static void main(String[] args) {

		ColorHistogram h = new ColorHistogram(3); 
		
	}

}