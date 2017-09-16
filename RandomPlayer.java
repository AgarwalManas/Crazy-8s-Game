import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class RandomPlayer extends Player{

	public RandomPlayer(Card [] cards){
		this.hand = new ArrayList<Card>(Arrays.asList(cards));
	}
	
	
	//The Play Method
	public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
		Card topOfDiscard = discardPile.top();
		boolean temp = false; 
		//int i = 0;
		//boolean cardPlayed = false;
		//if(topOfDiscard.getRank() == 4){
		//		cardPlayed = true;
		//	}
		
		if(topOfDiscard.getRank() == 2  && super.checker_2 == true){
			this.hand.add(drawPile.pop());
			this.hand.add(drawPile.pop());
			temp = true;
			super.checker_2 = false;
			
		}
		
		if ((discardPile.top()).getRank() == 4 && super.checker_4 == true){
			System.out.println("Skip Turn");
			super.checker_4 = false;
			temp = true;
			//return false;
			
			}
		
			 
		if(temp == false){
		for(int i = 0; i < this.hand.size(); i+=1){
			if((topOfDiscard.getSuit().equals(this.hand.get(i).getSuit())) || topOfDiscard.getRank() == this.hand.get(i).getRank()){
				discardPile.add(this.hand.get(i));
				this.hand.remove(i);
				temp = true;
				break;
			}
			
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
			
				//cardPlayed = true; 
			
			
			
		System.out.println(this.hand.toString());
		if(this.hand.size() == 0){
			return true;
		}
		return false; 
	}
		public String wildEight(DiscardPile       discardPile, 
	                             Stack<Card>       drawPile, 
										          	ArrayList<Player> players){
						
			return Card.SUITS[(int) Math.floor(Math.random() * 4)];
												
	}
}

