package test04;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class BeanTest {
	
	private MyClass myClass;

	public BeanTest() {
		System.out.println("BeanTest 기본 생성자");
	}

	public BeanTest(Date date) {
		System.out.println("날씨 생성자(date : " + date + ")");
	}
	
	public void setMyClass(MyClass myClass) {
		this.myClass = myClass;
		System.out.println("setMyClass 호출 : " + myClass);
	}
	
	public void setNumber(int num) {
		System.out.println("setNumber 호출 : " + num);
	}
	
	// 배열
	public void setArray(String[] arr) {
		System.out.println("setArray 호출 : " + Arrays.toString(arr));
	}
	
	// 리스트
	public void setList(List<String> list) {
		System.out.println("setList 호출 : " + list);
	}
	
	// set
	public void setSet(Set<String> set) {
		System.out.println("setSet 호출 : " + set);
	}
	
	// map
	public void setMap(Map<String, String> map) {
		System.out.println("setMap 호출 : " + map);
	}
	
	// properties
	public void setProperties(Properties pro) {
		System.out.println("setProperties 호출 : " + pro);
	}
	
	// Collection에 객체 넣기
	public void setScore(List<Score> list) {
		System.out.println("setScore 호출");
		for( Score sc : list) {
			System.out.println(sc);
		}
	}
	
}
