package org.zerock.board.repository;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Setter(onMethod_=@Autowired)
    private MemberRepository memberRepo;

    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach( i-> {
            Member member = Member.builder()
                    .email("user"+i + "@aaa.com")
                    .password("1111")
                    .name("USER"+i)
                    .build();

            memberRepo.save(member);
        });
    }
}
