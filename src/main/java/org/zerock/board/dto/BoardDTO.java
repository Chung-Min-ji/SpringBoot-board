package org.zerock.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Member를 참조하지 않고, 화면에서 필요한 작성자의 이메일과 작성자 이름으로 처리 (Member 엔티티와 다른 점)
public class BoardDTO {
    private Long bno;
    private String title;
    private String content;
    private String writerEmail; // 작성자 이메일(id)
    private String writerName; // 작성자 이름
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private int replyCount; // 해당 게시글의 댓글 수
}
