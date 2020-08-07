<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function kakaoBookApi(){
	
	var query = document.querySelector("#query").value;
	var xhr = new XMLHttpRequest();
	
	//요청 방식 , 요청 url
	xhr.open("GET", "https://dapi.kakao.com/v3/search/book?query=" + query);

	// 헤더 설정
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization", "KakaoAK 356ba387f310921ae8dfe4fe37b45365");

	// 통신 시작
	xhr.send();
	
	// ajax 통신이 끝나고 실행할 함수
	xhr.addEventListener("load", function(){
		// log보다 자세하게 보여줌 : dir
// 		console.dir(xhr.response);
		
		var data = xhr.response;
		console.dir(data);
		sendKakaoData(data);
	
	});
}

function sendKakaoData(data){
	
	// method : POST, url : jacksoncore.do
	var xhr = new XMLHttpRequest();
	
	// 요청 시작줄 작성
	// 앞에 /가 없으면 상대경로
	xhr.open("POST", "/jacksoncore.do");
	
	// 헤더 설정
	// Content-Type 설정 : application/json 방식 - Request message body에 있는 데이터가 json 형식의 데이터입을 header에 명시한다.
	xhr.setRequestHeader("Content-Type", "application/json");
	
	// body에 데이터 추가하고 통신 시작
	xhr.send(data);

}


</script>
</head>
<body>

<h1>카카오 검색 API 사용하기</h1>
<hr>
<input type="text" id="query" />
<button onclick="kakaoBookApi()">카카오 api 통신</button>

</body>
</html>