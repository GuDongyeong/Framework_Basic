package run;

import builder.Book;
import builder.CalcTest;

public class Run {

	public static void main(String[] args) {
		
		Book book = new Book.BookBuilder()
					.title("�ظ�����")
					.author("���طѸ�")
					.publisher("������")
					.page(450)
					.build();
				
		int res = new CalcTest().add(4).add(5).sub(3).out();
		System.out.println(res);
		
		
		//System.out.println(book);
				
				
				
				
				
				
		
	}

}
