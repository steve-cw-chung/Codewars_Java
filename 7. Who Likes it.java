class likes {
    public static String whoLikesIt(String... names){
        String name1 = "";
        String name2 = "";
        String name3 = "";
        int count = 0;
        for (String name : names ) {
          if ( count == 0 ){
            name1 = name;
          } else if ( count == 1 ) {
            name2 = name;
          } else if (count == 2 ){
            name3 = name;
          } 
          count++;
        }
        if (count == 0){
          return "no one likes this";
        } else if ( count == 1){
          return name1 +" likes this";
        } else if ( count == 2 ){
          return name1 + " and " + name2 + " like this";
        } else if (count == 3){
          return name1 + ", "+ name2 + " and " + name3 + " like this";
        } else {
          return name1 + ", " + name2 + " and " + (count-2) + " others like this";
        }
    }
    public static void main (String [] args) {

    }    
}

/* Solution 1

class Solution {
    public static String whoLikesIt(String... names) {
        switch (names.length) {
          case 0: return "no one likes this";
          case 1: return String.format("%s likes this", names[0]);
          case 2: return String.format("%s and %s like this", names[0], names[1]);
          case 3: return String.format("%s, %s and %s like this", names[0], names[1], names[2]);
          default: return String.format("%s, %s and %d others like this", names[0], names[1], names.length - 2);
        }
    }
}
*/

/* Solution 2 
class Solution {
  public static String whoLikesIt(String... names) {
    final String Template1 = "%s likes this";
    final String Template2 = "%s and %s like this";
    final String Template3 = "%s, %s and %s like this";
    final String TemplateN = "%s, %s and %d others like this";
    return
        names.length == 0 ? "no one likes this" :
        names.length == 1 ? String.format(Template1, names[0]) :
        names.length == 2 ? String.format(Template2, names[0], names[1]) :
        names.length == 3 ? String.format(Template3, names[0], names[1], names[2]) :
        String.format(TemplateN, names[0], names[1], names.length-2);
  }
}
*/

/* Solution 3

class Solution {
    public static String whoLikesIt(String... nms) {
        switch (nms.length) {
          case 0: return "no one likes this";
          case 1: return String.format("%s likes this", nms[0]);
          case 2: return String.format("%s and %s like this", nms[0], nms[1]);
          case 3: return String.format("%s, %s and %s like this", nms[0], nms[1], nms[2]);
          default: return String.format("%s, %s and %d others like this", nms[0], nms[1], nms.length - 2);
        }
    }
}
*/

/* Solution 4 
import java.util.HashMap;
import java.util.Map;

class Solution {

    private static Map<Integer, String> choices = new HashMap<>();

    static {
        choices.put(0, "no one likes this");
        choices.put(1, "%s likes this");
        choices.put(2, "%s and %s like this");
        choices.put(3, "%s, %s and %s like this");
    }

    public static String whoLikesIt(String... names) {
        int namesCount = names.length;
        return namesCount <= 3 ?
                String.format(choices.get(namesCount), names) :
                String.format("%s, %s and %s others like this", names[0], names[1], namesCount - 2);
    }
}

*/