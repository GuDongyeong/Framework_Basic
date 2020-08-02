package test01.proxy;

public class ManProxy implements Develop {

	@Override
	public void classWork() {
		
		// 부기능 : 중복되는 코드(예외처리 등등)
		System.out.println("출석카드를 찍는다.");
		
		try {
			// 주기능 호출
			new Man().classWork();
		}catch(Exception e) {
			System.out.println("쉬는 날이었다");
		}finally {
			System.out.println("집에 간다");
		}
	}

}
