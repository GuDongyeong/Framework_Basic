package test01.proxy;

public class Run {
	public static void main(String[] args) {
		
//		Woman woman = new Woman();
//		Man man = new Man();
		
		Develop woman = new WomanProxy();
		Develop man = new ManProxy();
		
		System.out.println("여학생 입장");
		woman.classWork();
		System.out.println("-----------");
		System.out.println("남학생 입장");
		man.classWork();
		
		
	}

}
