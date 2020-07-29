package builder;

public class run {
	public static void main(String[] args) {
		
		Book book = new Book.BookBuilder()
							.title("해리포터")
							.author("조앤롤링")
							.publisher("민음사")
							.page(450)
							.build();
		
		System.out.println(book);
		
		//--------------------------------------------------
		
		int result = new Calculate.CalculateBuilder().add(4).add(5).substract(3).out();
		
		System.out.println(result);
	}
	

}
