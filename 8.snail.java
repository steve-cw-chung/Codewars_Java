 /*
 Snail Sort
Given an n x n array, return the array elements arranged from outermost elements to the middle element, traveling clockwise.

array = [[1,2,3],
         [4,5,6],
         [7,8,9]]
snail(array) #=> [1,2,3,6,9,8,7,4,5]
For better understanding, please follow the numbers of the next array consecutively:

array = [[1,2,3],
         [8,9,4],
         [7,6,5]]
snail(array) #=> [1,2,3,4,5,6,7,8,9]
*/
 
 class Snail {

    public static int[] snail(int[][] array) {
      if (array[0].length == 0) return new int[0];
      int n = array.length;
      int[] answer = new int[n*n];
      int index=0;
      for (int i = 0; i<n/2; i++){
        for (int j = i; j < n-i; j++) answer[index++] = array[i][j];
        for (int j = i+1; j < n-i; j++) answer[index++] = array[j][n-i-1];
        for (int j = i+1; j < n-i; j++) answer[index++] = array[n-i-1][n-j-1];
        for (int j = i+1; j < n-i-1; j++) answer[index++] = array[n-j-1][i];
      }
      if (n%2 != 0) answer[index++] = array[n/2][n/2];
      return answer;
    } 
}

/* Solution 1 

public class Snail {
  public static int[] snail(int[][] array) {
    int n = array[0].length;
    final int TOTAL_MOVES = n * n;
    int[] result = new int[TOTAL_MOVES];
    boolean[][] traversed = new boolean[n][n]; //Defaults to all false
    State state = new State();
    
    for(int moves = 0; moves < TOTAL_MOVES; moves++) {
      result[moves] = array[state.y][state.x];
      traversed[state.y][state.x] = true;
      
      possiblyChangeDirection(state, traversed);
      state.move();
    }
    
    return result;
  }
  
  private static void possiblyChangeDirection(State state, boolean[][] traversed) {
    int x = state.x;
    int y = state.y;
    switch(state.currentDirection) {
      case POSITIVE_X:
        if(x == traversed[x].length - 1 || traversed[y][x+1]) {
          state.rotate();
        }
        break;
      case POSITIVE_Y:
        if(y == traversed.length - 1 || traversed[y+1][x]) {
          state.rotate();
        }
        break;
      case NEGATIVE_X:
        if(x == 0 || traversed[y][x-1]) {
          state.rotate();
        }
        break;
      case NEGATIVE_Y:
        if(y == 0 || traversed[y-1][x]) {
          state.rotate();
        }
        break;
      default:
        throw new IllegalStateException("Direction not implemented");
    }
  }
  
  //Private, so no need for encapsulation
  private static class State {
    private enum Direction {
      POSITIVE_X, NEGATIVE_X, POSITIVE_Y, NEGATIVE_Y
    }
    
    //Default position and direction
    int x = 0;
    int y = 0;
    Direction currentDirection = Direction.POSITIVE_X;
    
    void rotate() {
      switch(currentDirection) {
        case POSITIVE_X:
          currentDirection = Direction.POSITIVE_Y;
          break;
        case POSITIVE_Y:
          currentDirection = Direction.NEGATIVE_X;
          break;
        case NEGATIVE_X:
          currentDirection = Direction.NEGATIVE_Y;
          break;
        case NEGATIVE_Y:
          currentDirection = Direction.POSITIVE_X;
          break;
      }
    }
    
    void move() {
      switch(currentDirection) {
        case POSITIVE_X:
          x++;
          break;
        case POSITIVE_Y:
          y++;
          break;
        case NEGATIVE_X:
          x--;
          break;
        case NEGATIVE_Y:
          y--;
          break;
        default:
          throw new IllegalStateException("Direction not implemented");
      }
    }
  }
}*/

/* Solution 2 

public class Snail {

    public static int[] snail(int[][] array) {
        if (array[0].length == 0) return new int[]{};
        int n = array.length;
        int[] res = new int[n*n];
        int k = 0;
        for (int l = 0; l < n/2; l++) {
            for (int i = l; i < n - l; i++) {
                res[k] = array[l][i];
                k++;
            }
            for (int i = l + 1; i < n-l-1; i++) {
                res[k] = array[i][n-l-1];
                k++;
            }
            for (int i = n-l-1; i > l-1; i--) {
                res[k] = array[n-l-1][i];
                k++;
            }
            for (int i = n-l-2; i > l; i--) {
                res[k] = array[i][l];
                k++;
            }
        }
        if (n%2==1) res[res.length-1] = array[n/2][n/2];
        return res;
   } 
}*/

/* Solution 3 

public class Snail {

    public static int[] snail(int[][] array) {   
    int[] nul ={};
    if (array[0].length==0) {return nul;}
    else
    {
    int n=array.length*array.length;
    int [] rez = new int[n];
    int br=1; 
    int napr=0;
    int i=-1;
    int j=0;
    for (int k=0;k<n;k++)
    {
        switch (napr) 
        {
           case  0:
               if (i<array.length-br) i++;
               else {napr=1; j++;}
               break;
           case  1:
               if (j<array.length-br) j++;
               else {napr=2; i--;}               
               break;
           case  2:
               if (i>br-1) i--;
               else {napr=3; j--;}               
               break;
           case  3:
               if (j>br) j--;
               else {napr=0; br++; i++;}                 
               break;
        }
        rez[k]=array[j][i];  
     }
     return rez;
    }
   }
}


*/