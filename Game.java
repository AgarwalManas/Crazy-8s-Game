import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Random;


/*
 * Implements the procedures from Crazy8Game in a separate class (creating a deck, shuffling and keeping score)
 *  
 * 
 * */

public class Game{
  
    public Card[] shuffle(Card[] deck){
    
  Random rnd = new Random();
  Card swap;
  for(int i = deck.length-1; i>=0; i=i-1){
   int pos = rnd.nextInt(i+1);
   swap = deck[pos];
   deck[pos] = deck[i];
   deck[i] = swap;
  }  
    
  return deck;
  }
  
  public   Card[] deck_init(){
  Card[] deck = new Card[52];  
  int index = 0;
  for(int r=2; r<=14; r+=1){
   for(int s=0; s<4; s+=1){
    deck[index++] = new Card(Card.SUITS[s], Card.RANKS[r]);
   }
  }
  return deck;
  }
  
  /*Score keeping*/
  public static int points(Card xyz){
  
    int r = xyz.getRank();
    
    if(r == 14){
      return 1;
    }
    else if(r == 2 || r == 4){
      return 25;
    }
    else if(r == 3){
      return 3;
    }
    else if(r == 5){
      return 5;
    }
    else if(r == 6){
      return 6;
    }
    else if(r == 7){
      return 20;
    }
    else if(r == 8){
      return 50;
    }
    else if(r == 10 || r == 11 || r == 12 || r == 13){
      return 10;
    }
    
    return -1;
  
  }
  
  public int [] score(int [] playerNum, int winning_player,ArrayList<Player> players){    
    for(Player p : players){
      for(Card aCard: p.hand){
        playerNum[winning_player] = playerNum[winning_player] + points(aCard);
      }
    }
    return playerNum;
  }
  
  /*Recycling cards*/ 
  public Card [] recycle(DiscardPile discardPile){
    
    
  Random rand = new Random();
    
    Card [] recycled = new Card[discardPile.size()];
    int i = 0;
  
    while(discardPile.isEmpty() != true){
    recycled[i] = discardPile.pop();
    i++;  
  }
    
    recycled = shuffle(recycled);
     
    
  return recycled;  
  }
}
