public class Card{

	public static final int SPADES   = 1;
	public static final int HEARTS   = 2;
	public static final int CLUBS    = 3;
	public static final int DIAMONDS = 4;

	public static final int ACE      = 1;
	public static final int TWO      = 2;
	public static final int THREE    = 3;
	public static final int FOUR     = 4;
	public static final int FIVE     = 5;
	public static final int SIX      = 6;
	public static final int SEVEN    = 7;
	public static final int EIGHT    = 8;
	public static final int NINE     = 9;
	public static final int TEN      = 10;
	public static final int JACK     = 11;
	public static final int QUEEN    = 12;
	public static final int KING     = 13;

	int Suit;
	int Face;
	boolean FceDwn;
	
	public Card(int cardSuit, int cardFace){
		this.Suit = cardSuit;
		this.Face = cardFace;
		FceDwn = true;
		}

	public String getSuit(){
		if (this.Suit == 1){
			String s = "Spades";
			return s;
		}
		
		else if (this.Suit == 2){
			String s = "Hearts";
			return s;
		}
		
		else if (this.Suit == 3){
			String s = "Clubs";
			return s;
		}
		
		else if (this.Suit == 4){
			String s = "Diamonds";
			return s;
		}
		
		else{
			return null;
		}
	}
	
	public int getFace(){
		return Face;
	}
	
	public int getValue(){
		 if (this.Face == JACK || this.Face == QUEEN || this.Face == KING){
			return 10;
		}
		 
		 else{
			 return this.Face;
		 }
	}

	
}