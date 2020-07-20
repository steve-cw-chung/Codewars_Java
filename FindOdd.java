
/* Given an array, find the integer that appears an odd number of times.

There will always be only one integer that appears an odd number of times. */

import java.util.HashMap;
//import java.util.Map;

public class FindOdd {
	public static int findIt(int[] a) {
        
        HashMap<Integer,Integer> map= new HashMap<Integer,Integer>();

        
        for(int i = 0; i<a.length; i++ ){
            if(!map.containsKey(a[i])){
                map.put(a[i],1);
            }else{
                map.replace(a[i],map.get(a[i])+1);
            }
        }
        for(int i = 0; i<a.length; i++){
            if(map.get(a[i])%2==0){
                return a[i];
            }
        }
        return 0;
  }
  public static void main(String[] args){
    int [] a = {0,1,2,3,4,4};  
    System.out.println(findIt(a));
  }
}
/* Solution 1 

public class FindOdd {
  public static int findIt(int[] A) {
    int xor = 0;
    for (int i = 0; i < A.length; i++) {
      xor ^= A[i];
    }
    return xor;
  }
}
*/

/* Solution 2 

import static java.util.Arrays.stream;

public class FindOdd {
  public static int findIt(int[] arr) {
    return stream(arr).reduce(0, (x, y) -> x ^ y);
  }
}

/* Solution 3

public class FindOdd {
  public static int findIt(int[] A) {
    int odd=0;
    for (int item: A)
      {
        odd = odd ^ item;// XOR will cancel out everytime you XOR with the same number so 1^1=0 but 1^1^1=1 so every pair should cancel out leaving the odd number out
      }
    
    return odd;
  }
}

*/

/* Solution 4 

public class FindOdd {
  public static int findIt(int[] A) {
    int odd = 0;
    
    for (int i : A) {
      odd ^= i;
    }
  
    return odd;
  }
}

*/