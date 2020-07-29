package builder;

public class Calculate {
	
	private Calculate() {

	}
	
	public static class CalculateBuilder{
		
		private int num = 0;
		
		public CalculateBuilder add(int val) {
			num += val;
			return this;
		}
		
		public CalculateBuilder substract(int val) {
			num -= val;
			return this;
		}
		
		public int out() {
			return num;
		}
		
	}

}
