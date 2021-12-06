package org.zerock.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // Board 삭제시 댓글도 삭제
    // JPQL이용해서 update, delete 실행하려면 @Modifying 어노테이션 같이 추가해야 함
    @Modifying
    @Query("delete from Reply r where r.board.bno=:bno")
    void deleteByBno(Long bno);

    // 게시물로 댓글 목록 가져오기
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
