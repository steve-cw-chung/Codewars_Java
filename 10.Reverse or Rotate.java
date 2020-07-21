/*

The input is a string str of digits. Cut the string into chunks (a chunk here is a substring of the initial string) of size sz (ignore the last chunk if its size is less than sz).

If a chunk represents an integer such as the sum of the cubes of its digits is divisible by 2, reverse that chunk; otherwise rotate it to the left by one position. Put together these modified chunks and return the result as a string.

If

sz is <= 0 or if str is empty return ""
sz is greater (>) than the length of str it is impossible to take a chunk of size sz hence return "".
Examples:
revrot("123456987654", 6) --> "234561876549"
revrot("123456987653", 6) --> "234561356789"
revrot("66443875", 4) --> "44668753"
revrot("66443875", 8) --> "64438756"
revrot("664438769", 8) --> "67834466"
revrot("123456779", 8) --> "23456771"
revrot("", 8) --> ""
revrot("123456779", 0) --> "" 
revrot("563000655734469485", 4) --> "0365065073456944"
*/


class RevRot {
    
    public static String revRot(String strng, int sz) {
      String retVal = "";
      int total = 0;
      String subs = "";
      char [] c; 
      int val = 0;
      char first ;
      if (sz <= 0 | strng.length() == 0 | sz > strng.length()){
        return "";
      } else {
        for(int i = 0 ; i< strng.length()-sz+1;i+=sz){
          System.out.println(strng.substring(i,i+sz));
          subs = strng.substring(i,i+sz);
          c= subs.toCharArray();
          for(int j = 0 ; j < subs.length(); j++){
            val = c[j];
            //System.out.println(val-48);
            val -= 48;
            val = val * val * val;
            total += val;
          }
          if (total % 2 == 0 ) {
            for(int j = subs.length()-1 ; j >= 0; j-- ){
              retVal += c[j];
            }  
          } else {
            for(int j = 0; j < subs.length(); j ++ ){
              if( j == 0 ){
                first = c[j];
                retVal += c[j+1];
              } else if ( j < subs.length()-1) {
                retVal += c[j+1];
              } else {
                retVal += c[0];
              }
              
            }
          }
          total = 0;
        }
       
        //System.out.println(strng.substring(0, sz));
      }
        return retVal;
  }
}

/* Solution 1 

class RevRot {

    public static String revRot(String nums, int sz) {
        StringBuffer groups = new StringBuffer();
        for (int i = 0, len = nums.length(); i + sz <= len && sz > 0; i += sz) {
            String group = nums.substring(i, i + sz);
            groups.append(isDivisible(group) ? new StringBuffer(group).reverse() : group.substring(1) + group.charAt(0));
        }
        return groups.toString();  
    }
    
    public static boolean isDivisible(String group) {
        int sum = 0;
        for (char num : group.toCharArray()) {
            sum += Character.getNumericValue(num);
        }
        return sum % 2 == 0;
    }

}
*/

/* Solution 2 

class RevRot {
    
    public static String revRot(String strng, int sz) {
        if ((sz==0) || (strng == "") || (sz > strng.length())) return "";  // INVALID CASES -> Return empty string
    
        String result = "";                                                // this string will contain the result to be returned
        
        for (int i = 0; i < (strng.length()/sz); i++){                     // for each chunk of sz elements
            String chunk = strng.substring(i*sz, i*sz + sz);
            int digitSum = 0;
                                                                           // Because our final goal is to determine if the digtSum is odd or even
            for (int j = 0; j < sz; j++){                                  // There is no need of summing the cubes of the numbers 
                digitSum += Character.getNumericValue(chunk.charAt(j));    // odd^n remains odd, even^n remains even 
            }                                                              // so the proportion of even and odd numbers remains the same 
            
            if ((digitSum % 2) == 0){                                      // REVERSE CHUNK
                for (int k = sz-1; k >= 0; k--){                           //   by adding every character of the chunk to the result from right to left
                    result += chunk.charAt(k);                               
                }
            }else{                                                         // ROTATE CHUNK TO THE LEFT BY ONE POSITION
                result += chunk.substring(1) + chunk.charAt(0);            //   by adding the chunk to the result with its first character in the end
            }            
        }
        
        return result;
    }
}

*/

/* Solution 3 

class RevRot {
    
    public static String revRot(String str, int sz){
        
        if(sz <= 0 || str.isEmpty()) return "";
        
        int part = str.length() / sz;
        if(part < 1) return "";
        
        String retVal = "";        
        for(int i = 0; i < part; i++){
            String chunk = str.substring(i * sz, i * sz + sz);
           
            int digitSum = getDigitSum(chunk);
            retVal += isEven(digitSum) ? reverseString(chunk) : rotate(chunk);
        }
        
        return retVal;
    }
    
    private static int getDigitSum(String n){
        int sum = 0;
        int length = n.length();

        for(int i = 0; i < length; i++)
          sum += Integer.parseInt(String.valueOf(n.charAt(i)));
        
        return sum;
    }
    
    private static boolean isEven(int n){
        return n % 2 ==0;
    }
    
    private static String rotate(String str){
        String retVal = str;       
        
        return retVal.substring(1) + retVal.substring(0, 1);
    }
    
    private static String reverseString(String str){
        String retVal = "";
        int length = str.length();
        
        for(int i = length - 1; i >= 0; i--)
            retVal += str.charAt(i);
        return retVal;
    }
} */