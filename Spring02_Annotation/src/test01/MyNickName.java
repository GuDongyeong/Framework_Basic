package test01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyNickName {
	// nickName 클래스를 의존성 주입받을 클래스

	// @Component 어노테이션이 있는 클래스 내에서 사용가능한 어노테이션
	@Autowired
	private NickName nickName;
	
	public MyNickName() {}

	public NickName getNickName() {
		return nickName;
	}

	public void setNickName(NickName nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "MyNickName [nickName=" + nickName + "]";
	}
	
	

}
