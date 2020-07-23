/* 
A famous casino is suddenly faced with a sharp decline of their revenues. They decide to offer Texas hold'em also online. Can you help them by writing an algorithm that can rank poker hands?

Task
Create a poker hand that has a method to compare itself to another poker hand:

Result PokerHand.compareWith(PokerHand hand);
A poker hand has a constructor that accepts a string containing 5 cards:

PokerHand hand = new PokerHand("KS 2H 5C JD TD");
The characteristics of the string of cards are:

Each card consists of two characters, where
The first character is the value of the card: 2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce)
The second character represents the suit: S(pades), H(earts), D(iamonds), C(lubs)
A space is used as card separator between cards
The result of your poker hand compare can be one of these 3 options:

public enum Result
{
    WIN,
    LOSS,
    TIE
}
Notes
Apply the Texas Hold'em rules for ranking the cards.
Low aces are NOT valid in this kata.
There is no ranking for the suits.


*/
import java.util.*;

    
class PokerHand
{      
    public enum Result { TIE, WIN, LOSS }
    
    private static char[] valArr = new char[] {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
    private static Map<Character,Integer> valueMap = new HashMap<Character,Integer>();
    static { for (int n = 0 ; n < valArr.length ; n++) valueMap.put(valArr[n], n); }
    
    private double rank;
    
    public PokerHand(String hand) {
    
        Map<Integer,Integer>   valCount   = new HashMap<Integer,Integer>();
        Map<Character,Integer> colorCount = new HashMap<Character,Integer>();
        Set<Integer>           suiteSet   = new HashSet<Integer>();
    
        for (String card: hand.split(" ")) {
            int valKard    = valueMap.get(card.charAt(0));
            char colorKard = card.charAt(1);
            
            valCount.put(   valKard,   1 + valCount.getOrDefault(valKard, 0));
            colorCount.put( colorKard, 1 + colorCount.getOrDefault(colorKard, 0));
        }
         
        List<Integer> Keys_SortedByValues = new ArrayList<Integer>(valCount.keySet());
        int p = 0;
        rank = 0;
        Keys_SortedByValues.sort( (k1, k2) -> valCount.get(k1) - valCount.get(k2) == 0 ? k1 - k2 : valCount.get(k1) - valCount.get(k2) );
        for (int k: Keys_SortedByValues) rank += k * Math.pow(14, p++);
        
        int minVal = Collections.min(valCount.keySet());
        for (int n = 0 ; n < 5 ; n++) suiteSet.add(minVal + n);
        boolean isSuite = valCount.keySet().equals(suiteSet);
        
        if (colorCount.containsValue(5) && isSuite )                         rank += 8 * Math.pow(14, 5);  // straight flush
        else if (valCount.containsValue(4))                                  rank += 7 * Math.pow(14, 5);  // carre
        else if (valCount.containsValue(3) && valCount.containsValue(2))     rank += 6 * Math.pow(14, 5);  // full
        else if (colorCount.containsValue(5))                                rank += 5 * Math.pow(14, 5);  // couleur
        else if (isSuite)                                                    rank += 4 * Math.pow(14, 5);  // suite
        else if (valCount.containsValue(3))                                  rank += 3 * Math.pow(14, 5);  // brelan
        else if (valCount.containsValue(2) && valCount.keySet().size() == 3) rank += 2 * Math.pow(14, 5);  // 2 paires
        else if (valCount.containsValue(2) && valCount.keySet().size() == 4) rank += 1 * Math.pow(14, 5);  // 1 paire
        else                                                                 rank += 0;                     // 1 carte
    }
    
    public double getHandValue() { return rank; }

    public Result compareWith(PokerHand hand) {
        double other = hand.getHandValue();
        return rank < other ? Result.LOSS : rank == other ? Result.TIE : Result.WIN;
    }
}

/* Solution 1 

import java.util.*;

public class PokerHand {
  public enum Result {TIE,WIN,LOSS}
  
  private int[] cardValues = new int[5]; // In ascending order
  private int value = 0;
  
  public PokerHand(String hand) {
    String[] cardStrs = hand.split(" ");
    
    int[] ofAKinds = new int[13];
    
    boolean isFlush = true;
    char prevSuit = 0;
    
    for(int i = 0; i < cardStrs.length; ++i) {
      String cardStr = cardStrs[i];
      char suit = cardStr.charAt(1);
      char valueChr = cardStr.charAt(0);
      int value = 0;
      
      switch(valueChr) {
        case 'T': value = 10; break;
        case 'J': value = 11; break;
        case 'Q': value = 12; break;
        case 'K': value = 13; break;
        case 'A': value = 14; break;
        default: value = Character.getNumericValue(valueChr);
      }
      
      ++ofAKinds[value - 2];
      
      if(prevSuit != 0 && suit != prevSuit) { isFlush = false; }
      prevSuit = suit;
      
      cardValues[i] = value;
    }
    
    boolean is2OfAKind = false,is2Pairs = false;
    boolean is3OfAKind = false,is4OfAKind = false;
    
    for(int oak: ofAKinds) {
      switch(oak) {
        case 2:
          if(is2OfAKind) { is2Pairs = true; }
          else { is2OfAKind = true; }
          break;
        case 3: is3OfAKind = true; break;
        case 4: is4OfAKind = true; break;
      }
    }
    
    Arrays.sort(cardValues);
    
    boolean isStraight = true;
    int straightValue = (cardValues[0] == 14) ? 1 : cardValues[0]; // Ace can be 1
    
    for(int value: cardValues) {
      if(value != straightValue++) { isStraight = false; break; }
    }
    
    if(isStraight && isFlush) {
      // Royal flush?
      value = (cardValues[0] == 10) ? 1337 : 800;
    }
    else if(is4OfAKind) { value = 700; }
    // Full house
    else if(is3OfAKind && is2OfAKind) { value = 600; }
    else if(isFlush) { value = 500; }
    else if(isStraight) { value = 400; }
    else if(is3OfAKind) { value = 300; }
    else if(is2Pairs) { value = 200; }
    else if(is2OfAKind) { value = 100; }
    // High card
    else { value = cardValues[4]; }
  }
  
  public Result compareWith(PokerHand hand) {
    int diff = value - hand.value;
    
    if(diff == 0) {
      for(int i = cardValues.length - 1; i >= 0; --i) {
        if((diff = cardValues[i] - hand.cardValues[i]) != 0) { break; }
      }
    }
    return (diff < 0) ? Result.LOSS : ((diff > 0) ? Result.WIN : Result.TIE);
  }
}
*/

/* Solution 2 
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.IntStream;

public class PokerHand{

  public enum Result { TIE, WIN, LOSS } 

  public enum HandRank {
      HIGH_CARD, ONE_PAIR, TWO_PAIR,
      TRHEE_OF_A_KIND, STRAIGHT, FLUSH, 
      FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH
  }
  
  private static Map<Character,Integer> valueMap = new HashMap<>();;
  static{
    char[] cardValues = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
    int[] realValues = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    for(int i=0;i<cardValues.length;i++)
      valueMap.put(cardValues[i],realValues[i]);
  }
  
  private static class Card{
    Integer value;
    char suit;
    public Card(char value,char suit){
      this.value = valueMap.get(value);
      this.suit = suit;
    }
  }
  
  private List<Card> cards;
  private HandRank rank; 
  
  PokerHand(String hand){
    cards = new ArrayList<>();
    for(String card:hand.split(" "))
      cards.add(new Card(card.charAt(0),card.charAt(1)));
    cards.sort((c1,c2)->c1.value.compareTo(c2.value));
    rank = getRank();
  }
  
  public Result compareWith(PokerHand hand){        
    int result = 0;
    if(rank == hand.rank){
      for(int i=0;i<cards.size();i++){
        Integer val1 = cards.get(i).value;
        Integer val2 = hand.cards.get(i).value;
        if(val1!=val2) result = val1.compareTo(val2);
      }
    }
    else
      result = rank.compareTo(hand.rank);
    return result>0 ? Result.WIN : result<0 ? Result.LOSS : Result.TIE;
  }
  
  private HandRank getRank(){
    List<Integer> groups = getGroups();
    int value = groups.size()>0?groups.get(0):0;
    
    switch(groups.size()){
    case 0:
      boolean isStraight = IntStream.range(0, cards.size()-1).allMatch(i->cards.get(i+1).value-cards.get(i).value == 1);
      boolean isFlush = IntStream.range(0, cards.size()-1).allMatch(i->cards.get(i+1).suit==cards.get(i).suit);
      if(isStraight && isFlush)
        return HandRank.STRAIGHT_FLUSH;
      if(isStraight)
        return HandRank.STRAIGHT;
      if(isFlush)
        return HandRank.FLUSH;
      return HandRank.HIGH_CARD;
    case 1:
      return value==2 ? HandRank.ONE_PAIR : value==3 ? HandRank.TRHEE_OF_A_KIND : HandRank.FOUR_OF_A_KIND;
    case 2:
      return value == 3 ? HandRank.FULL_HOUSE : HandRank.TWO_PAIR;
    }
    throw new IllegalArgumentException("ERROR");
  }
  
  private List<Integer> getGroups(){
    List<Integer> groups = new ArrayList<>();
    for(int i=1,count=0;i<cards.size();i++){
      int val = cards.get(i-1).value;
      int nextVal = cards.get(i).value;
      if(val == nextVal)
        count++;
      if(count > 0 && (nextVal != val || i==cards.size()-1)){
        groups.add(++count);
        count = 0;  
      }
    }
    groups.sort((a,b)->b.compareTo(a));
    return groups;
  }
}
*/

/* Solution 3 

import java.util.*;
    
public class PokerHand
{      
    public enum Result { TIE, WIN, LOSS } 
  
    private final double INTERVAL = 100000;
  
    private String[] hand;
    private List<String> pairs = new ArrayList<>();
    private String triple, quad;
    private boolean flush, straight, straightFlush, fullHouse = false;
    
    private double sum, weightedValue;
  
    PokerHand(String hand)
    {
      this.hand = hand.split(" ");
      Arrays.sort(this.hand, Comparator.comparingInt((String c) -> valueOfRank(c)));
      this.triple = "";
      this.quad = "";
      
      countMultiple();
      
      this.flush = isFlush();
      this.straight = isStraight();
      this.straightFlush = this.flush && this.straight;
      this.fullHouse = !this.pairs.isEmpty() && !this.triple.isEmpty();
      
      this.sum = sumOfCards(this.hand);
      this.weightedValue = weightedValue(this.hand, new String[] {});
    }
    
  
    public Result compareWith(PokerHand hand) { 
        double v1 = this.valueOfHand();
        double v2 = hand.valueOfHand();
        
        if (v1 > v2) {
          return Result.WIN;
        }
        
        if (v1 == v2) {
          return Result.TIE;
        }
      
        return Result.LOSS;
    }
  
    private double valueOfHand() {
      if (this.straightFlush) {
        return 8 * INTERVAL + valueOfRank(this.hand[4]);
      }
      if (!this.quad.isEmpty()) {
        return 7 * INTERVAL + 20 * valueOfRank(this.quad) + this.sum;
      }
      if (this.fullHouse) {
        return 6 * INTERVAL + 20 * valueOfRank(this.triple) + valueOfRank(this.pairs.get(0));
      }
      if (this.flush) {
        return 5 * INTERVAL + this.weightedValue;
      }
      if (this.straight) {
        return 4 * INTERVAL + valueOfRank(this.hand[4]);
      }
      if (!this.triple.isEmpty()) {
        return 3 * INTERVAL + valueOfRank(this.triple) + weightedValue(this.hand, new String[] {this.triple});
      }
      if (this.pairs.size() == 2) {
        return 2 * INTERVAL + Math.pow(2, valueOfRank(this.pairs.get(0))) + Math.pow(2, valueOfRank(this.pairs.get(1))) 
          + weightedValue(this.hand, new String[] {this.pairs.get(0), this.pairs.get(1)});
      }
      if (this.pairs.size() == 1) {
        return INTERVAL + valueOfRank(this.pairs.get(0)) + weightedValue(this.hand, new String[] {this.pairs.get(0)});
      }
      
      return this.weightedValue;
    }
  
    private int valueOfRank(String card) {
      char c = card.charAt(0);
      switch(c) {
        case 'T': return 10;
        case 'J': return 11;
        case 'Q': return 12;
        case 'K': return 13; 
        case 'A': return 14;
        default: return Character.getNumericValue(c);
      }
    }
  
    private double sumOfCards(String[] hand) {
      double sum = 0;
      for (String card : hand) {
        sum += valueOfRank(card);
      }
      return sum;
    }
  
    private double weightedValue(String[] cards, String[] except) {
      double sum = 0;
      for (String card : cards) {
        int val = valueOfRank(card);
        double weighted = Math.pow(0.5, 14 - valueOfRank(card));
        for (String e : except) {
          if (val == valueOfRank(e)) {
            weighted = 0;
          }
        }
        sum += weighted;
      }
      return sum;
    }
  
    private boolean isFlush() {
      for (int i = 1; i < this.hand.length; i++) {
        if (this.hand[i-1].charAt(1) != this.hand[i].charAt(1)) {
          return false;
        }
      }
      return true;
    }
  
    private boolean isStraight() {
      for (int i = 1; i < this.hand.length; i++) {
        if (valueOfRank(this.hand[i]) - valueOfRank(this.hand[i-1]) != 1) {
          return false;
        }
      }
      return true;
    }
  
    private void countMultiple() {
      int count = 1;
      for (int i = 1; i < this.hand.length; i++) {
        int v1 = valueOfRank(this.hand[i-1]);
        int v2 = valueOfRank(this.hand[i]);
        if (v2 != v1) {
          count = 1;
          continue;
        }
        count++;
        if (count == 2) {
          this.pairs.add(this.hand[i].substring(0,1));
        }
        if (count == 3) {
          this.pairs.remove(this.pairs.size() - 1);
          this.triple = this.hand[i];
        }
        if (count == 4) {
          this.triple = "";
          this.quad = this.hand[i];
        }
      }
    }
  
}
*/
