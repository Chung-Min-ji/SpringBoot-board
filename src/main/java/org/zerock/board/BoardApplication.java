package org.zerock.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

// 자동으로 시간 처리하려면 AuditingEntityListener 활성화 시켜야 하는데,
// 그러려면 해당 어노테이션 추가
@EnableJpaAuditing
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}
