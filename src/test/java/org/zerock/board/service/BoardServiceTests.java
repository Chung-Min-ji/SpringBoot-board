package org.zerock.board.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Setter(onMethod_=@Autowired)
    private BoardService service;

    @Test
    public void testRegister(){
        BoardDTO dto = BoardDTO.builder()
                .title("Test.")
                .content("Test...")
                .writerEmail("user55@aaa.com") //현재 디비에 존재하는 회원 이메일
                .build();

        Long bno = service.register(dto);
    }

    @Test
    public void testList(){
        PageRequestDTO reqDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = service.getList(reqDTO);

        for(BoardDTO boardDTO : result.getDtoList()){
            System.out.println(boardDTO);
        }
    }

    @Test
    public void testGet(){
        Long bno = 100L;

        BoardDTO boardDTO = service.get(bno);

        log.info("boardDTO : {} ", boardDTO);
    }

    @Test
    public void testRemove(){
        Long bno=1L;

        service.removeWithReplies(bno);
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목 변경합니다")
                .content("내용 변경")
                .build();

        service.modify(boardDTO);
    }

}
