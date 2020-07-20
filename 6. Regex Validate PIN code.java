 /* 
ATM machines allow 4 or 6 digit PIN codes and PIN codes cannot contain anything but exactly 4 digits or exactly 6 digits.

If the function is passed a valid PIN string, return true, else return false.

eg:

Solution.validatePin("1234") === true
Solution.validatePin("12345") === false
Solution.validatePin("a234") === false
 */
 import java.util.regex.Pattern;

 class Solution {
    public static boolean validatePin(String pin) {
       
            return Pattern.compile("\\d{4}|\\d{6}").matcher(pin).matches();
    }

    public static void main (String [] args){
        String pin = "111111";
        System.out.println(validatePin(pin));

    }
}