package poly;

public class Accounting {
	private int balance; // 잔고
	private int salesPrice; //매출
	private int expences; // 지출
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(int salesPrice) {
		this.salesPrice = salesPrice;
	}
	public int getExpences() {
		return expences;
	}
	public void setExpences(int expences) {
		this.expences = expences;
	}
	
	private Accounting() {}
	
	private static Accounting accounting = null;
	
	public static Accounting getAccounting() {
		
		if(accounting == null ) {
			accounting = new Accounting();
		}
		
		return accounting;
		
	}
	
	// 매출 등록을 담당하는 메소드
	public int registerSales(int payPrice) {
		balance += payPrice;
		salesPrice += payPrice;
		return balance;
	}
	
	// 지출 등록을 담당하는 메소드
	public boolean regitsterExpences(int expencesPrice) {
		if(balance > expencesPrice ) {
			balance -= expencesPrice;
			expences += expencesPrice;
			return true;
		}else {
			return false;
		}
		
	}

}
