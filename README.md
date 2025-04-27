# V_HERE
위치 기반 사진 공유 어플리케이션(Kakao Map API)  
(PWA 기반 웹앱 지원)

## Spec
- Spring Boot 2.7.18
- Java 21
- Maven Build
- Thymeleaf
- Spring Security
- H2 Database
- JPA (Hibernate) + JDBC MyBatis
- support PWA
- Service on AWS
- Docker

## Docker 환경 구성
아래 Container Registry 를 참고하여 테스트

### V_HERE 서비스
- https://github.com/users/Hotsse/packages/container/package/v-here
``` bash
docker pull ghcr.io/hotsse/v-here:latest
docker run --platform=linux/amd64 -p 8080:8080 ghcr.io/hotsse/v-here:latest
```

## 서비스 미리보기
<img width="200" alt="image" src="https://github.com/user-attachments/assets/b2dd3f60-304c-4a46-8606-36ce6ff70b03" />
<img width="200" alt="image" src="https://github.com/user-attachments/assets/79c32e59-5ff2-4f0b-8f99-e42f27001463" />
<img width="200" alt="image" src="https://github.com/user-attachments/assets/0193f2e2-928a-4c37-9cde-9509cb1dfd5f" />

- 회원가입 및 로그인 기능 (PW SHA256 해시화)
- Kakao Map API 를 활용한 지도 기반 추천 게시글 조회 기능
- 이미지 파일 업로드가 가능한 게시글 작성 기능
