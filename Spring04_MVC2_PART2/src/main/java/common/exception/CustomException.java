package common.exception;

public class CustomException extends Exception{
	// Exception을 상속 받은 예외 객체
	
	// message : Exception을 생성할 때 생성자의 매개변수로 문자열을 넘기면, getMessage 메소드를 통해 다시 사용할 수 있다
	public CustomException(String message) {
		super(message);
	}
	
}
