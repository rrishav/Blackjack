public class Player{
	
	private String name;
	private double money;
	private double bid;
	private int score;
	private int score1;
	private int Insurance;
	private boolean split = false;
	private boolean isinsurance = false;
	
	public Player(String name, double money){
		
		this.name = name;
		
		while (money <= 0){
			if(money < 0){
				System.out.println("Enter a new valid poitive amount");
				money = IO.readDouble();
			}
			else{
				System.out.println("Enter a new valid amount grater than 0");
				money = IO.readDouble();
			}
		}
		
		this.money = money;
		this.bid = 0;
		this.score = 0;
		this.score1 = 0;
		this.Insurance = 0;
		this.split = false;
	}
	
	public String getName(){
		return this.name;
		
	}
	
	public double getMoney(){
		return this.money;
		
	}
	
	public int getScore(){
		return this.score;
	}
	
	public int getScore1(){
		return this.score1;
	}
	
	public void setScre(int val){
		this.score += val;
	}
	
	public void addScore1(int val){
		this.score1 += val;
	}
	
	public void addInsurance(int x){
		this.Insurance += x;
	}
	
	public void isInsured(){
		this.isinsurance = true;
	}
	
	public void placeBet(double bet){
		while (bet <= 0 || bet > money){
			
			if (bet <= 0){
				System.out.println("Invalid bet amount, enter proper amount");
				bet=IO.readDouble();
			}
			
			else if (bet > money){
				System.out.println("Insufficient funds, enter proper amount");
				bet = IO.readDouble();
				
			}
		}
		this.bid = bet;
	} 
	public void doubleDown(){
		this.bid *= 2;
	}
	public double getBet(){
		return this.bid;
	}
	
	public void Win(){
		this.money += bid;
	}
	public void Lose(){
		this.money -= bid;
	}
	public void winInsurance(){
		this.money += (Insurance*2);
	}
	public void loseInsurance(){
		this.money -= Insurance;
	}
	public boolean Insured(){
		return this.isinsurance;
	}
	public void split(){
		this.split = true;
	}
	public boolean doSplit(){
		return this.split;
	}
	public void reset(){
		this.score = 0;
		this.score1 = 0;
		this.bid = 0;
		this.Insurance = 0;
		this.split = false;
		isinsurance = false;
	}


}