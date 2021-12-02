package org.zerock.board.repository;

import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Setter(onMethod_=@Autowired)
    private ReplyRepository replyRepo;

    @Test
    public void insertReply(){
        IntStream.rangeClosed(1, 300). forEach(i->{
            // 1~100 중 랜덤숫자
            long bno = (long)(Math.random() * 100) + 1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply......."+ i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepo.save(reply);
        });
    }
}
