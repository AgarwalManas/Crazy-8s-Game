import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class SpecialPlayer extends Player{

	public SpecialPlayer(Card[] cards){
		this.hand = new ArrayList<Card>(Arrays.asList(cards));
		}
	
	
	public int points_1(Card xyz){
	
	int r = xyz.getRank();
	
	
	if(r == 2 || r == 4){
		return 25;
	}
	
	else if(r == 7){
		return 20;
	}
	else if(r == 8){
		return 50;
	}
	else if( r == 11 || r == 12 || r == 13){
		return 10;
	}
	
	return -1;
	
	}
	
	
	
	public int points_2(Card xyz){
	
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
	
	public int location_2(DiscardPile discardPile){
		
		int i = 0;	
		int temp, maximum = -1, value = -1;
		
		if(this.hand.size() !=0){
			for(i = 0; i<this.hand.size(); i++){
				
				if(this.hand.get(i).getRank() == 8){
					return i;
				}
				
				
				

				
				if ((discardPile.top()).getSuit().equals(this.hand.get(i).getSuit()) || (discardPile.top()).getRank() == this.hand.get(i).getRank()){
					
					temp = points_2(this.hand.get(i));
					if(temp > value){
						maximum = i;
						value = temp;
						//System.out.println("value is :" + value);
						//System.out.println();
						//System.out.println();
					}
				
				}
			}
		}
		
		return maximum;
	
	}
	
	public int location_1(DiscardPile discardPile){
		
		int i = 0;	
		int temp, maximum = -1, value = -1;
		
		if(this.hand.size() !=0){
			for(i = 0; i<this.hand.size(); i++){
				
				if(this.hand.get(i).getRank() == 8){
					return i;
				}
				
				
				

				
				if ((discardPile.top()).getSuit().equals(this.hand.get(i).getSuit()) || (discardPile.top()).getRank() == this.hand.get(i).getRank()){
					
					temp = points_1(this.hand.get(i));
					if(temp > value){
						maximum = i;
						value = temp;
						//System.out.println("value is :" + value);
						//System.out.println();
						//System.out.println();
					}
				
				}
			}
		}
		
		return maximum;
	
	}
  
  
  
  /* play a card */ 
	public boolean play(DiscardPile       discardPile, 
	                    Stack<Card>       drawPile, 
											ArrayList<Player> players)
	{	
	
	
	System.out.println("Before playing: "  + this.hand.toString());
		boolean temp = false;
		String suitEight = "xyz";
	
		if ((discardPile.top()).getRank() == 4 && super.checker_4 == true){
			System.out.println();
			System.out.println("\t   _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
			System.out.println("\t  |  skipping the turn.. :(     |");
			System.out.println("\t   -----------------------------");
			System.out.println();
			System.out.println();
			super.checker_4 = false;
			return false;
			
			}
	
	
	
	
	
	
		/* adding two cards */
		 if ((discardPile.top()).getRank() == 2 && super.checker_2 == true){
			 
			 System.out.println();
			 System.out.println("\t   _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
			 System.out.println("\t  |  twice the value added!     |");
			 System.out.println("\t   -----------------------------");
			 System.out.println();
			 System.out.println();
			 
			 
			this.hand.add(drawPile.pop());
			 this.hand.add(drawPile.pop());
			temp = false;
			super.checker_2 = false;
		 }
		
		
		 
		
		else{
			int i = location_1(discardPile);
			
			
			if(i == -1){
				this.hand.add(drawPile.pop());
				if( i == location_2(discardPile)){ 
					//System.out.println("value is added!");
					//System.out.println();
					//System.out.println();
				
				} else{
					i = location_2(discardPile);
					//System.out.println("HAHA! got it!");
					discardPile.add(this.hand.remove(i));
					temp = false;
					
					
					if ((discardPile.top()).getRank() == 2 && super.checker_2 == false){
						super.checker_2 = true;
					}
					
					if(discardPile.top().getRank() == 4 && super.checker_4 == false){
						super.checker_4 = true;
					}
					
					if ((discardPile.top()).getRank() == 8 && temp == false){
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
					}
					
					
					
					if( this.hand.size() == 0 ){
					temp = true;
					}
				
				
				
				}
				
				
			} else{
				discardPile.add(this.hand.remove(i));
				temp = false;
				
				if ((discardPile.top()).getRank() == 2 && super.checker_2 == false){
						super.checker_2 = true;
					}
				
				if(discardPile.top().getRank() == 4 && super.checker_4 == false){
					super.checker_4 = true;
					}
				
				if ((discardPile.top()).getRank() == 8 && temp == false){
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
					
					}
				
				
				
				if( this.hand.size() == 0 ){
					temp = true;
					}
				
			}
		}
	

		
		
	
		System.out.println("After Playing:  " + this.hand.toString());
		System.out.println();
		return temp;
		
	
	}
	
	
	
	
	
	
	
	
	
	public String wildEight(DiscardPile       discardPile, 
	                    Stack<Card>       drawPile, 
											ArrayList<Player> players)
	{
		
		int i = 0;	
		int temp, maximum = 0, value = -1;
	
		if(this.hand.size() > 0){
			for(i = 0; i<this.hand.size(); i++){
					
					temp = points_2(this.hand.get(i));
					
					if(temp > value){
						maximum = i;
						value = temp;
					}
				}
		
		
		}
		
		
		//System.out.println(this.hand.get(maximum).getSuit());
		return this.hand.get(maximum).getSuit();
		
	}
	
	
	
	
	
	
}