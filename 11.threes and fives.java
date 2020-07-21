 /* If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number passed in.

Note: If the number is a multiple of both 3 and 5, only count it once.
*/
/* My Solution 
 class Solution {

    public int solution(int number) {
      //TODO: Code stuff here
      int threes = 3;
      int fives = 5;
      int [] numbers = new int [100];
      int i =0;
      int retVal = 0;
      while ( threes < number ){
        
        numbers[i++] = threes;
       
        threes += 3;
      }
      while (fives < number){
        if (fives%3 == 0 ){
        }else {
        numbers[i++] = fives;
        }
        fives += 5;
      }
      i = 0;
      System.out.println(number);
      while (numbers[i]!= 0){
        System.out.println(numbers[i]);
        
        retVal += numbers[i++];
        
      }
      
      return retVal;
    }
  }
*/
  /* Solution 1 

  public class Solution {

  public int solution(int number) {
    int sum=0;
    
    for (int i=0; i < number; i++){
      if (i%3==0 || i%5==0){sum+=i;}
    }
    return sum;
  }
}
*/

/* Solution 2 

import java.util.stream.IntStream;

public class Solution {
  public int solution(int number) {
    return IntStream.range(0, number)
                    .filter(n -> (n % 3 == 0) || (n % 5 == 0))
                    .sum();
  }
}
*/

/* Solution 3 
import java.lang.Math;
public class Solution {
  public int solution(int n) {
    int a = (int) Math.ceil(n/3d) - 1;
    int b = (int) Math.ceil(n/5d) - 1;
    int c = (int) Math.ceil(n/15d) - 1;
    return (3 * a * (a+1) + 5 * b * (b+1) - 15 * c * (c + 1)) / 2;
  }
}

*/
/*
    The sum of multiples of 3 is 3 + 6 + 9 + ... = 3 (1+2+3+...)
    The sum of mulitples of 5 is 5 + 10 + 15 + ... = 5 (1+2+3+...)
    If we just sum these, we'll get double values when a number is divisble by both,
    so we substract the sum of multiples of 15, which is obtained in a similar manner.
    The upper bound cannot be floor function because the inputed number shouldn't count.
*/

