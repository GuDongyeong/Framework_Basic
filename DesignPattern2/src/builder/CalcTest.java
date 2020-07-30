package builder;

public class CalcTest {
	
	private int num;
	
	public CalcTest add(int num) {
		this.num += num;
		return this;
	}
	
	public CalcTest sub(int num) {
		this.num -= num;
		return this;
	}
	
	public int out() {
		return num;
	}
	
	

}
