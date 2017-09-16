import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Random;
import java.util.Collections;

public class MindThe8Test{
  public static int numPlayers = 3;

  public static void main(String[] args){
    int ocunt = 0;
    boolean win = false;
    int player = -1;
    int dir = 1;
    int count = 0;
    int countPrev = 0;
    String currentSuit = "";

    Random rand = new Random();
    Game game = new Game();
    /* create the deck */
    Card[] deck = new Card[52]; 
    deck = game.deck_init();  
    /* shuffle the deck */
    deck = game.shuffle(deck);

    Player[] players = new Player[numPlayers];
    int[] scoreboard = new int[numPlayers];
  
   
    //Regular Game:
    // players[0] = new MindTheEights( Arrays.copyOfRange(deck, 0, 5) );
    // System.out.println("0 : " + Arrays.toString( Arrays.copyOfRange(deck, 0, 5))); 
    // players[1] = new MindTheEights( Arrays.copyOfRange(deck, 5, 10) );
    // System.out.println("1 : " + Arrays.toString( Arrays.copyOfRange(deck, 5, 10))); 
    // players[2] = new MindTheEights( Arrays.copyOfRange(deck, 10, 15) );
    // System.out.println("2 : " + Arrays.toString( Arrays.copyOfRange(deck, 10, 15))); 

    System.out.println("\n~~~~~~~~~~MIND THE EIGHTS TEST~~~~~~~~~~\n");

    //Custom Game:
    players[0] = new MindTheEights(Arrays.copyOfRange(deck,0,35) );
    System.out.println("0 : " + Arrays.toString( Arrays.copyOfRange(deck, 0,35))); 
    players[1] = new MindTheEights(Arrays.copyOfRange(deck,35,38));
    System.out.println("1 : " + Arrays.toString( Arrays.copyOfRange(deck, 35, 38)));
    players[2] = new MindTheEights(Arrays.copyOfRange(deck,38,41));
    System.out.println("2 : " + Arrays.toString( Arrays.copyOfRange(deck, 38, 41))); 

    DiscardPile discardPile = new DiscardPile();
    Stack<Card> drawPile = new Stack<Card>();
    for(int i=15; i<deck.length; i++){
      drawPile.push(deck[i]);
    }

    //System.out.println("draw pile is : " + Arrays.toString( Arrays.copyOfRange(deck, 15, deck.length) ));
    
    deck = null;

    ArrayList<Player> people = new ArrayList<Player>(Arrays.asList(players));
    discardPile.add( drawPile.pop() );

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
      if(drawPile.size() <= 2){
        System.out.println("recycling cards");
         
        Stack <Card> top = new Stack <Card>();
       
        // saves top of discard pile
        top.add(discardPile.pop());
        // System.out.println("top: " + top);
       
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
      System.out.println("draw pile   : " + drawPile.peek() );
      System.out.println("discard pile : " + discardPile.top() );
      /*shuffle start*/
      System.out.println("-----------------------");

      System.out.println("draw pile size: " + drawPile.size());

      System.out.println("-----------------------");
    }//while loop 1 end
  }
}