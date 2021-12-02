package org.zerock.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.board.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 한개의 row(Object)내에 Object[]로 나옴
    @Query("SELECT b, w FROM Board b LEFT JOIN b.writer w WHERE b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    //JPQL
    @Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WhERE b.bno=:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value="SELECT b, w, count(r) "+
            "FROM Board b "+
            "LEFT JOIN b.writer w "+
            "LEFT JOIN Reply r ON r.board = b "+
            "GROUP BY b ",
            countQuery = "SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageble); //목록화면에 필요한 데이터


}