package poly;

public class Sales {
	
	private Accounting accounting = Accounting.getAccounting();
	
	public Sales() {}

	
	// 제품 판매
	public int sellProduct(Coffee coffee, int salesCnt) {
		
		// 커피 객체에 판매 등록하고 결제 금액을 반환
		int payPrice = coffee.registerSales(salesCnt);
		
		// 결제 금액이 0보다 크면 결제가 정상적으로 진행된다
		if( payPrice >0 ) {
			// 계좌 객체에 매출을 등록한다
			accounting.registerSales(payPrice);
		}
		
		// 결제 금액을 반환한다.
		return payPrice;
	}
	
	// 환불 등록
	public int refundProduct(Coffee coffee, int refundCnt) {
		
		int refundPrice = coffee.registerRefund(refundCnt);
		
		return refundPrice;
	}
	
	// 반품 등록
	public int returnProduct(Coffee coffee, int returnCnt ) {
		
		int returnPrice = coffee.registerReturn(returnCnt);
		if(returnPrice > 0) {
			accounting.registerSales(returnPrice);
		}
		
		return returnPrice;
	}
	
	
}
