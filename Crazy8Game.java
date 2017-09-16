import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Random;
import java.util.Collections;

public class Crazy8Game{
  
  public static int numPlayers = 4;

  public static Player[] reverse(Player[] players){
    System.out.println("Reversal.");
    Player[] temp = new Player[players.length];
    int n = 0;
    for(int i = players.length - 1; i>0; i--){
      temp[n] = players[i];
      n++;
    }
    return temp;
  }
  
 public static void main(String[] args){
  int count = 0;
  boolean direc = false;
  int winningScore = 100;
  int dir = 1;
  int countPrev = 0;
  String currentSuit = "";
 
   Random rand = new Random();
   Game game = new Game();
  /* create the deck */
  Card[] deck = new Card[52]; 
  deck = game.deck_init();  
  /* shuffle the deck */
  deck = game.shuffle(deck);
 
 
  
  /* players in the game */
  Player[] players = new Player[numPlayers];
  int[] scoreboard = new int[numPlayers];
  
  for(int p = 0; p < scoreboard.length; p++){
    
   scoreboard[p] = 0; 
  }
  
  players[0] = new MindTheEights( Arrays.copyOfRange(deck, 0, 5) );
  System.out.println("0 : " + Arrays.toString( Arrays.copyOfRange(deck, 0, 5))); 
  players[1] = new HamperLeader( Arrays.copyOfRange(deck, 5, 10) );
  System.out.println("1 : " + Arrays.toString( Arrays.copyOfRange(deck, 5, 10))); 
  players[2] = new ExtraCards( Arrays.copyOfRange(deck, 10, 15) );
  System.out.println("2 : " + Arrays.toString( Arrays.copyOfRange(deck, 10, 15)));
  players[3] = new DiscardHighPoints( Arrays.copyOfRange(deck, 15, 20) );
  System.out.println("3 : " + Arrays.toString( Arrays.copyOfRange(deck, 15, 20)));

   
  
  /* discard and draw piles */
  DiscardPile discardPile = new DiscardPile();
  Stack<Card> drawPile = new Stack<Card>();
  for(int i=15; i<deck.length; i++){
   drawPile.push(deck[i]);
  }
  
  System.out.println("draw pile is : " + Arrays.toString( Arrays.copyOfRange(deck, 15, deck.length) ));
  
  deck = null;  
  
  boolean win = false;
  boolean score = false;
  int player = -1;    // start game play with player 0
  
  ArrayList<Player> people = new ArrayList<Player>(Arrays.asList(players));
  discardPile.add( drawPile.pop() );
  
  /*
int count = 0;
boolean direc = false;
int winningScore = 200;
int dir = 1;
int countPrev = 0;
String currentSuit = "";
*/

while(!score){
  //Reset game
  win = false;
  
  for(Player p : people){
    while(p.getSizeOfHand() < 6){
      //pick up card
      if(drawPile.size() <=3){
           System.out.println("recycling cards");
         
         Stack <Card> top = new Stack <Card>();
       
         // saves top of discard pile
         top.add(discardPile.pop());
       //  System.out.println("top: " + top);
       
        Card [] recycled_cards = new Card[discardPile.size()];
        
        recycled_cards = game.recycle(discardPile);
        
        //clear discard pile
        discardPile.clear();
        
        //add top back to the discard pile
        discardPile.add(top.pop());
        
        //shuffling

      //copying back to the draw pile
        for(int k = 0; k < recycled_cards.length; k++){
        drawPile.add(recycled_cards[k]); 
        }

      }
      Card  newCard = drawPile.pop();
      p.hand.add(newCard);
    }
  }

  while(!win ){


      /**TESTING ONLY STARTS**/
      /*while(drawPile.isEmpty() != true){
            discardPile.add(drawPile.pop());
      }*/
      /**TESTING ONLY ENDS**/


          // -7- Played: Change of directional play
          if(count > countPrev && (!discardPile.top().getSuit().equals(currentSuit))){
            Player.checker_7 = true;
          }

          if(discardPile.top().getRank() == 7 && Player.checker_7 == true){
            currentSuit = discardPile.top().getSuit();
            dir = -dir;
            System.out.println("----------------------------------Direction changed");
            Player.checker_7 = !(Player.checker_7);
            countPrev = count;
          }
          if(dir == -1 && (player == -1 || player == 0)){
            player = players.length;
            count++;
          }

         //System.out.println("player variable: "+ player);
         player = (player + dir) % players.length;
         System.out.println("player " + player);
         System.out.println("draw pile    : " + drawPile.peek() );
         System.out.println("discard pile : " + discardPile.top() );

        win = people.get(player).play(discardPile, drawPile, people);





          if(drawPile.size() <= 3){
           System.out.println("recycling cards");
         
         Stack <Card> top = new Stack <Card>();
       
         // saves top of discard pile
         top.add(discardPile.pop());
       //  System.out.println("top: " + top);
       
        Card [] recycled_cards = new Card[discardPile.size()];
        
        recycled_cards = game.recycle(discardPile);
        
        //clear discard pile
        discardPile.clear();
        
        //add top back to the discard pile
        discardPile.add(top.pop());
        
        //shuffling

      //copying back to the draw pile
        for(int k = 0; k < recycled_cards.length; k++){
        drawPile.add(recycled_cards[k]); 
        }

         }
         //System.out.println("draw pile   : " + drawPile.peek() );
         System.out.println("discard pile : " + discardPile.top() );
            /*shuffle start*/
        System.out.println("-----------------------");

        System.out.println("draw pile size: " + drawPile.size());

        System.out.println("-----------------------");
      }//while loop 1 end
    System.out.println("---------------------------------NEWGAME?");
    System.out.println("winner is player " + player);
    scoreboard = game.score(scoreboard, player, people);
  
    for(int i = 0; i < scoreboard.length; i++){
    
      System.out.println("Score for: " + i + " " + scoreboard[i]); 
    }

    for(int i = 0; i<scoreboard.length; i++){
      if(scoreboard[i] >= winningScore){
        score = true;
        System.out.println("----------------------THE OVERALL WINNER IS: Player "+ player);
      }
    }
   
  }//while loop 2 end
  
  
 }
}
 
