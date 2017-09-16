import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Collections;

public class MindTheEights extends Player{

    //    should always be aware of any eights they are holding. This player will their eights until late in the game,
    //    but won't hold on to them too long (as they are worth a lot of points). Once any opponent goes down to one card,
    //    it's time to play your eight. If you have two eights, start playing them once an opponent goes down to two cards.
    //    (Expand this for 3 or 4 or more eights.)

    //    Methods: Player discard: (if drawpile.match), player draw: (if not matching),
    //             place eight: tracks num of 8s = n, player discard calls if any player has n cards left
    //    Attributes:
    //    Constructor: public MindTheEights(Card[] cards)
    public MindTheEights(Card[] cards){
        this.hand = new ArrayList<Card>(Arrays.asList(cards));
    }

    @Override
    public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
        Card topOfDiscard = discardPile.top();
        boolean cardPlayed = false;
        boolean temp = false;
        //String suitEight = "";
        if ((discardPile.top()).getRank() == 4 && super.mchecker_4 == true){
            System.out.println("skipping the turn.. :(");
            super.mchecker_4 = false;
            return false;
            
        }
        //pick up 2 conditional
        if(topOfDiscard.getRank() == 2 && super.mchecker_2 == true){
            this.hand.add(drawPile.pop());
            this.hand.add(drawPile.pop());
            cardPlayed = true;
            temp = true;
            super.mchecker_2 = !(super.mchecker_2);
        }
        if(cardPlayed == false){

            //cardPlayed can become true if MT8
            cardPlayed = this.discardEight(discardPile,drawPile,players);

        }
        if(cardPlayed == false){
            //Ensures user doesn't play an 8 when not wanted
            for(Card aCard : this.hand){
                if(aCard.getRank() == 8){
                    continue;
                }
                //Play a card if match with discardpile top
                else if( topOfDiscard.getSuit().equals(aCard.getSuit())
                        ||  topOfDiscard.getRank() == aCard.getRank() ){
                    discardPile.add(aCard);
                    this.hand.remove(aCard);
                    cardPlayed = true;
                    break;
                }
            }
        }

        if(cardPlayed == false){
            //pick up a card:
            Card newCard = drawPile.pop();
            if(newCard.getRank() == 8){
                cardPlayed = discardEight(discardPile,drawPile,players);

            }else if(topOfDiscard.getSuit().equals(newCard.getSuit())
                    || topOfDiscard.getRank() == newCard.getRank()){
                discardPile.add(newCard);
                cardPlayed = true;
            }else{
                this.hand.add(newCard);
            }

        }

        System.out.println(this.hand.toString());
        return this.hand.size() == 0;
    }

    //returns number of eights in hand
    private int getEights(){
        int count = 0;
        for (Card aHand : hand) {
            if (aHand.getRank() == 8) {
                count++;
            }
        }
        return count;
    }

    public String wildEight(DiscardPile       discardPile, 
                                 Stack<Card>       drawPile, 
                                                    ArrayList<Player> players){
       return Card.SUITS[(int) Math.floor(Math.random() * 4)];

    }

    //Mind the Eights strategy
    private boolean discardEight(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
        Card topOfDiscard = discardPile.top();
        String suitEight = "";
        for(Player p : players){

            //checking to see if an opponent has as many cards as this has 8s
            if( p.getSizeOfHand() <= this.getEights() ){
                //discard an 8 onto disc pile
                for (Card aCard : this.hand) {
                    if (aCard.getRank() == 8) {
                        discardPile.add(aCard);
                        this.hand.remove(aCard);
                        suitEight = this.wildEight(discardPile,drawPile,players);
                        System.out.println("---------------------------------Eight Played");
                        System.out.println("Changing suit to   :   "+suitEight);
                        discardPile.top().setSuit(suitEight);
                        System.out.println("---------------------------------Suit Changed");
                        super.mchecker_8 = !(super.mchecker_8);
                        return true;
                    }
                }// end for
            }// end if /* else just play a matching card */
        }//end for
        return false; //might change. nvm good.
    }
}
