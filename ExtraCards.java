/*ExtraCards algorithm
 * 
 *
 * (check if power cards are in hand)
 * check if next player has only 1 card left
 * if true: draw one card and do not play (keep running this procedure until a power card is drawn)
 * (in the early game: only draw 1 card) (check game stage if the other players have 3 or less cards)
 * (if the other players have 3 or less cards, keep drawing from the draw pile until a power card is drawn
 * if the draw pile runs out, do the shuffle operation on the discard pile and merge with the draw pile (card refresh)
 * 
 * 
 *KNOWN ISSUES: If all players are ExtraCards, and every player has a power card but none match the top of the discard card pile, no one is able to move. 
 * They cannot pick up cards either, since they all have at least one power card.
 * 
 * 
 *
 * */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;

public class ExtraCards extends Player{
  
  
  public String wildEight(DiscardPile       discardPile, 
                              Stack<Card>       drawPile, 
                     ArrayList<Player> players){
    
HashMap<String, Integer> suits = new HashMap<String, Integer>();
    
  String suit = "";
  String maxsuit = "";
  int count = 0;
  int maxcount = -1;
  
  if(this.hand.size() == 0){
    return "None";
  }
  for(int i = 0; i < this.hand.size(); i++){
    if(suits.containsKey(this.hand.get(i).getSuit())){
      suits.put(this.hand.get(i).getSuit(), suits.get(this.hand.get(i).getSuit())+1);
    }else{
         suits.put(this.hand.get(i).getSuit(), 1);
    }
  }
    for(int i = 0; i < this.hand.size(); i++){
     suit = this.hand.get(i).getSuit();
 //    System.out.println("suit: " + suit);
     count = suits.get(suit);
     if(count > maxcount){
       maxsuit = suit;
       maxcount = count;
     }   
    }  

     return maxsuit;         
  }
  
   public ExtraCards(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}
 
   public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
      
   Card topOfDiscard = discardPile.top();
  boolean temp = false;   
    if(topOfDiscard.getRank() == 2  && super.echecker_2 == true){
   this.hand.add(drawPile.pop());
   this.hand.add(drawPile.pop());
   temp = true;
   super.echecker_2 = false;
   System.out.println(this.hand.toString());
   return false;
   
  }
  
  if ((discardPile.top()).getRank() == 4 && super.echecker_4 == true){
   System.out.println("Skip Turn");
   super.echecker_4 = false;
   temp = true;
   //return false;
   
   }
     Card add = null;
     String suit = "";
     boolean breaker = false;
     Card addedCard;
    //check if neighbours have only one card left (late stage of the game)

     for(int i = 0; i < players.size(); i++){
     //  System.out.println("i: " + i);
       if(players.get(i).getSizeOfHand() == 1){              
         System.out.println("Neighbour only has one card... Checking if my hand has a power card");
        //check if your hand has any power cards
         for(int j = 0; j < this.hand.size(); j++){
           if(this.hand.get(j).getRank() == 2 || this.hand.get(j).getRank() == 4 || this.hand.get(j).getRank() == 7 || this.hand.get(j).getRank() == 8){
             System.out.println("Power cards already in hand... not picking up extra cards...");
             breaker = true;
             break;
           }
         }
        
         if(breaker == true){

          break; 
         }        
           while(drawPile.size() > 0){
            System.out.println("Picking up extra cards");
           addedCard = drawPile.pop();
           this.hand.add(addedCard);          
           if(addedCard.getRank() == 2 || addedCard.getRank() == 4 || addedCard.getRank() == 7 || addedCard.getRank() == 8){
             System.out.println("Picked up a power card... exiting loop");
            break; 
           }      
           
         }
       }  
    


         // draw cards until you pick up a power card         
    
       
         if(drawPile.size() == 0){
//          System.out.println("out of cards"); 
         }
     }//end for
     
     //play card
       for(int k = 0; k < this.hand.size(); k++){   
  
         if(this.hand.get(k).getRank() == discardPile.top().getRank() || this.hand.get(k).getSuit() == discardPile.top().getSuit()){
       add = this.hand.remove(k);
       if(add != null){
         
      System.out.println("Playing card");
           discardPile.add(add); 
           
           
           if(add.getRank() == 8){ 
             suit = this.wildEight(discardPile,drawPile,players);
              //System.out.println("---------------------------------Eight Played");
                 
                        if(suit.equals("None")){
                          
                         return true; 
                        }
                        //System.out.println("Changing suit to   :   " + suit);
                        discardPile.top().setSuit(suit);
                        System.out.println("---------------------------------Eight Played");
                        System.out.println("Changing suit to   :   "+suit);
                        System.out.println("---------------------------------Suit Changed");
                        //System.out.println("---------------------------------Suit Changed");
                        super.echecker_8 = !(super.echecker_8);
                        return true;
                    }
             
           }
           break;
       }
         }
//       System.out.println("this ran");

         System.out.println(this.hand.toString());
          if (this.hand.size() == 0){
      return true; 
     } 
       return false;      
       }
//     System.out.println("now this ran");
   //  System.out.println("hand size: " + this.hand.size());
   
   }
  

