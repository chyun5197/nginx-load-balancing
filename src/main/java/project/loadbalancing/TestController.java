package project.loadbalancing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {
    // 로드밸런싱으로 서버가 바뀌면 UUID로 응답도 변하도록
    public static final UUID randomUUID = UUID.randomUUID();

    @GetMapping("/test")
    public ResponseEntity<UUID> test() {
        return ResponseEntity.ok(randomUUID);
    }

    // 로컬 실행시
    //$ ./gradlew clean build -x test
    //$ docker compose up --build -d
    // 스프링 build 후 -> 도커 build

    // 현재 방법은 1개의 스프링부트 빌드한 jar를 3개의 이미지(컨테이너)로 복붙 나누어서 로드 밸런싱
    // 스프링 프로필을 달리하고 Dockerfile .active=프로필 따로 줘서 별개로 만들어서 올릴수도 있음

    // nginx upstream의 server 주소를
    // 1. '컨테이너이름:컨테이너포트번호'로 연결할지 [컨테이너 포트번호는 중복되도 별개로 존재]
    // 2. '호스트주소:호스트포트번호'로 연결할지에 따라 컴포즈 ports 작성이 달라짐

    // 1번 방식은
    // ports:
    //  - 아무거나:8080
    // 혹은
    // expose:
    //  - 8080
    // 다만 expose는 도커 컨테이너 내부에서만 포트번호로 접근 가능

    // 2번 방식은
    // ports:
    //  - 8080:8080
    // ports:
    //  - 8081:8080
    // ports:
    //  - 8082:8080

}
