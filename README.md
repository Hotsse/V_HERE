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
docker run -p 8080:8080 ghcr.io/hotsse/v-here:latest
```
