package poly;

public class CoffeeFactory {
	
	private Accounting accounting = Accounting.getAccounting();
	
	public CoffeeFactory() {}
	

	public Coffee createCoffee(String type) {
		
		Coffee coffee = null;
		
		switch(type) {
		case "p" : coffee = new PremiumCoffee(); break;
		case "n" : coffee = new Coffee();
		default : System.out.println("잘못 입력하였습니다");
		}
		
		return coffee;
		
	}
}
