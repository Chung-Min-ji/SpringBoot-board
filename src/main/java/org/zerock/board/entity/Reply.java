package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString // ****주의
// 회원이 아닌 사람도 댓글을 남길 수 있다고 가정하고, Board와의 연관관계를 맺지 않은 상태로 처리
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude   // ****
    private Board board; // 연관관계 지정

//    public void setBoard(Board board) {
//        this.board = board;
//    }
}
