/* Write a function, persistence, that takes in a positive parameter num and returns its multiplicative persistence, which is the number of times you must multiply the digits in num until you reach a single digit.

For example:

 persistence(39) == 3 // because 3*9 = 27, 2*7 = 14, 1*4=4
                      // and 4 has only one digit

 persistence(999) == 4 // because 9*9*9 = 729, 7*2*9 = 126,
                       // 1*2*6 = 12, and finally 1*2 = 2

 persistence(4) == 0 // because 4 is already a one-digit number */
public class Persist {
    public static int persistence(long n) {
        String s = Long.toString(n);
        char [] c = s.toCharArray();
        long total = 1;
        int retVal = 0;
  
        while(c.length != 1){
        
        for(int i = 0; i< c.length; i++){
  
            total *= (c[i]-48);

        }
        s = Long.toString(total);
        c = s.toCharArray();
        total = 1;
        retVal++;
        }
        
        return retVal;
    }
    
    public static void main (String [] args) {
        System.out.println(persistence(39));
    }
    
}

/* Solution 1 

class Persist {
  public static int persistence(long n) {
    long m = 1, r = n;

    if (r / 10 == 0)
      return 0;

    for (r = n; r != 0; r /= 10)
      m *= r % 10;

    return persistence(m) + 1;
    
  }
}

*/

/* Solution 2 
class Persist {
  public static int persistence(long n) {
    int times = 0;
    while (n >= 10) {
      n = Long.toString(n).chars().reduce(1, (r, i) -> r * (i - '0'));
      times++;
    }
    return times;
  }
}

/* Solution 3 

class Persist {
   /**
     * given a positive integer produce its multiplicative persistence
     * @param n a positive integer
     * @return the multiplicative persistence of n
     */
  /*
     public static int persistence(long n) {
        if (n < 10) {
            return 0;
        }
        return 1 + persistence(multiplyDigits(n));
    }
    /**
     * given an integer produce the product of the given integers digits.
     * example: multiplyDigits(785) = 7 * 8 * 5 = 280
     * @param n
     * @return the product of the digits that comprise n
     */
    /*
     private static long multiplyDigits(long n) {
        if (n < 10) {
            return n;
        }
        return n % 10 * multiplyDigits(n / 10);
    }
}

/* Solution 4

import java.util.Arrays;

class Persist {

    public static int persistence(long n) {
        if (n < 10) return 0;

        final long newN = Arrays.stream(String.valueOf(n).split(""))
                .mapToLong(Long::valueOf)
                .reduce(1, (acc, next) -> acc * next);

        return persistence(newN) + 1;
    }
}
*/

*/

*/