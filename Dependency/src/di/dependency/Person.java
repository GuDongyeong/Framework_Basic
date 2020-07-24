package di.dependency;

public class Person {
	public static void main(String[] args) {
		
		// 외부 객체 생성(Car에 의존적)
		Car car = new Car();
		
		System.out.println(car.getTire());
	}

}
