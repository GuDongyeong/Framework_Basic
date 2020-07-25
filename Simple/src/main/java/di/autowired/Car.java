package di.autowired;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import di.tire.Tire;

public class Car {
	
	// Qualifier : 어떤 객체를 만들지 선택할 수 있다
	@Qualifier("gTire")
//	@Autowired
//	@Resource
	@Inject
	private Tire tire;
	
	public String getInfo() {
		return tire.getProduct() + "장착함!";
	}

	public Tire getTire() {
		return tire;
	}
	
	public void setTire(Tire tire) {
		this.tire = tire;
	}

}
