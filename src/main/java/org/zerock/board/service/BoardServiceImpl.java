package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.BoardRepository;
import org.zerock.board.repository.ReplyRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repo; // 생성자 자동주입 위한 final

    private final ReplyRepository replyRepo; // final

    @Override
    public Long register(BoardDTO dto){
        log.debug("register({}) invoked...", dto);

        Board board = dtoToEntity(dto);

        repo.save(board);

        return board.getBno();
    }

    @Override
    // entityToDTO()를 이용해서 PageResultDTO 객체를 구성하는 것이 핵심
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO reqDTO) {
        log.debug("getList({}) invoked...", reqDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> result = repo.getBoardWithReplyCount(
                reqDTO.getPageable(Sort.by("bno").descending())
        );

        return new PageResultDTO<>(result, fn);
    }

    @Override
    // 게시물 조회
    public BoardDTO get(Long bno) {
        Object result = repo.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    // 삭제기능 구현. 트랜잭션 추가
    public void removeWithReplies(Long bno) {

        // 댓글부터 삭제
        replyRepo.deleteByBno(bno);
        repo.deleteById(bno);
    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {
        // findById 대신, 필요한 순간까지 로딩을 지연하는 방식인 getById() 이용
        Board board = repo.getById(boardDTO.getBno());

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        repo.save(board);
    }
}
