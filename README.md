# blog
Goal:  "회원가입, 로그인 기능이 추가된 나만의 항해 블로그 백엔드 서버 만들기"
## API 명세서
기능	API URL	Method	Request Header	Request	Response	Response header
회원 가입	/api/auth/signup	POST		{
"username": "bin1234",
"password": "Bin@12345"
}	{
"msg": "회원가입 성공",
"statusCode": 200
}
	
로그인	/api/auth/login	POST		{
"username": "bin1234",
"password": "Bin@12345"
}	{
"msg": "로그인 성공",
"statusCode": 200
}
	Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaW4xMjM0IiwiZXhwIjoxNjY5ODcwNDUyLCJpYXQiOjE2Njk4NjY4NTJ9.mm8wgaV8M70hidhPX4Ut6UONZGaxjA1KnOJT1mO59Xc
게시글 작성	/api/post	POST	Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaW4xMjM0IiwiZXhwIjoxNjY5ODcwNDUyLCJpYXQiOjE2Njk4NjY4NTJ9.mm8wgaV8M70hidhPX4Ut6UONZGaxjA1KnOJT1mO59Xc	{
"title": "게시글5",
"content": "내용5"
}	{
"id": 5,
"title": "게시글5",
"content": "내용5",
"username": "bin1234",
"createdAt": "2022-12-01T12:56:36.821474",
"modifiedAt": "2022-12-01T12:56:36.821474"
}	
게시글 목록 조회	/api/posts	GET			{
"postList": [
{
"id": 1,
"title": "게시글1",
"content": "내용1",
"username": "bin1234",
"createdAt": "2022-12-01T12:52:06.729608",
"modifiedAt": "2022-12-01T12:52:06.729608"
},
{
"id": 2,
"title": "게시글2",
"content": "내용2",
"username": "bin1234",
"createdAt": "2022-12-01T12:52:10.566505",
"modifiedAt": "2022-12-01T12:52:10.566505"
},
{
"id": 3,
"title": "게시글3",
"content": "내용3",
"username": "bin1234",
"createdAt": "2022-12-01T12:52:16.773748",
"modifiedAt": "2022-12-01T12:52:16.773748"
},
{
"id": 5,
"title": "게시글4 삭제 내용5 수정",
"content": "내용4 삭제 내용5 수정",
"username": "bin1234",
"createdAt": "2022-12-01T12:56:36.821474",
"modifiedAt": "2022-12-01T12:59:25.681261"
}
]
}	
게시글 상세 조회	/api/post/{id}	GET			{
"id": 1,
"title": "게시글1",
"content": "내용1",
"username": "bin1234",
"createdAt": "2022-12-01T12:52:06.729608",
"modifiedAt": "2022-12-01T12:52:06.729608"
}	
게시글 수정	/api/post/{id}	PUT	Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaW4xMjM0IiwiZXhwIjoxNjY5ODcwNDUyLCJpYXQiOjE2Njk4NjY4NTJ9.mm8wgaV8M70hidhPX4Ut6UONZGaxjA1KnOJT1mO59Xc	{
"title": "게시글4 삭제 내용5 수정",
"content": "내용4 삭제 내용5 수정"
}	{
"id": 5,
"title": "게시글4 삭제 내용5 수정",
"content": "내용4 삭제 내용5 수정",
"username": "bin1234",
"createdAt": "2022-12-01T12:56:36.821474",
"modifiedAt": "2022-12-01T12:56:36.821474"	
게시글 삭제	/api/post/{id}	DELETE	Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaW4xMjM0IiwiZXhwIjoxNjY5ODcwNDUyLCJpYXQiOjE2Njk4NjY4NTJ9.mm8wgaV8M70hidhPX4Ut6UONZGaxjA1KnOJT1mO59Xc		{
"msg": "게시글 삭제 성공",
"statusCode": 200
}	

## ERD 설계

