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
    private final BigDecimal negOne = new BigDecimal(-1);
    private static BigDecimal[] x_values;
    private static BigDecimal[] y_values;
    private static BigDecimal xMean;
    private static BigDecimal xSqrdMean;
    private static BigDecimal yMean;
    private static BigDecimal xyMean;
    private static BigDecimal slope;
    private static BigDecimal yIntercept;

    public static void main(String [] args) throws IOException {
        y_values = new BigDecimal[100];
        x_values = new BigDecimal[100];
        int counter = 0;
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("ROI_dataset.txt")));
            //data is separated by commas
            s.useDelimiter(",\\s*");
            
            DecimalFormat df = new DecimalFormat("#.00");
            while(s.hasNext()) {
                x_values[counter] = new BigDecimal(s.next());
                y_values[counter] = new BigDecimal(s.next());

//                System.out.print(" x: " + df.format(x_values[counter].doubleValue()) + 
//                                 " y: " + df.format(y_values[counter].doubleValue()));
                counter +=1;
            }
        } finally {
            if(s !=null) {
                s.close();
            }
        }
        
        if(x_values.length != 0) {
            xMean = calculate_mean(x_values, false);
            yMean = calculate_mean(y_values, false);
            xSqrdMean = calculate_mean(x_values, true);
            calculate_xy_mean();
            calc_slope();
            calc_yIntercept();

            System.out.println("Your xMean is: " + xMean);
            System.out.println("Your xSqrdMean is: " + xSqrdMean);
            System.out.println("Your yMean is: " + yMean);
            System.out.println("Your xyMean is: " + xyMean);
            System.out.println("Your slope is: " + slope);
            System.out.println("Your y-intercept is: " + yIntercept);
            System.out.println("Your best fit line equation is: y = " + slope.setScale(3, BigDecimal.ROUND_CEILING) + "x " + 
                               ((((yIntercept).compareTo(BigDecimal.ZERO)) == -1) ? " - " : " + " ) + 
                               yIntercept.setScale(3, BigDecimal.ROUND_CEILING));
        }
    }

    public static BigDecimal calculate_mean(BigDecimal[] axisValues, boolean isSquaredMean){
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal size = new BigDecimal(axisValues.length);

        if(isSquaredMean){
            for(BigDecimal value : axisValues){
                sum = sum.add(value.multiply(value));
            }
        } else {
            for(BigDecimal value : axisValues){
                sum = sum.add(value);
            }
        }
        return sum.divide(size, 4, BigDecimal.ROUND_CEILING);
    }
    
    public static void calculate_xy_mean(){
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal size = new BigDecimal(x_values.length);
        
        for(int i = 0; i < x_values.length; i++){
            sum = sum.add(x_values[i].multiply(y_values[i]));
        }
 
        xyMean = sum.divide(size, 4, BigDecimal.ROUND_CEILING);
    }
    
    public static void calc_slope(){
        //        x_ y_ - xy_
        //  m = ---------------
        //      (x_)^2 - (x^2)_
 
        BigDecimal numerator = (xMean.multiply(yMean)).subtract(xyMean);
        slope = numerator;
        BigDecimal denominator = (xMean.multiply(xMean)).subtract(xSqrdMean);
        
        if( denominator.compareTo(BigDecimal.ZERO) != 0 ) {
            slope = slope.divide(denominator, 4,BigDecimal.ROUND_CEILING);
        } else {
            System.out.println("Something went wrong. Denominator is zero. Slope is set to 0");
            slope = BigDecimal.ZERO;
        }
    }

    public static void calc_yIntercept(){
        //b = y_ - mx_
        yIntercept = yMean.subtract(slope.multiply(xMean));
    }
}
