package test02;

import javax.annotation.Resource;

// Component 어노테이션으로 올라가는 bean은 singleton 이다.
//@Component
public class School {
	
	// 의존성 주입
//	@Autowired
//	@Qualifier("lee")
	@Resource(name="hong")
	private Student kim;
	private int grade;
	
	public School() {}

	public School(Student kim, int grade) {
		super();
		this.kim = kim;
		this.grade = grade;
	}

	public Student getKim() {
		return kim;
	}

	public void setKim(Student kim) {
		this.kim = kim;
	}
	
	// setKim과 동일한 역할
	public void thisKim(Student kim) {
		this.kim = kim;
	}
	

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "School [kim=" + kim + ", grade=" + grade + "]";
	}
	
	
	
	

}
