/* Usually when you buy something, you're asked whether your credit card number, phone number or answer to your most secret question is still correct. However, since someone could look over your shoulder, you don't want that shown on your screen. Instead, we mask it.

Your task is to write a function maskify, which changes all but the last four characters into '#'.

Examples
Maskify.Maskify("4556364607935616"); // should return "############5616"
Maskify.Maskify("64607935616");      // should return "#######5616"
Maskify.Maskify("1");                // should return "1"
Maskify.Maskify("");                 // should return ""

// "What was the name of your first pet?"
Maskify.Maskify("Skippy");                                   // should return "##ippy"
Maskify.Maskify("Nananananananananananananananana Batman!"); // should return "####################################man!" */

class Maskify {
    public static String maskify(String str) {
        String returnVal = ""; 
        //throw new UnsupportedOperationException("Unimplemented");
        for(int i = 0; i < str.length()-4; i++){
            System.out.println(str.charAt(i));
            returnVal += "#";
            
        }
        if(str.length() > 3) {
            returnVal += str.charAt(str.length()-4);
            returnVal += str.charAt(str.length()-3);
            returnVal += str.charAt(str.length()-2);
            returnVal += str.charAt(str.length()-1);
        }else if (str.length() ==3){
            returnVal += str.charAt(str.length()-3);
            returnVal += str.charAt(str.length()-2);
            returnVal += str.charAt(str.length()-1);
        } else if (str.length() == 2){
            returnVal += str.charAt(str.length()-2);
            returnVal += str.charAt(str.length()-1);
        } else if (str.length() == 1){
            returnVal += str.charAt(str.length()-1);
        }
        return returnVal;
    }
}

class Test{
    public static void main(String[] args){
        System.out.println("in Test class");
        
        System.out.println(Maskify.maskify("hello"));
    }
}

/* Solution 1 */
public class Maskify {
    public static String maskify(String str) {
        if (str.length() <= 4) return str;
        String result = "";
        for (int i = 0; i < str.length()-4; i++) {
            result += "#";
        }
        return result + str.substring(str.length()-4);
    }
}

/* Solution 2 */
public class Maskify {
    public static String maskify(String str) {
        return str.replaceAll(".(?=.{4})", "#");
    }
}

/* Solution 3 */

public class Maskify {
    public static String maskify(String str) {
      char[] strChars = str.toCharArray();
      for( int i = 0; i < strChars.length - 4; i++ ) {
        strChars[i] = '#';
      }
      return new String(strChars);
    }
  }