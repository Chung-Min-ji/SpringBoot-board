package org.zerock.board.repository;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.entity.Reply;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Setter(onMethod_=@Autowired)
    private BoardRepository boardRepo;

    @Test
    public void insertBoard(){
        IntStream.rangeClosed(1,100).forEach(i->{

            Member member = Member.builder().email("user"+i+"@aaa.com").build();

            Board board = Board.builder()
                    .title("Title..."+i)
                    .content("Content..."+i)
                    .writer(member)
                    .build();

            boardRepo.save(board);
        });
    }

    // lazyLoading의 경우, board.getWriter()는 member 테이블을 로딩해야 하는데 이미 커넥션이 끝난 상태이므로 오류 발생.
    // 따라서 트랜잭션 처리 필요하다.
    @Transactional
    @Test
    public void testRead1(){
        Optional<Board> result = boardRepo.findById(100L); //db에 존재하는 번호

        Board board = result.get();

        log.info("\t + board: {}", board);
        log.info("\t + board.getWriter(): {}", board.getWriter());
    }

    @Test
    public void testReadWithWriter(){
        Object result = boardRepo.getBoardWithWriter(100L);
        Object[] arr = (Object[])result;

        log.info("----------------------------");
        log.info("Arrays.toString(arr) : {}", Arrays.toString(arr));
    }

    @Transactional
    @Test
    public void testGetBoardWithReply(){

        List<Object[]> result = boardRepo.getBoardWithReply(100L);

        for(Object[] arr:result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testWithReplyCount(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepo.getBoardWithReplyCount(pageable);

        result.get().forEach(row->{
            Object[] arr = row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testSearch1(){
        boardRepo.search1();
    }

    @Test
    public void testSearchPage(){
        Pageable pageable =
                PageRequest.of(0,10,Sort.by("bno").descending()
                        .and (Sort.by("title").ascending()));

        Page<Object[]> result = boardRepo.searchPage("t", "75", pageable);
    }
}
