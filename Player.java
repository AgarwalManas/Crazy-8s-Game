import java.util.ArrayList;
import java.util.Stack;

public abstract class Player{
	protected ArrayList<Card> hand;
	public static boolean mchecker_2 = true;
	public static boolean mchecker_4 = true;
	public static boolean mchecker_7 = true;
	public static boolean mchecker_8 = true;

	public static boolean echecker_2 = true;
	public static boolean echecker_4 = true;
	public static boolean echecker_7 = true;
	public static boolean echecker_8 = true;

	public static boolean hchecker_2 = true;
	public static boolean hchecker_4 = true;
	public static boolean hchecker_7 = true;
	public static boolean hchecker_8 = true;

	public static boolean checker_2 = true;
	public static boolean checker_4 = true;
	public static boolean checker_7 = true;
	public static boolean checker_8 = true;
	
	public int getSizeOfHand(){
		return hand.size();
	}
	
	/* play a card  */
	public abstract boolean play(DiscardPile       discardPile, 
	                             Stack<Card>       drawPile, 
										          	ArrayList<Player> players);
	// return true if player wins game by playing last card
	// returns false otherwise
	// side effects: plays a card to top of discard Pile, possibly taking zero
	//               or more cards from the top of the drawPile
	//               card played must be valid card
	
	public abstract String wildEight(DiscardPile       discardPile, 
	                             Stack<Card>       drawPile, 
										          	ArrayList<Player> players);
}