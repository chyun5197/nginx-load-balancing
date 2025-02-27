package project.roadbalancing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {
    // 로드밸런싱으로 서버가 바뀌면 UUID 응답도 변하도록
    public static final UUID randomUUID = UUID.randomUUID();

    @GetMapping("/test")
    public ResponseEntity<UUID> test() {
        return ResponseEntity.ok(randomUUID);
    }

    //$ ./gradlew clean build -x test
    //$ docker compose up --build -d
    // 스프링 build 후 -> 도커 build

    // 현재 방법은 같은 스프링을 빌드해서 1개의 이미지를 3개 컨테이너로 복붙 나누어서 로드 밸런싱

    // nginx upstream의 server 주소를
    // 1. '컨테이너이름:컨테이너포트번호'로 연결할지
    // 2. '호스트주소:호스트포트번호'로 연결할지에 따라 도커 ports 연결이 달라짐

    // 1번 방식은
    // ports:
    //  - 의미없음:8080
    // 혹은
    // expose:
    //  - 8080

    // 2번 방식은 ports
    // ports:
    //  - 8080:8080
    // ports:
    //  - 8081:8080
    // ports:
    //  - 8082:8080

}
