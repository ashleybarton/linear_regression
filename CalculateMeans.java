// Ashley Barton
//
// Introductary Linear Regression
//
// b = y_ - m*x_
//
// m = (x_ y_ - xy_) / (x_)^2 - x^2_
//
// Dataset taken from: http://www.payscale.com/college-roi/
//
// To find our best fit line,
// we need to calculate the slope (m)
// as well as our mean values
//

import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.math.BigDecimal;

public class CalculateMeans {

    public static void main(String [] args) throws IOException {
        BigDecimal[] y_values = new BigDecimal[100];
        BigDecimal[] x_values = new BigDecimal[100];
        DecimalFormat df = new DecimalFormat("#.00");
        int counter = 0;
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("ROI_dataset.txt")));
            //data is separated by commas
            s.useDelimiter(",\\s*");
            
          while(s.hasNext()) {
                x_values[counter] = new BigDecimal(s.next());
                y_values[counter] = new BigDecimal(s.next());

                System.out.print(" x: " + df.format(x_values[counter].doubleValue()) + 
                                 " y: " + df.format(y_values[counter].doubleValue()));
                counter +=1;
                System.out.println();
            }
        } finally {
            if(s !=null) {
                s.close();
            }
        }
    }

    public static double calculate_x_mean(double[] x_values){
        return 0.0;   
    }

    public static double calculate_y_mean(double[] y_values){
        return 0.0;
    }
    
    public static double calculate_xy_mean(double[] y_values){
        return 0.0;
    }

    public static double find_slope(double xyMean, double xMean, double yMean){
        return 0.0;
    }
}
