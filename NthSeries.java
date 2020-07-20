/* Task:
Your task is to write a function which returns the sum of following series upto nth term(parameter).

Series: 1 + 1/4 + 1/7 + 1/10 + 1/13 + 1/16 +...
Rules:
You need to round the answer to 2 decimal places and return it as String.

If the given value is 0 then it should return 0.00

You will only be given Natural Numbers as arguments.

Examples:
SeriesSum(1) => 1 = "1.00"
SeriesSum(2) => 1 + 1/4 = "1.25"
SeriesSum(5) => 1 + 1/4 + 1/7 + 1/10 + 1/13 = "1.57"
NOTE: In PHP the function is called series_sum(). */

public class NthSeries {
    public static String seriesSum(int n) {
        // Happy Coding ^_^		
        
        String retVal = "";
        float denominator = -2;
        float sum = 0;
        for (int i = 0 ; i < n ; i++){
            denominator += 3;
            sum += 1/denominator;
        }
        System.out.println(sum);
        sum *= 100;
        sum = Math.round(sum);
        sum /= 100;
        retVal = String.valueOf(sum);
        retVal += "0";
        retVal = retVal.substring(0,4);
        return retVal;
    }
    public static void main(String[] args){
        System.out.println(seriesSum(5));
      }
}

/* Solution 1 

public class NthSeries {
  
  public static String seriesSum(int n) {
    
    double sum = 0.0;
    for (int i = 0; i < n; i++)
      sum += 1.0 / (1 + 3 * i);
    
    return String.format("%.2f", sum);
    
  }
} */


/* Solution 2 

import java.util.stream.IntStream;

public class NthSeries {
  
  public static String seriesSum(int n) {
        return String.format("%.2f", IntStream.range(0, n).mapToDouble(num -> 1.0 / (1 + num * 3)).sum());
    }
} */

/* Solution 3 

public class NthSeries {
  
  public static String seriesSum(int n) {
    double sum = 0;
    for (int i = 1; i < n*3; i+=3) {
      sum += 1.0/i;
    }
    return String.format("%.2f", sum);
  }
} */