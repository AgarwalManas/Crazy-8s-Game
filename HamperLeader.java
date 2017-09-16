import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;


public class HamperLeader extends Player{
	
	public HamperLeader(Card [] cards){
		this.hand = new ArrayList<Card>(Arrays.asList(cards));		
	}
	
	//Algorithim for HamperLeader
	/*
	1. Compare # of cards held by all players
	2. Determine least amount of cards
	3. Determine if least amount of cards is held by next Player
	4. If yes then play power cards
	5. Else, play normally. 
	*/
	
	// 1,2,3
	public Player leastNumberOfCards(ArrayList<Player> players){
		Player leastCard = players.get(0);
		
		//Determine minimum hand size;
		for(int i = 1; i < players.size(); i+=1){
			if(players.get(i).getSizeOfHand() < leastCard.getSizeOfHand() || players.get(i) != this){
				leastCard = players.get(i); 
			}
		}
		
		//Determine if leastCard is next to this player. //Not sure if to put this but (if(indexOf(leastCard) - indexOf(this) == -1)) because of reverse direction
		if(players.indexOf(leastCard) - players.indexOf(this) == 1){
			return leastCard;
		}
		return null;
		
	}

	// The Play Method
	public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
		
		Card topOfDiscard = discardPile.top();
		boolean temp = false;
		String suitEight = "";
		
		if(topOfDiscard.getRank() == 2 && super.hchecker_2 == true){
			this.hand.add(drawPile.pop());
			this.hand.add(drawPile.pop());
			temp = true;
			super.hchecker_2 = !(super.hchecker_2);
			System.out.println(this.hand.toString());
			return false;
		}
		
		if ((discardPile.top()).getRank() == 4 && super.hchecker_4 == true){
            System.out.println("skipping the turn.. :(");
            super.hchecker_4 = false;
            System.out.println(this.hand.toString());
            return false;
            
        }
        /*
		if ((discardPile.top()).getRank() == 4 && super.checker_4 == true){
			System.out.println("Skip Turn..");
			super.checker_4 = !(super.checker_4);
			temp = true;
			//return false;
			
			}
			*/
		
		// If next player hand size is equal to leastNumberOfCards then play power card
		if(temp == false){
			if(leastNumberOfCards(players) != null){
				int handRank = 0; 
				String handSuit = " ";
				for(int j = 0; j < this.hand.size(); j+=1){
					handRank = this.hand.get(j).getRank();
					handSuit = this.hand.get(j).getSuit();
					if((handRank == 2 || handRank == 4 || handRank == 7) && handSuit.equals(topOfDiscard.getSuit())){
						discardPile.add(this.hand.get(j));
						this.hand.remove(j);
						break;
					}
					else if(handRank == 8){
						discardPile.add(this.hand.get(j));
						this.hand.remove(j);
						break;
					}
				}
			}
			else{
				for(int i = 0; i < this.hand.size(); i+=1){
					if((topOfDiscard.getSuit().equals(this.hand.get(i).getSuit())) || topOfDiscard.getRank() == this.hand.get(i).getRank()){
						discardPile.add(this.hand.get(i));
						this.hand.remove(i);
						temp = true;
						break;
					}
				}
				if( temp == false){
					//pick up a card and check to see if that equals topofdiscard card and play 
					Card newCard = drawPile.pop();
					if(topOfDiscard.getSuit().equals(newCard.getSuit()) || topOfDiscard.getRank() == newCard.getRank()){
						discardPile.add(newCard);
						//this.hand.remove(i);
					}
				else{
					this.hand.add(newCard);
				}
			}
			}
			if ( (discardPile.top()).getRank() == 8 && temp == false && super.hchecker_8 == true){
						suitEight = this.wildEight(discardPile, drawPile, players);
						System.out.println();
						System.out.println("\t \t  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("\t \t \t Changing the suit to: " + suitEight);
						System.out.print("\t \t \t Processing....");
						
						
						
						System.out.println();
						(discardPile.top()).setSuit(suitEight);
						System.out.println("\t \t \t draw pile   : " + drawPile.peek() );
						System.out.println("\t \t \t discard pile : " + discardPile.top() );
						System.out.println("\t \t  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println();
						System.out.println();
						super.hchecker_8 = !(super.hchecker_8);
					
					}
			
			
		}
	
		
		
		if(this.hand.size() == 0){
			return true;
		}
		System.out.println(this.hand.toString());
		return false;
	}
	
	
	public String wildEight(DiscardPile       discardPile, 
	                             Stack<Card>       drawPile, 
										          	ArrayList<Player> players){
						
			return Card.SUITS[(int) Math.floor(Math.random() * 4)];
												
	}
	
	
	
	
	
}