package poly;

public class Coffee {
	protected String name;
	protected int salesPrice;
	protected int purchasePrice;
	protected int stock;
	protected int safetyStock;
	protected int salesCount;
	
	protected Accounting accounting = Accounting.getAccounting();
	
	public Coffee() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(int salesPrice) {
		this.salesPrice = salesPrice;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSafetyStock() {
		return safetyStock;
	}

	public void setSafetyStock(int safetyStock) {
		this.safetyStock = safetyStock;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public Accounting getAccounting() {
		return accounting;
	}

	public void setAccounting(Accounting accounting) {
		this.accounting = accounting;
	}
	
	// 판매등록
	public int registerSales(int salesCnt) {
		
		int payPrice = deductStock(salesCnt, salesPrice);
		if( payPrice > 0) {
			// 누적 판매 개수 += 판매개수
			salesCount += salesCnt;
		}
		
		return payPrice;
		
	}
	
	// 환불 등록
	public int registerRefund(int refundCnt) {
		
		int expencesPrice = addStock(refundCnt, salesPrice);
		if( expencesPrice > 0) {
			salesCount -= refundCnt;
		}
		
		return expencesPrice;
		
	}
	
	// 반품 등록
	public int registerReturn(int returnCnt) {
		return deductStock(returnCnt, purchasePrice);
	}
	
	
	
	// 재고 차감
	protected int deductStock(int coffeeCnt, int price) {
		
		secureSafetyStock();
		
		if(stock > coffeeCnt) {
			stock -= coffeeCnt;
			return price * coffeeCnt;
		}else if(addStock(coffeeCnt, purchasePrice) > 0){
			stock -= coffeeCnt;
			return price * coffeeCnt;
		}else {
			return 0;
		}
		
	}
	

	// 안전재고 확보
	protected void secureSafetyStock() {
		
		if(stock < safetyStock) {
			System.out.println("* 안전 재고가 부족해 재고를 확보합니다.");
			int needCnt = safetyStock * 2;
			addStock(needCnt, purchasePrice);
		}
		
	}
	
	// 재고 추가
	protected int addStock(int coffeeCnt, int price) {
		
		int expencesPrice = coffeeCnt * price;
		if( accounting.regitsterExpences(expencesPrice)) {
			System.out.println("* 재고를" + coffeeCnt + "개 추가 합니다.");
			stock += coffeeCnt;
			return expencesPrice;
		}else {
			System.out.println(" * 잔고가 부족해 재고를 추가하지 못했습니다");
			return 0;
		}
	}
	
	
	
	
	

}
