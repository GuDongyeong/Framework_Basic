package mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {
	
	// sqlSession 객체를 생성하는 팩토리 객체
	private static SqlSessionFactory sqlSessionFactory;

	static {
		
		// 마이바티스 Configuration XML 파일의 경로
		String res = "mybatis/mybatis-config.xml";
		
		try {
			Reader reader = Resources.getResourceAsReader(res);
			
			// 싱글톤 유지 코드
			if( sqlSessionFactory == null ) {
				
				// 입력 스트림으로 읽은 설정 파일을 이용한 sqlSessionFactory 객체 생성
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// private 생성자
	private MyBatisConnectionFactory() {}

	// 싱글톤 객체 반환 메소드
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}
