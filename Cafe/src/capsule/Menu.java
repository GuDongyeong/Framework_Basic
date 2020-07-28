package capsule;

import java.util.Scanner;


public class Menu {
	
	Scanner sc = new Scanner(System.in);
	
	private Accounting accounting;
	private Sales sales;
	
	// 사용자가 판매할 커피의 종류를 결정할 수 있다.
	private Coffee[] coffeeArr;
	
	public Menu() {
		
		accounting = new Accounting();
		System.out.print("잔고를 입력하세요 : ");
		
		// 사용자에게 잔고를 입력받아서 accounting 객체에 전달
		accounting.setBalance(sc.nextInt());
		
		// 생성자로 의존성 주입
		sales = new Sales(accounting);
		
		System.out.println("----------------------------");
		System.out.println("커피 종류의 가짓수를 입력하세요 : ");
		
		// 사용자가 지정한 커피 종류의 수 만큼 커피 배열의 길이 지정
		coffeeArr = new Coffee[sc.nextInt()];
		
		System.out.println("----------------------------");
		
		// 커피 객체를 생성하여 배열에 추가
		for(int i = 0; i < coffeeArr.length; i++) {
			
			Coffee coffee = new Coffee(accounting);
			int no = i + 1;
			
			System.out.print( no + "번째 커피의 이름을 등록하세요 : ");
			coffee.setName(sc.next());
			
			System.out.print( no + "번째 커피의 판매가를 등록하세요 : ");
			coffee.setSalesPrice(sc.nextInt());
			
			System.out.print( no + "번째 커피의 매입가를 등록하세요 : ");
			coffee.setPurchasePrice(sc.nextInt());
			
			System.out.print( no + "번째 커피의 재고를 등록하세요 : ");
			coffee.setStock(sc.nextInt());
			
			System.out.print( no + "번째 커피의 안전재고를 등록하세요 : ");
			coffee.setSafetyStock(sc.nextInt());
			
			System.out.println("==================================");
			coffeeArr[i] = coffee;
			
		}
		
	}
	
	public void showIndex() {
		
		do {
			
			System.out.println("\n============메뉴============");
			System.out.println("메뉴(0)");
			System.out.println("현황(1)");
			System.out.println("종료(2)");
			System.out.print("입력 : ");
			
			int inputMenu = sc.nextInt();
			
			switch(inputMenu) {
			case 0: showMenu(); break;
			case 1: showStates(); break;
			case 2: System.out.println(" * 프로그램 종료"); return;
			default: System.out.println(" * 잘못된 변호를 입력하였습니다");
			}
			
			
		}while(true);
		
	}

	
	
	private void showMenu() {
		
		System.out.println("\n ****** 음료 메뉴 ******");
		
		for(int i = 0; i < coffeeArr.length; i ++) {
			System.out.println(coffeeArr[i].getName() + "(" + i + ")");
		}
		
		System.out.println("\n 판매등록(0)");
		System.out.println("고객 환불 등록(1)");
		System.out.println("반품 등록(2)");
		System.out.print(" 입력 : ");
		int inputMenu = sc.nextInt();
		
		switch(inputMenu) {
		case 0: showSales(); break;
		case 1: showRefund(); break;
		case 2: showReturn(); break;
		default: System.out.println(" * 잘못된 번호를 입력하였습니다. ");
		
		}
		
		
	}
	
	private void showSales() {
		
		System.out.print("\n * 판매한 커피의 코드를 입력하세요 : ");
		int inputType = sc.nextInt();
		
		
		System.out.print(" * 주문량을 입력하세요 : ");
		int orderCnt = sc.nextInt();
		
		int payPrice = sales.sellProduct(coffeeArr[inputType], orderCnt);
		
		if( payPrice > 0) {
			System.out.println("\n 제품명 : " + coffeeArr[inputType].getName()
							+ "\n 판매가 : " + coffeeArr[inputType].getSalesPrice()
							+ "\n 구매개수 : " + orderCnt
							+ "\n 결제 금액 : " + payPrice
							+ "\n 남은 재고 : " + coffeeArr[inputType].getStock()
							);
			
		}else {
			System.out.println(" * 주문이 취소되었습니다");
		}
		
	}
	
	private void showRefund() {
		
		System.out.print("\n * 환불한 커피의 코드를 입력하세요 : ");
		int inputType = sc.nextInt();
		
		
		System.out.print(" * 환불수량을 입력하세요 : ");
		int refundCnt = sc.nextInt();
		
		int refundPrice = sales.refundProduct(coffeeArr[inputType], refundCnt);
		
		if( refundPrice > 0) {
			System.out.println("\n 제품명 : " + coffeeArr[inputType].getName()
							+ "\n 판매가 : " + coffeeArr[inputType].getSalesPrice()
							+ "\n 환불 개수 : " + refundCnt
							+ "\n 환불 금액 : " + refundPrice
							+ "\n 남은 재고 : " + coffeeArr[inputType].getStock()
							);
			
		}else {
			System.out.println(" * 환불이 취소되었습니다");
		}
		
	}
	
private void showReturn() {
		
		System.out.print("\n * 반품한 커피의 코드를 입력하세요 : ");
		int inputType = sc.nextInt();
		
		
		System.out.print(" * 반품수량을 입력하세요 : ");
		int returnCnt = sc.nextInt();
		
		int returnPrice = sales.returnProduct(coffeeArr[inputType], returnCnt);
		
		if( returnPrice > 0) {
			System.out.println("\n 제품명 : " + coffeeArr[inputType].getName()
							+ "\n 판매가 : " + coffeeArr[inputType].getSalesPrice()
							+ "\n 환불 개수 : " + returnCnt
							+ "\n 환불 금액 : " + returnPrice
							+ "\n 남은 재고 : " + coffeeArr[inputType].getStock()
							);
			
		}else {
			System.out.println(" * 반품이 취소되었습니다");
		}
		
	}
	
	
	
	private void showStates() {
		System.out.println("==========================");
		
		// 모든 커피들의 재고와 판매량을 출력
		for(Coffee coffee : coffeeArr) {
			System.out.printf("%10s 재고 : %d | 판매량 : %d \n"
						, coffee.getName(), coffee.getStock(), coffee.getSalesCount());
		}
		
		System.out.println("* 재고 : " + accounting.getBalance()
						+ " | 매출 : " + accounting.getSalesPrice()
						+ " | 지출 : " + accounting.getExpences()
		);
		
	}
	
	
}
