package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board") // 주의***
// 회원이 아닌 사람도 댓글을 남길 수 있다고 가정하고, Board와의 연관관계를 맺지 않은 상태로 처리
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;

    private String replyer;

    @ManyToOne
    private Board board; // 연관관계 지정
}
