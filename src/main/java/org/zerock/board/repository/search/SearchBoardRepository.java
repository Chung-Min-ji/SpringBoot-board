package org.zerock.board.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.board.entity.Board;

public interface SearchBoardRepository {
    // 확장하고 싶은 기능을 인터페이스로 선언.
    // 인터페이스 내의 메서드명은 가능하면 쿼리메서드와 구별 가능하도록 작성한다.
    Board search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
