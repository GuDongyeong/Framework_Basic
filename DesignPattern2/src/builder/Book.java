package builder;

public class Book {
	//������ ������ ����
	//���� : �������� �Ű������� �ѱ�� �����Ͱ� � �ǹ̸�
	//   ���ϴ� �� �˱� ��ƴ�.
	
	//�ڹ� �� ����
	//���� : �� ������ �ǹ̸� �ľ��ϱ� ���ϴ�.
	//���� : ��ü�� �ϰ����� ������.
	//    setter�޼��尡 �������� ���� �Ұ�����(immutable)Ŭ������
	//	  ���� �� ����.
	
	//builder ����
	
	private final String title;
	private final String author;
	private final String publisher;
	private final int page;
	
	private Book(BookBuilder builder) {
		title = builder.title;
		author = builder.author;
		publisher = builder.publisher;
		page = builder.page;
	}
	
	public static class BookBuilder{
		
		private String title = "";
		private String author = "";
		private String publisher = "";
		private int page = 0;
		
		public BookBuilder title(String val) {
			title = val;
			return this;
		}
		
		public BookBuilder author(String val) {
			author = val;
			return this;
		}
		
		public BookBuilder publisher(String val) {
			publisher = val;
			return this;
		}
		
		public BookBuilder page(int val) {
			page = val;
			return this;
		}
		
		public Book build() {
			return new Book(this);
		}
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publisher=" + publisher + ", page=" + page + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
