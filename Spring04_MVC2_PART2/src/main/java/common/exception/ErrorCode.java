package common.exception;

// 상수를 효율적으로 다루기 위해서 자바 1.5버전부터 추가
// 주로 코드(에러코드, 상태코드, 회원등급코드 등)를 다루기 위해 사용됨
public enum ErrorCode {
	
	// 내부적으로 활용하는 에러코드 등록
	
	// 메일
	M_ERROR_01("메일 전송 중 에러가 발생하였습니다.","join.do"),
	// 파일
	F_ERROR_01("파일 등록 중 에러가 발생하였습니다.", "noticelist.do");
	
	private final String MESSAGE;
	private final String URL;
	
	ErrorCode(String msg, String url){
		this.MESSAGE = msg;
		this.URL = url;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public String getURL() {
		return URL;
	}
	
	

}
