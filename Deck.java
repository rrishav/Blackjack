public class Deck{
	Card newdeck [] = new Card[52];
	public static int count =-1;
	 
	
	
	public Deck(){
		int counter=0;
		for (int x = 1; x <= 4; x++){
			for (int y = 1; y <= 13; y++){
				newdeck[counter]=new Card(x,y);
				counter++;
			}
		}
		
	}

	

	public Card deal(){
		if (count <= 51){
			count += 1;
			return newdeck[count];
		}
		return null;
		
	}
	
	
	public boolean isEmpty(){
		if (count >= 51){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public void shuffle(){
		for (int z = 0; z <= 51; z++){
			int num=(int)(Math.random()*52);
			Card temp=newdeck[z];
			newdeck[z]=newdeck[num];
			newdeck[num]=temp;
		}
		
	}
}