package di.dependency;

import di.tire.SilverTire;
import di.tire.Tire;

public class Car {
	
	// 변수만 선언한 것은 아직 의존이 아니다
	private Tire tire;
	
	public Car() {
		// 의존성 발생 지점
//		tire = new GoldTire();
		tire = new SilverTire();
		
	}
	
	public String getTire() {
		// NullPointerException 발생, 의존성이 없기 때문에
		return tire.getProduct() + " 장착함!";
	}

}
