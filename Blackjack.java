public class Blackjack {
	public static void main(String[] args){
		
		
		
		int cardCount = 0;
		int numofGame = 0;
		
		System.out.println("Enter The Number Of Players Excluding The Dealer");
		int plyr = IO.readInt();
		Player[] plyrs = new Player[plyr];
		
		for (int x = 0; x < plyr; x++){
			int a = x+1;
			
			System.out.println("Name Of " + a + " Player.");
			String player_name = IO.readString();
			
			System.out.println("Amount This Player has in bank");
			double money = IO.readDouble();
			plyrs[x] = new Player(player_name,money );
		}
		
		boolean game;
		
		do{
			numofGame += 1;
			double bid;
		
			for (int y = 0; y < plyr; y++){
				System.out.println(plyrs[y].getName() + " has "+"$" +plyrs[y].getMoney());
				System.out.println("How much does the player want to bet?");
				bid = IO.readDouble();
				plyrs[y].placeBet(bid);  
			}
			
			Deck deckOfCards = new Deck();
			deckOfCards.shuffle();
			Card dealerCard1 = deckOfCards.deal();
			numofGame = emptyDeck(numofGame, deckOfCards);
			Card dealerCard2=deckOfCards.deal();
			numofGame = emptyDeck(numofGame, deckOfCards);
			
			cardCount += checker(dealerCard1);    
			cardCount += checker(dealerCard2);
			
			if (dealerCard1.getFace() == 1){
				System.out.println("The cards of the dealer are " + dealerCard1.getFace() + " " + dealerCard1.getSuit());
			
				for (int x = 0; x < plyr; x++) {
					int insurance = 0;
					System.out.println("Dealer has an Ace!");
					System.out.println(plyrs[x].getName() + " do you want insurance? ");
					boolean isinsurance = IO.readBoolean();
				
					if (isinsurance) {
						do {
							System.out.println(
									"How much insurance do you want to place? (Must be less or equal to half of placed bet)");
							insurance = IO.readInt();
						}
						while ((insurance < 0) || (insurance > (plyrs[x].getBet() / 2)));
						plyrs[x].addInsurance(insurance);
						plyrs[x].isInsured();
					}
				}
			}
			boolean blackjack = true;
			if (dealerCard1.getFace() == 1 && dealerCard2.getValue() == 10) {
				blackjack = false;
				System.out.println("Dealer has " + dealerCard2.getFace() + dealerCard2.getSuit());
				System.out.println("Dealer has blackjack");
				for (int x = 0; x < plyr; x++) {
					if (plyrs[x].Insured()) {
						plyrs[x].winInsurance();
					}
					else {
						plyrs[x].Lose();
					}
					System.out.println(plyrs[x].getName() + ", your money is: " + plyrs[x].getMoney());
				}
			}
			else if (dealerCard1.getFace() == 1) {
				System.out.println("Dealer does not have blackjack");
				for (int x = 0; x < plyr; x++) {
					if (plyrs[x].Insured()) {
						plyrs[x].loseInsurance();
					}
				}
			}
			
			if (blackjack){
				for(int x=0;x<plyr;x++){
					System.out.println("The cards of the dealer are " + dealerCard1.getFace() + " " + dealerCard1.getSuit());
					Card c1=deckOfCards.deal();
					numofGame= emptyDeck(numofGame, deckOfCards);
					Card c2=deckOfCards.deal();
					numofGame= emptyDeck(numofGame, deckOfCards);
					cardCount+=(checker(c1) + checker(c2));
					int score=0;
					score=c1.getValue() + c2.getValue();
					
					System.out.println(plyrs[x].getName()+", the 2 cards are:"+c1.getFace() + " " + c1.getSuit()
					+ " and " + c2.getFace() + " " + c2.getSuit());
					
					System.out.println("Your score is:" + score);
					
					boolean split = false;
					if (c1.getFace() == c2.getFace()
							&& ((plyrs[x].getBet() * 2) <= plyrs[x].getMoney())) {
						System.out.println("Do you want to split? (yes to split)");
						split = IO.readBoolean();
					}
					if (split) { 
						score = 0;
						System.out.println("Your first card is: " + c1.getFace() + c1.getSuit());
						Card c3 = deckOfCards.deal();
						numofGame = emptyDeck(numofGame, deckOfCards);
						cardCount += checker(c3); 
						System.out.println("Dealt card: " + c3.getFace() + c3.getSuit());
						score = c1.getValue()+c3.getValue();
						System.out.println("Your sum: " + score);
						Boolean hit;
						
						do {
							hintSystem(cardCount, numofGame);
							System.out.println("Do you want to hit? ");
							hit = IO.readBoolean();
						
							if (hit) {
								Card c = deckOfCards.deal();
								numofGame = emptyDeck(numofGame, deckOfCards);
								cardCount += checker(c);
								System.out.println("Dealt card: " + c.getFace() + c.getSuit());
								score += c.getValue();
							
								if (score > 21) {
									System.out.println("Your sum: " + score);
									System.out.println("Busted! ");
									hit = false;
									break;
								}
								System.out.println("Your sum: " + score);
							}
						}
						while (hit);
						plyrs[x].addScore(score);
						
						score = 0;
						System.out.println("Your second card is: " + c2.getFace() + c2.getSuit());
						Card c4 = deckOfCards.deal();
						numofGame = emptyDeck(numofGame, deckOfCards);
						cardCount += checker(c4);
						System.out.println("Dealt card: " + c4.getFace() + c4.getSuit());
						score = c2.getValue()+c4.getValue();
						System.out.println("Your sum: " + score);
						
						do {
							hintSystem(cardCount, numofGame);
							System.out.println("Do you want to hit?");
							hit = IO.readBoolean();
						
							if (hit) {
								Card temp = deckOfCards.deal();
								System.out.println(temp.getFace() +" " + temp.getSuit());
								score+=temp.getValue();
							
								if (score>21){
									System.out.println("Your score is:" + score);
									System.out.println("You have busted" + plyrs[x].getName() + " loses"
											+ " the round");
									break;
								}
								System.out.println("Your score is:" + score);
							}
						}
						while (hit);
							plyrs[x].addScore1(score);
							plyrs[x].split();
					}
					else {
						boolean doubleDown = false;
						
						if ((plyrs[x].getBet() * 2) <= plyrs[x].getMoney()) {
							System.out.println("Do you want to double down? ");
							doubleDown = IO.readBoolean();
						}
						if (doubleDown) {
							Card c3 = deckOfCards.deal();
							numofGame = emptyDeck(numofGame, deckOfCards);
							cardCount += checker(c3);
							System.out.println("Dealt card: " + c3.getFace() + c3.getSuit());
							score += c3.getValue();
							System.out.println("Your sum: " + score);
							plyrs[x].addScore(score);
							plyrs[x].doubleDown();
						}
						else { 
							Boolean hit;
							do {
								hintSystem(cardCount, numofGame);
								System.out.println("Do you want to hit? ");
								hit = IO.readBoolean();
								if (hit) {
									Card c = deckOfCards.deal();
									numofGame = emptyDeck(numofGame, deckOfCards);
									cardCount += checker(c);
									System.out.println(c.getFace() + c.getSuit());
									score += c.getValue();
									if (score > 21) {
										System.out.println("Your score: " + score);
										System.out.println("Busted! " + plyrs[x].getName() + " loses");
										break;
									}
									System.out.println("Your score: " + score);
								}
							}
							while (hit);
							plyrs[x].addScore(score);
						}
					}
				}
				
				int DleScr;
				DleScr= dealerCard1.getValue() + dealerCard2.getValue();
				System.out.println("The dealer has " + dealerCard1.getFace() + " " + dealerCard1.getSuit()
				+ " and " + dealerCard2.getFace()+ dealerCard2.getSuit());
				boolean dealerBust = true;
				
				while (DleScr < 17) {
					System.out.println("Dealer's total: " + DleScr);
					System.out.println("Dealer hits");
					Card temp = deckOfCards.deal();
					numofGame=emptyDeck(numofGame,deckOfCards);
					cardCount+=checker(temp);
					System.out.println(temp.getFace() + " " + temp.getSuit());
					DleScr += temp.getValue();
					if (DleScr > 21) {
						System.out.println("Dealer Busted");
						System.out.println("Dealer Total: " + DleScr);
						dealerBust = false;
					}
				}
				
				if (DleScr >= 17 && dealerBust) {
					System.out.println("Dealer's Total: " + DleScr);
					System.out.println("Dealer stands");
				}
				
				for(int y = 0; y < plyr; y++){
					if (plyrs[y].doSplit()){
						System.out.println(plyrs[y].getName() + " has the amount" + plyrs[y].getMoney());
						if (plyrs[y].getScre() > 21) {
							System.out.println("Busted!");
							plyrs[y].Lose();
						}
						else if (DleScr > 21) {
							System.out.println(plyrs[y].getName() + " wins first hand!");
							plyrs[y].Win();
						}
						else if (DleScr > plyrs[y].getScre()) {
							System.out.println(plyrs[y].getName() + " loses first hand");
							plyrs[y].Lose();
						}
						else if (DleScr < plyrs[y].getScre()) {
							System.out.println(plyrs[y].getName() + " wins first hand!");
							plyrs[y].Win();
						}
						else if (DleScr == plyrs[y].getScre()) {
							System.out.println(plyrs[y].getName() + " first hand tied with the dealer!");
						}
						System.out.println(plyrs[y].getName() + "'s second sum is " + plyrs[y].getScre1());
						
						if (plyrs[y].getScre1() > 21) {
							System.out.println("Busted!");
							plyrs[y].Lose();
						}
						else if (DleScr > 21) {
							System.out.println(plyrs[y].getName() + " wins second hand!");
							plyrs[y].Win();
						}
						else if (DleScr > plyrs[y].getScre1()) {
							System.out.println(plyrs[y].getName() + " loses second hand");
							plyrs[y].Lose();
						}
						else if (DleScr < plyrs[y].getScre1()) {
							System.out.println(plyrs[y].getName() + " wins second hand!");
							plyrs[y].Win();
						}
						else if (DleScr == plyrs[y].getScre1()) {
							System.out.println(plyrs[y].getName() + " second hand tied with the dealer!");
						}
					}
				}

				
				for (int x = 0; x < plyr; x++) {
					System.out.println(plyrs[x].getName() + ", your worth is: " + plyrs[x].getMoney());
				}
			}

			for (int x = 0; x < plyr; x++) {
				if (plyrs[x].getMoney() <= 0) {
					System.out.println("Someone is broke. Cannot play again.");
					return;
				}
			}
			System.out.println("Want to Play again? (yes or no)");
			game = IO.readBoolean();
		}
		while (game);

	}
					
				
	private static int checker(Card c) {
		int x = 0;
		if (c.getFace() == 10 || c.getFace() == 11 || c.getFace() == 12 || c.getFace() == 13 || c.getFace() == 1) {
			x = -1;
		}
		else if (c.getFace() == 7 || c.getFace() == 8 || c.getFace() == 9) {
			x = 0;
		}
		else if (c.getFace() == 2 || c.getFace() == 3 || c.getFace() == 4 || c.getFace() == 5 || c.getFace() == 6) {
			x = 1;
		}
		return x;
	}
	private static void hintSystem(int cheaterCounter, int gamesPlayed) {
		if (gamesPlayed >= 3) {
			if (cheaterCounter > 5) {
				System.out.println("You Might Think Of Hitting!");
			}
			else if (gamesPlayed <= 0) {
				System.out.println("You Might Think Of Standing!");
			}
			else {
				System.out.println("You Shuld Be careful!");
			}
		}
	}
	
	private static int emptyDeck(int gamesPlayed, Deck deckOfCards) {
		if (deckOfCards.isEmpty()) {
			gamesPlayed = 0;
		}
		return gamesPlayed;
	}		
}