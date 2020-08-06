package web.book;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	
	@RequestMapping(value="jacksoncore.do", method=RequestMethod.POST)
	public String kakaoBook(@RequestBody Map<String, Object> kakaoData) {
		System.out.println(kakaoData);
		
		// document가 전체 데이터의 키 값
		List<Map<String, Object>> jsonData = (List<Map<String, Object>>) kakaoData.get("documents");
		
		for( Map<String, Object> m : jsonData) {
			System.out.println(m);
		}
		
		return "member/join";
	}

}
