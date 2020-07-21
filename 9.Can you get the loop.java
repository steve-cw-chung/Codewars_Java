/*You are given a node that is the beginning of a linked list. This list always contains a tail and a loop.

Your objective is to determine the length of the loop.

For example in the following picture the tail's size is 3 and the loop size is 11.

Image and video hosting by TinyPic
// Use the `getNext()` method to get the following node.

node.getNext()
Note: do NOT mutate the nodes!

*/

import java.util.HashMap; // import the HashMap class

class LoopInspector {

  public int loopSize(Node node) {
    HashMap<Node, Integer> m = new HashMap<Node, Integer>();
    Boolean done = false;
    Integer count = 1;
    Integer retVal = 0;
    while(done == false){
   
      if(m.get(node.getNext())!=null){
        if(count == 2){
          return 1;
        }
        retVal = count - m.get(node.getNext())+1; 
       
        done = true;
   
      } else {
        m.put(node,count++);
        node = node.getNext();
        
      }
    }
    return retVal;
  }

}

/* Solution 1 

import java.util.HashMap;
public class LoopInspector {
  public int loopSize(Node node) {
    Node current = node;
    HashMap<Node, Integer> map = new HashMap<>();
    int i = 0;
    for(; !map.containsKey(current); i++) {
      map.put(current, i);
      current = current.getNext();
    }
    return i - map.get(current);
  }
}
*/

/* Solution 2
public class LoopInspector {

  public int loopSize(Node node) {
    Node a = node;
    Node b = node.getNext();
 
    while (a != b)
    {
      a = a.getNext();
      b = b.getNext().getNext();
    }
  
    int len = 0;
    
    do {
      b = b.getNext();
      len++;
    } while (a != b);
 
   return len;
  }
}
*/

/* Solution 3
import java.util.*;

public class LoopInspector {

  public int loopSize(Node node) {
    ArrayList<Node> arrayNode = new ArrayList<Node>();
    while(node != null && !arrayNode.contains(node)) {
        arrayNode.add(node);
        node = node.getNext();
    }
    return arrayNode.size() - arrayNode.indexOf(node);
  }
}

*/